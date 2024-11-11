package com.example.cuu_ho_tech.Test;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cuu_ho_tech.Presentation.Fragment.BottomSheetDialogCustomFragment;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.BottomSheetInterface;
import com.example.cuu_ho_tech.databinding.ActivityTestCreateRequestBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetDialogCustomBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetRequestCreateBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test_Create_Request_Activity extends AppCompatActivity{

    ActivityTestCreateRequestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestCreateRequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> list = new ArrayList<>();
        for(int i =0; i<=10;i++){
            list.add("Tech "+i);
        }
        Log.d("LIST_TECH", ""+list.size());

//        bs_DialogFragment = new BottomSheetDialogOrderFilterFragment(Test_Create_Request_Activity.this)
//                .setNegativeButton("Button 1", new BottomSheetInterface.OnClickFilterListener() {
//                    @Override
//                    public void onClick(BottomSheetInterface bottomsheet, List<String> list_status, String date_from, String date_to) {
//                        String status ="";
//                        for(String t : list_status){
//                            status += t +" ";
//                        }
//                        status += date_from +" " + date_to;
//                        binding.tvTechnicianLocationCurrentLocation.setText(status);
//                    }
//                })
//                .setPositiveButton("Button 2", new BottomSheetInterface.OnClickFilterListener() {
//                    @Override
//                    public void onClick(BottomSheetInterface bottomsheet, List<String> list_status, String date_from, String date_to) {
//                        String status ="";
//                        for(String t : list_status){
//                            status += t +" ";
//                        }
//                        status += date_from +" " + date_to;
//                        binding.tvTechnicianLocationCurrentLocation.setText(status);
//                        bottomsheet.dismiss();
//                    }
//                });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Test_Create_Request_Activity.this)
//                        .setTitle("Error")
//                        .setMessage("Gửi yêu cầu thất bại")
//                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(Test_Create_Request_Activity.this,"OKOK"+which,Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(Test_Create_Request_Activity.this,"OKOK"+which,Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();



//                FragmentManager fragmentManager = getSupportFragmentManager();
//                // Kiểm tra nếu đã tồn tại instance của BottomSheetDialogOrderFilterFragment
//                BottomSheetDialogOrderFilterFragment existingFragment =
//                        (BottomSheetDialogOrderFilterFragment) fragmentManager.findFragmentByTag("BottomSheetOrderFilter");
//
//                if (existingFragment == null) {
//                    // Nếu chưa có thì tạo mới và hiển thị
//                    bs_DialogFragment = new BottomSheetDialogOrderFilterFragment(Test_Create_Request_Activity.this);
//                    bs_DialogFragment.show(fragmentManager, "BottomSheetOrderFilter");
//                } else {
//                    // Nếu đã tồn tại, dùng lại instance đó
//                    existingFragment.show(fragmentManager, "BottomSheetOrderFilter");
//                }
            }
        });
    }
}