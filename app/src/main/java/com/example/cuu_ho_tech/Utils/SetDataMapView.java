package com.example.cuu_ho_tech.Utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.cuu_ho_tech.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SetDataMapView {
    private Activity activity;
    private MapView mapView;
    private double latitude;
    private double longitude;

    public SetDataMapView(Activity activity, MapView mapView) {
        this.activity = activity;
        this.mapView = mapView;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public OnLocationListener actionListener;

    public void triggerAction() {
        if (actionListener != null) {
            actionListener.onLocationSelected(latitude, longitude);
        }
    }

    public void setData() {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("map_settings", Context.MODE_PRIVATE);
        Configuration.getInstance().load(activity, sharedPreferences);

        SharedPreferences saved = activity.getSharedPreferences("screen_pref", Context.MODE_PRIVATE);
        int width = saved.getInt("screen_width", 0);
        int height = saved.getInt("screen_height", 0);
        if(height <= 0) {
            WindowManager windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
            saved = activity.getSharedPreferences("screen_pref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = saved.edit();
            editor.putInt("screen_width", width);
            editor.putInt("screen_height", height);
            editor.apply();
        }
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        RotationGestureOverlay rotationGestureOverlay = new RotationGestureOverlay(mapView);
        rotationGestureOverlay.setEnabled(true);
        mapView.getOverlays().add(rotationGestureOverlay);
//        CompassOverlay compassOverlay = new CompassOverlay(activity, new InternalCompassOrientationProvider(activity), mapView);
//        compassOverlay.enableCompass();
//        Log.d("locations", width + "/" + height + dpToPx(55));
//        Log.d("ScreenDimensions", "Width: " + pxToDp(width) + ", Height: " + pxToDp(height));
//        compassOverlay.setCompassCenter(35, 120);
//        mapView.getOverlays().add(compassOverlay);
//        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        mapView.getController().setZoom(18.0);
    }


    public void setUpLocation(double latitudeEnd, double longitudeEnd, String title, String address, int icon, boolean isUser) {
        if(mapView != null) {
            GeoPoint geoPoint = new GeoPoint(latitudeEnd, longitudeEnd);
            mapView.setMinZoomLevel(4.0);
            Marker marker = new Marker(mapView);
            marker.setPosition(geoPoint);
            marker.setTitle(title);
            marker.setInfoWindow(null);
            if (isUser) mapView.getController().setCenter(geoPoint);
            else {
                marker.setOnMarkerClickListener((clickedMarker, mapView) -> {
                    float originalY = (float) geoPoint.getLatitude();
                    float bounceY = (float) (originalY + 0.0002);
                    ValueAnimator animator = ValueAnimator.ofFloat(originalY, bounceY, originalY);
                    animator.setDuration(300);
                    animator.addUpdateListener(animation -> {
                        float animatedValue = (float) animation.getAnimatedValue();
                        marker.setPosition(new GeoPoint(animatedValue, geoPoint.getLongitude()));
                        mapView.invalidate();
                    });
                    animator.start();
                    fetchAndDrawRoute(latitudeEnd, longitudeEnd);
                    triggerAction();
                    return true;
                });
            }
            Drawable customIcon = ResourcesCompat.getDrawable(activity.getResources(), icon, null);
            marker.setIcon(customIcon);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            mapView.getOverlays().add(marker);
        }
    }


    private Polyline currentRoutePolyline = null;

    public void fetchAndDrawRoute(double latitudeEnd, double longitudeEnd) {
        new Thread(() -> {
            try {
                if (currentRoutePolyline != null) {
                   mapView.getOverlays().remove(currentRoutePolyline);
                }
                String url = "https://router.project-osrm.org/route/v1/driving/" +
                        longitude + "," + latitude + ";" +
                        longitudeEnd + "," + latitudeEnd +
                        "?overview=full&geometries=geojson";

                String json = new BufferedReader(new InputStreamReader(new URL(url).openStream()))
                        .readLine();
                List<GeoPoint> routeCoordinates = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(json);
                JSONArray coordinates = jsonObject.getJSONArray("routes")
                        .getJSONObject(0)
                        .getJSONObject("geometry")
                        .getJSONArray("coordinates");
                for (int i = 0; i < coordinates.length(); i++) {
                    JSONArray point = coordinates.getJSONArray(i);
                    double lon = point.getDouble(0);
                    double lat = point.getDouble(1);
                    routeCoordinates.add(new GeoPoint(lat, lon));
                }
                activity.runOnUiThread(() -> {
                    Polyline polyline = new Polyline();
                    polyline.setPoints(routeCoordinates);
                    Paint outlinePaint = polyline.getOutlinePaint();
                    outlinePaint.setStyle(Paint.Style.STROKE);
                    outlinePaint.setColor(ResourcesCompat.getColor(mapView.getResources(), R.color.primary_main, null));
                    outlinePaint.setStrokeWidth(5f);
                    outlinePaint.setAntiAlias(true);

                    mapView.getOverlays().add(polyline);
                    currentRoutePolyline = polyline;
                    mapView.invalidate();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void addCircleOverlay(MapView mapView, double latitude, double longitude, double radiusInMeters) {
        GeoPoint center = new GeoPoint(latitude, longitude);
        Polygon circle = new Polygon(mapView);
        circle.setPoints(Polygon.pointsAsCircle(center, radiusInMeters));
        circle.setStrokeColor(ContextCompat.getColor(activity, R.color.warning_main_40));
        circle.setFillColor(ContextCompat.getColor(activity, R.color.warning_main_40));
        circle.setStrokeWidth(2.0f);
        circle.setInfoWindow(null);
        mapView.getOverlays().add(circle);
        mapView.invalidate();
    }
}
