package com.example.cuu_ho_tech.Presentation.Dialog;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.graphics.drawable.Drawable;

import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.databinding.DialogNotificationBinding;
import com.example.cuu_ho_tech.Utils.OnDialogViewReadyListener;
import java.util.Objects;


public class CustomDialog extends DialogFragment {
    private DialogNotificationBinding binding;
    public static final int SUCCESS = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;
    public static final int DISCONNECT = 4;
    public static final int NOTIFICATION = 5;
    public static final int AVATAR_WRENCH = 6;

    public static final int LEFT_RIGHT = 1;
    public static final int TOP_BOTTOM = 2;
    public static final int CENTER = 3;

    private String title = "";
    private String text = "";
    private String textBtn = "Có";
    private String textBtnOutline = "Không";
    private int type = 0;
    private int typeLayoutBtn = 0;
    private int btnDrawableOutline = 0;
    private int textDrawableOutline = 0;
    private int titleDrawable = 0;
    private boolean isIconLoading = false;
    private boolean isLoading = false;
    private boolean isListView = false;
    private boolean isBack = false;


    private OnDialogViewReadyListener listener;
    public CustomDialog setOnDialogViewReadyListener(OnDialogViewReadyListener listener) {
        this.listener = listener;
        return this;
    }

    public CustomDialog setText(String text) {
        this.text = text;
        return this;
    }

    public CustomDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomDialog setType(int type) {
        this.type = type;
        return this;
    }

    public CustomDialog setTitleDrawable(int titleDrawable) {
        this.titleDrawable = titleDrawable;
        return this;
    }

    public CustomDialog setBtnDrawableOutline(int btnDrawableOutline) {
        this.btnDrawableOutline = btnDrawableOutline;
        return this;
    }

    public CustomDialog setIconLoading(boolean loading) {
        isIconLoading = loading;
        return this;
    }

    public CustomDialog setLoading(boolean loading) {
        isLoading = loading;
        return this;
    }

    public CustomDialog setBack(boolean back) {
        this.isBack = back;
        return this;
    }

    public CustomDialog setTypeLayoutBtn(int typeLayoutBtn) {
        this.typeLayoutBtn = typeLayoutBtn;
        return this;
    }

    public CustomDialog setTextDrawableOutline(int textDrawableOutline) {
        this.textDrawableOutline = textDrawableOutline;
        return this;
    }

    public CustomDialog setListView(boolean listView) {
        isListView = listView;
        return this;
    }

    public CustomDialog setTextBtn(String textBtn) {
        this.textBtn = textBtn;
        return this;
    }

    public CustomDialog setTextBtnOutline(String textBtnOutline) {
        this.textBtnOutline = textBtnOutline;
        return this;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DialogNotificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        initialize();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            Drawable background = ContextCompat.getDrawable(requireContext(), R.drawable.layout_dialog);
            if (background != null) {
                dialog.getWindow().setBackgroundDrawable(background);
            }

        }
    }


    private void initialize() {
        if(!isLoading) {
            binding.title.setText(title);
            binding.text.setText(text);
            binding.loading.setVisibility(isIconLoading ? View.VISIBLE : View.GONE);
            if(isListView){
                binding.listView.setVisibility(View.VISIBLE);
                if (listener != null) {
                    listener.onReadyListView(binding.listView);
                }
            }
            binding.title.setVisibility(title.isEmpty() ? View.GONE : View.VISIBLE);
            binding.text.setVisibility(text.isEmpty() ? View.GONE : View.VISIBLE);
            if (isBack) {
                binding.btnBack.setVisibility(View.VISIBLE);
                binding.btnBack.setOnClickListener(v -> dismiss());
            }
            setupButtons();
            setupIcon();
        } else  {
            Objects.requireNonNull(getDialog()).setCancelable(false);
            binding.layoutLoading.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                private int dotCount = 0;
                @Override
                public void run() {
                    String text = binding.textLoading.getText().toString();
                    if (dotCount < 3) {
                        binding.textLoading.setText(text + ".");
                        dotCount++;
                    } else {
                        binding.textLoading.setText(text.substring(0, text.length() - 3));
                        dotCount = 0;
                    }
                    handler.postDelayed(this, 500);
                }
            };
            handler.post(runnable);
        }
    }

    private void setupButtons() {
        switch (typeLayoutBtn) {
            case LEFT_RIGHT:
                binding.btnViewOutlineLeft.setVisibility(View.VISIBLE);
                binding.btnViewRight.setVisibility(View.VISIBLE);
                if (btnDrawableOutline != 0) {
                    Drawable background = ContextCompat.getDrawable(requireContext(), btnDrawableOutline);
                    int textColor = ContextCompat.getColor(requireContext(), textDrawableOutline);
                    binding.btnViewOutlineLeft.setTextColor(textColor);
                    binding.btnViewOutlineLeft.setBackground(background);
                }
                if (listener != null) {
                    listener.onViewReadyTwoBtn(binding.btnViewOutlineLeft, binding.btnViewRight);
                }
                binding.btnViewOutlineLeft.setText(textBtnOutline);
                binding.btnViewRight.setText(textBtn);
                binding.layoutBtnView.setVisibility(View.VISIBLE);
                break;
            case TOP_BOTTOM:
                binding.btnView.setVisibility(View.VISIBLE);
                binding.btnViewOutline.setVisibility(View.VISIBLE);
                binding.btnView.setText(textBtn);
                binding.btnViewOutline.setText(textBtnOutline);
                if (listener != null) {
                    listener.onViewReadyTwoBtn(binding.btnView, binding.btnViewOutline);
                }
                break;
            case CENTER: {
                if (titleDrawable != 0) {
                    int textColor = ContextCompat.getColor(requireContext(), titleDrawable);
                    binding.title.setTextColor(textColor);
                }
                if (btnDrawableOutline != 0) {
                    Drawable btnBackground = ContextCompat.getDrawable(requireContext(), btnDrawableOutline);
                    int textColor = ContextCompat.getColor(requireContext(), textDrawableOutline);
                    binding.btnViewOutline.setTextColor(textColor);
                    binding.btnViewOutline.setBackground(btnBackground);
                }
                if (listener != null) {
                    listener.onViewReadyOneBtn(binding.btnViewOutline);
                }
                binding.btnViewOutline.setVisibility(View.VISIBLE);
                binding.btnViewOutline.setText(textBtnOutline);
                break;
            }
        }
    }

    private void setupIcon() {
        int iconResId;
        switch (type) {
            case SUCCESS:
                iconResId = R.drawable.ic_success;
                break;
            case WARNING:
                iconResId = R.drawable.ic_warning;
                break;
            case DISCONNECT:
                iconResId = R.drawable.ic_disconnect_wifi;
                break;
            case NOTIFICATION:
                iconResId = R.drawable.ic_notification;
                break;
            case AVATAR_WRENCH:
                iconResId = R.drawable.ic_avatar_wrench;
                break;
            default:
                iconResId = 0;
                break;
        }

        if(iconResId != 0) {
            binding.icon.setVisibility(View.VISIBLE);
            binding.icon.setBackground(ContextCompat.getDrawable(requireActivity(), iconResId));
        }
    }

    public void cancel() {
        if (getDialog() != null && getDialog().isShowing()) {
            getDialog().cancel();
        }
    }
}
