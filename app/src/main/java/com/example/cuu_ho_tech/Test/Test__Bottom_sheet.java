package com.example.cuu_ho_tech.Test;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cuu_ho_tech.Presentation.Adapter.TechnicianSearchAdapter;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.Utils.Method4UI;
import com.example.cuu_ho_tech.databinding.ActivityTestBottonSheetBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListLocationBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListRequestFilterBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListRequestSortBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianSearchBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianStatusFilterBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetListTechnicianWorkshopBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetRemindGuestBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetRequestCreateBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetTechinicianIsCommingBinding;
import com.example.cuu_ho_tech.databinding.BottomSheetWorkshopTechnicianInfoBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class Test__Bottom_sheet extends AppCompatActivity {
    ActivityTestBottonSheetBinding binding;
    //Bottom sheet tạo đơn cứu hộ
    BottomSheetRequestCreateBinding bottomSheetRequestCreateBinding;
    //Bottom sheet danh sách kĩ thuật viên trên mapview
    BottomSheetListTechnicianSearchBinding bottomSheetListTechnicianSearchBinding;
    //Bottom sheet sắp xếp đơn
    BottomSheetListRequestSortBinding bottomSheetListRequestSortBinding;
    //Bottom sheet lọc đơn
    BottomSheetListRequestFilterBinding bottomSheetListRequestFilterBinding;
    //Bottom sheet thợ đang tới trong map view
    BottomSheetTechinicianIsCommingBinding bottomSheetTechinicianIsCommingBinding;
    //Bottom sheet list thợ trong workshop
    BottomSheetListTechnicianWorkshopBinding bottomSheetListTechnicianWorkshopBinding;
    //Bottom sheet thông tin thợ trong map view
    BottomSheetWorkshopTechnicianInfoBinding bottomSheetWorksopTechnicianInfoBinding;
    //Bottom sheet nhắc nhở đăng nhập đăng ký
    BottomSheetRemindGuestBinding bottomSheetRemindGuestBinding;
    //Bottom sheet lọc thợ theo trạng thái
    BottomSheetListTechnicianStatusFilterBinding bottomSheetListTechnicianStatusFilterBinding;
    //Bottom sheet danh sách tỉnh thành quận huyện
    BottomSheetListLocationBinding bottomSheetListLocationBinding;
    BottomSheetBehavior bsb_reqquest_create, bsb_list_tech_search,
    bsb_tech_is_comming, bsb_list_tech_workshop, bsb_tech_info_mapview;
    BottomSheetDialog bsb_request_sort,bsb_request_filter,bsb_remind_guest,
            bsb_list_tech_status_filter,bsb_list_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBottonSheetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Bottom sheet tạo đơn cứu hộ
        bottomSheetRequestCreateBinding =BottomSheetRequestCreateBinding.inflate(getLayoutInflater());
        //Bottom sheet danh sách kĩ thuật viên trên mapview
         bottomSheetListTechnicianSearchBinding =BottomSheetListTechnicianSearchBinding.inflate(getLayoutInflater());
        //Bottom sheet sắp xếp đơn
         bottomSheetListRequestSortBinding=BottomSheetListRequestSortBinding.inflate(getLayoutInflater());
        //Bottom sheet lọc đơn
         bottomSheetListRequestFilterBinding=BottomSheetListRequestFilterBinding.inflate(getLayoutInflater());
        //Bottom sheet thợ đang tới trong map view
         bottomSheetTechinicianIsCommingBinding=BottomSheetTechinicianIsCommingBinding.inflate(getLayoutInflater());
        //Bottom sheet list thợ trong workshop
         bottomSheetListTechnicianWorkshopBinding=BottomSheetListTechnicianWorkshopBinding.inflate(getLayoutInflater());
        //Bottom sheet thông tin thợ trong map view
         bottomSheetWorksopTechnicianInfoBinding=BottomSheetWorkshopTechnicianInfoBinding.inflate(getLayoutInflater());
        //Bottom sheet nhắc nhở đăng nhập đăng ký
         bottomSheetRemindGuestBinding=BottomSheetRemindGuestBinding.inflate(getLayoutInflater());
        //Bottom sheet lọc thợ theo trạng thái
         bottomSheetListTechnicianStatusFilterBinding=BottomSheetListTechnicianStatusFilterBinding.inflate(getLayoutInflater());
        //Bottom sheet danh sách tỉnh thành quận huyện
         bottomSheetListLocationBinding=BottomSheetListLocationBinding.inflate(getLayoutInflater());


        //Bottom sheet tạo đơn cứu hộ
        bsb_reqquest_create = BottomSheetBehavior.from(binding.llRequestCreate.llRequestCreate);
        //Bottom sheet danh sách kĩ thuật viên trên mapview
        bsb_list_tech_search = BottomSheetBehavior.from(binding.llListTechnicianSearch.llListTechnicianSearch);
        List<String> list = new ArrayList<>();
        for(int i =0; i<=10;i++){
            list.add("Tech "+i);
        }
        binding.llListTechnicianSearch.rvListTechnician.setLayoutManager(new LinearLayoutManager(Test__Bottom_sheet.this));
        TechnicianSearchAdapter adapter = new TechnicianSearchAdapter(Test__Bottom_sheet.this,list, new ClickListener() {
            @Override
            public void clickItem(String tech) {
                Toast.makeText(Test__Bottom_sheet.this,tech,Toast.LENGTH_SHORT).show();
            }
        });

        binding.llListTechnicianSearch.rvListTechnician.setAdapter(adapter);
        //Bottom sheet sắp xếp đơn
        bsb_request_sort = new BottomSheetDialog(Test__Bottom_sheet.this);
        bsb_request_sort.setContentView(bottomSheetListRequestSortBinding.getRoot().getRootView());

        //Bottom sheet lọc đơn
        bsb_request_filter = new BottomSheetDialog(Test__Bottom_sheet.this);
        bsb_request_filter.setContentView(bottomSheetListRequestFilterBinding.getRoot().getRootView());
        //Bottom sheet thợ đang tới trong map view
        bsb_tech_is_comming = BottomSheetBehavior.from(binding.llTechinicianIsComing.llTechinicianIsComing);
        //Bottom sheet list thợ trong workshop
        bsb_list_tech_workshop = BottomSheetBehavior.from(binding.llListTechnicianWorkshop.llListTechnicianWorkshop);
        //Bottom sheet thông tin thợ trong map view
        bsb_tech_info_mapview = BottomSheetBehavior.from(binding.llWorkshopTechnicianInfo.llWorkshopTechnicianInfo);
        //Bottom sheet nhắc nhở đăng nhập đăng ký
        bsb_remind_guest = new BottomSheetDialog(Test__Bottom_sheet.this);
        bsb_remind_guest.setContentView(bottomSheetRemindGuestBinding.getRoot().getRootView());
        //Bottom sheet lọc thợ theo trạng thái
        bsb_list_tech_status_filter = new BottomSheetDialog(Test__Bottom_sheet.this);
        bsb_list_tech_status_filter.setContentView(bottomSheetListTechnicianStatusFilterBinding.getRoot().getRootView());
        //Bottom sheet danh sách tỉnh thành quận huyện
        bsb_list_location = new BottomSheetDialog(Test__Bottom_sheet.this);
        bsb_list_location.setContentView(bottomSheetListLocationBinding.getRoot().getRootView());
//        bsb_reqquest_create.setHideable(false);
//         //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
//        binding.llRequestCreate.llRequestCreate.post(new Runnable() {
//            @Override
//            public void run() {
//                bsb_reqquest_create.setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//        });
        Method4UI.hidehint(binding.llRequestCreate.edtRequestCreateLicensePlate,binding.llRequestCreate.tilRequestCreateLicensePlate,"Nhập biển số xe");
        Method4UI.hidehint(binding.llRequestCreate.edtRequestCreateDescription,binding.llRequestCreate.tilRequestCreateDescription,"Nhập biển số xe");

        binding.btnTestRequestCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bsb_reqquest_create.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bsb_reqquest_create.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    bsb_reqquest_create.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        binding.btnTestListTechinicianSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.btnTestListRequestSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bsb_request_sort.isShowing()){
                    bsb_request_sort.dismiss();
                }else {
                    bsb_request_sort.show();
                }

            }
        });

        binding.btnTestListRequestFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bsb_request_filter.isShowing()){
                    bsb_request_filter.dismiss();
                }else {
                    bsb_request_filter.show();
                }
            }
        });

        binding.btnTestTechinicianIsComming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bsb_tech_is_comming.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bsb_tech_is_comming.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    bsb_tech_is_comming.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        binding.btnTestListTechinicianWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bsb_list_tech_workshop.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bsb_list_tech_workshop.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    bsb_list_tech_workshop.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        binding.btnTestTechnicianInfoWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bsb_tech_info_mapview.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bsb_tech_info_mapview.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    bsb_tech_info_mapview.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        binding.btnTestRemindGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bsb_remind_guest.isShowing()){
                    bsb_remind_guest.dismiss();
                }else {
                    bsb_remind_guest.show();
                }
            }
        });

        binding.btnTestListTechinicianStatusFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bsb_list_tech_status_filter.isShowing()){
                    bsb_list_tech_status_filter.dismiss();
                }else {
                    bsb_list_tech_status_filter.show();
                }
            }
        });

        binding.btnTestListLocationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bsb_list_location.isShowing()){
                    bsb_list_location.dismiss();
                }else {
                    bsb_list_location.show();
                }
            }
        });
    }

}