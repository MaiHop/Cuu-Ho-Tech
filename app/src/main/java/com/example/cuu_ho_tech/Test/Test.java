package com.example.cuu_ho_tech.Test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuu_ho_tech.databinding.ActivityCustomButtonLayoutBinding;

public class Test extends AppCompatActivity {
    ActivityCustomButtonLayoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomButtonLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        // Khởi tạo Pusher
//        PusherOptions options = new PusherOptions();
//        options.setCluster("ap1"); // Nhập cluster của bạn
//        Pusher pusher = new Pusher("b5f44c6c2b7e9df067d7", options);
//        pusher.connect(new ConnectionEventListener() {
//            @Override
//            public void onConnectionStateChange(ConnectionStateChange change) {
//                Log.d("PusherEvent", "State changed to " + change.getCurrentState());
//
//                if (change.getCurrentState() == ConnectionState.CONNECTED) {
//                    Channel channel = pusher.subscribe("mobile-channel");
//                    Log.d("PusherEvent", "channel " + channel);
//                    // Bind sự kiện ngay sau khi kết nối thành công
//                    channel.bind("mobileChannel", new SubscriptionEventListener() {
//                        @Override
//                        public void onEvent(PusherEvent event) {
//                            String message = event.getData();
//                            Log.d("PusherEvent", "Data received: " + message);
//                            runOnUiThread(() -> {
//                                Toast.makeText(Test.this, "Received: " + message, Toast.LENGTH_SHORT).show();
//                            });
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onError(String message, String code, Exception e) {
//                Log.d("PusherEvent", "Connection error: " + message);
//            }
//        });


//        pusher.connect(new ConnectionEventListener() {
//            @Override
//            public void onConnectionStateChange(ConnectionStateChange change) {
//                Log.d("PusherEvent", "State changed to " + change.getCurrentState());
//            }
//
//            @Override
//            public void onError(String message, String code, Exception e) {
//                Log.d("PusherEvent", "Connection error: " + message);
//            }
//        });
//
//
//        // Đăng ký kênh và sự kiện
//        Channel channel = pusher.subscribe("mobile-channel");
//        Log.d("PusherEvent","channel: " + channel);
//
//        channel.bind("mobileChannel", new SubscriptionEventListener() {
//            @Override
//            public void onEvent(PusherEvent event) {
//                String message = event.getData();
//                Log.d("PusherEvent", "Data received: " + message);
//                runOnUiThread(() -> {
//                    Toast.makeText(Test.this, "Received: " + message, Toast.LENGTH_SHORT).show();
//                });
//            }
//        });


        }
}
