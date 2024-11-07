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

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();

                RadioButton radioButton = new RadioButton(Test_Create_Request_Activity.this);
                radioButton.setId(random.nextInt());
                RadioGroup.LayoutParams childParam1 = new RadioGroup.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
                childParam1.setMarginEnd(2);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setLayoutParams(childParam1);
                radioButton.setBackground(null);
                radioButton.setText("Test");
                radioButton.setTextColor(ContextCompat.getColor(Test_Create_Request_Activity.this, R.color.neutral_main));
                radioButton.setBackgroundColor(ContextCompat.getColor(Test_Create_Request_Activity.this, R.color.gray_02_100));
                radioButton.setButtonDrawable(null);
                binding.rgListRequestStatusFilter.addView(radioButton, childParam1);

            }
        });

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
                BottomSheetDialogCustomFragment bs_DialogFragment = new BottomSheetDialogCustomFragment(Test_Create_Request_Activity.this)
                        .setTitle("Test")
                        .setMessage("Test")
                        .setNegativeButton("Button 1", new BottomSheetInterface.OnClickListener() {
                            @Override
                            public void onClick(BottomSheetInterface bottomsheet) {
                                bottomsheet.dismiss();
                            }
                        })
                        .setPositiveButton("Button 2", new BottomSheetInterface.OnClickListener() {
                            @Override
                            public void onClick(BottomSheetInterface bottomsheet) {
                                Toast.makeText(Test_Create_Request_Activity.this,"OKOK",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setListOption(list, new BottomSheetInterface.OnClickListListener() {
                            @Override
                            public void onClick(BottomSheetInterface bottomsheet, String data) {
                                Toast.makeText(Test_Create_Request_Activity.this,data,Toast.LENGTH_SHORT).show();
//                                bottomsheet.dismiss();
                            }
                        });

                bs_DialogFragment.show(getSupportFragmentManager(), bs_DialogFragment.getTag());
            }
        });
    }
}