//package com.example.cuu_ho.Presentation.Fragment;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import com.example.cuu_ho.Presentation.Adapter.TechnicianSearchAdapter;
//import com.example.cuu_ho.Utils.ClickListener;
//import com.example.cuu_ho.Utils.Method4UI;
//import com.example.cuu_ho.databinding.BottomSheetListTechnicianSearchBinding;
//import com.google.android.material.bottomsheet.BottomSheetBehavior;
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//
//import java.util.List;
//
//public class ListTechnicianSearchFragmentTEST extends BottomSheetBehavior {
//    private List<String> list;
//    private ClickListener clickListener;
//    private Context context;
//    private String name;
//    ListTechnicianSearchInterface listTechnicianSearchInterface;
//
//    public ListTechnicianSearchFragmentTEST(Context context, List<String> list, ClickListener clickListener) {
//        this.list = list;
//        this.clickListener = clickListener;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
//        BottomSheetListTechnicianSearchBinding binding = BottomSheetListTechnicianSearchBinding.inflate(getLayoutInflater());
//        bottomSheetDialog.setContentView(binding.getRoot().getRootView());
//
//        //Sử dụng post để đảm bảo layout hoàn tất trước khi thiết lập trạng thái
//        binding.llListTechnicianSearch.post(new Runnable() {
//            @Override
//            public void run() {
//                bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//        });
//        Method4UI.hidehint(binding.edtListTechnicianSearch, binding.tilListTechnicianSearch,"Nhập tên");
//
//        TechnicianSearchAdapter adapter = new TechnicianSearchAdapter(context,list, new ClickListener() {
//            @Override
//            public void clickItem(String tech) {
//                name = tech;
//                clickListener.clickItem(name);
//            }
//        });
//        binding.rvListTechnician.setLayoutManager(new LinearLayoutManager(context));
//        binding.rvListTechnician.setAdapter(adapter);
//        binding.btnListTechnicianConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (name!=null){
//                    listTechnicianSearchInterface.getTechnician(name);
//                    bottomSheetDialog.dismiss();
//                }
//            }
//        });
//        return bottomSheetDialog;
//    }
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        listTechnicianSearchInterface=  (ListTechnicianSearchInterface) context;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//    }
//
//    public interface ListTechnicianSearchInterface{
//        void getTechnician(String tech);
//    }
//}
