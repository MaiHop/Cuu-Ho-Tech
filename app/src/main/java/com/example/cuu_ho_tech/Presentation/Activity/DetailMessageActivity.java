package com.example.cuu_ho_tech.Presentation.Activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.emoji2.emojipicker.EmojiViewItem;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.Adapter.DetailMessageAdapter;
import com.example.cuu_ho_tech.Presentation.ViewModel.SharedDetailMessageToAdapterViewModel;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.DeviceUtils;
import com.example.cuu_ho_tech.databinding.ActivityDetailMessagerBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailMessageActivity extends AppCompatActivity {
    ActivityDetailMessagerBinding binding;

    SharedDetailMessageToAdapterViewModel sharedDetailMessageViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailMessagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        initialize();
        // Thêm TextWatcher để theo dõi những thay đổi của văn bản trong trường nhập
        binding.edtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.btnSend.setEnabled(!s.toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        binding.edtText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.emojiPicker.setVisibility(View.GONE);
            }
        });
        binding.btnEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.emojiPicker.getVisibility() != View.VISIBLE) {
                    binding.emojiPicker.setVisibility(View.VISIBLE);
                    DeviceUtils.hideKeyboard(binding.edtText, DetailMessageActivity.this);
                    binding.rcvMessagerDetail.scrollToPosition(data.size() - 1);
                } else {
                    binding.emojiPicker.setVisibility(View.GONE);
                    DeviceUtils.showKeyboard(binding.edtText, DetailMessageActivity.this);
                    binding.rcvMessagerDetail.scrollToPosition(data.size() - 1);
                }
            }
        });
        binding.rcvMessagerDetail.scrollToPosition(data.size() - 1);
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedDetailMessageViewModel.sendMessage(binding.edtText.getText().toString());
                binding.edtText.setText("");
            }
        });
        binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                binding.getRoot().getWindowVisibleDisplayFrame(r);
                int heightDiff =  binding.getRoot().getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 200) {
                    binding.rcvMessagerDetail.scrollToPosition(data.size() - 1);
                }
            }
        });
        binding.emojiPicker.setOnEmojiPickedListener(new Consumer<EmojiViewItem>() {
            @Override
            public void accept(EmojiViewItem emojiViewItem) {
                int start = binding.edtText.getSelectionStart();
                int end = binding.edtText.getSelectionEnd();

                Editable editable = binding.edtText.getText();
                assert editable != null;
                editable.replace(start, end, emojiViewItem.getEmoji());

                // Đặt con trỏ sau emoji vừa chèn
                binding.edtText.setSelection(start + emojiViewItem.getEmoji().length());

            }
        });
        binding.btnSend.setEnabled(false);
    }

    private List<List<String>> data;

    private void initialize() {
        setSupportActionBar(binding.tbMessagerDetail);
        sharedDetailMessageViewModel = new ViewModelProvider(this).get(SharedDetailMessageToAdapterViewModel.class);
        RecyclerView recyclerView = binding.rcvMessagerDetail;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        List<MessagerResponse> messagerResponses = Arrays.asList(
//                new MessagerResponse(1, 0, 1, 1, "12/12/2023", ""),
//                new MessagerResponse(1, 1, 0, 1, "12/12/2023", ""),
//                new MessagerResponse(1, 0, 1, 1, "12/12/2023", "")
//
//        );
        data = new ArrayList<>(Arrays.asList(
                Arrays.asList("Xe của bạn hỏng rất nặng", "10:40", "12 thg 8 2024", "0", "1"),
                Arrays.asList("Xe của bạn đã sửa xong", "11:20", "13 thg 8 2024", "1", "2"),
                Arrays.asList("Kiểm tra xe định kỳ", "15:30", "14 thg 8 2024", "0", "1"),
                Arrays.asList("Bảo dưỡng xe lần đầu", "09:10", "15 thg 8 2024", "1", "2"),
                Arrays.asList("Xe cần thay lốp", "16:00", "16 thg 8 2024", "0", "2"),
                Arrays.asList("Thay nhớt định kỳ", "10:15", "17 thg 8 2024", "1", "1"),
                Arrays.asList("Báo lỗi hệ thống phanh", "12:45", "18 thg 8 2024", "0", "1"),
                Arrays.asList("Đã hoàn thành kiểm tra động cơ", "14:20", "19 thg 8 2024", "1", "2")
        ));
        DetailMessageAdapter detailMessagerAdapter = new DetailMessageAdapter(this, data, recyclerView);
        recyclerView.setAdapter(detailMessagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}