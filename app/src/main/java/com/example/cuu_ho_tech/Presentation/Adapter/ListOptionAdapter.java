package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.OptionViewHolder;
import com.example.cuu_ho_tech.R;
import com.example.cuu_ho_tech.Utils.ClickListener;
import com.example.cuu_ho_tech.databinding.ItemLocationBinding;
import com.example.cuu_ho_tech.databinding.ItemOptionBinding;

import java.util.List;

public class ListOptionAdapter extends RecyclerView.Adapter<OptionViewHolder>{
    private List<String> list_option;
    private LayoutInflater minflater;
    private Context context;
    private ClickListener clickListener;
    private int selectedPosition = RecyclerView.NO_POSITION;
    public ListOptionAdapter(Context context, List<String> list_option, ClickListener clickListener) {
        this.list_option = list_option;
        this.clickListener = clickListener;
        this.minflater = LayoutInflater.from(context);
    }

    public void setData(List<String> list_option) {
        this.list_option = list_option;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemOptionBinding binding = ItemOptionBinding.inflate(inflater, parent, false);

        return new OptionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        String data = list_option.get(position);
        int p = position;

        // Kiểm tra xem item có đang được chọn không
        if (position == selectedPosition) {
            holder.binding.radio.setChecked(true);
        } else {
            holder.binding.radio.setChecked(false);
        }

        holder.updateUI(data);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cập nhật selectedPosition và gọi notifyDataSetChanged để cập nhật giao diện
                int previousSelectedPosition = selectedPosition;
                selectedPosition = p;

                notifyItemChanged(previousSelectedPosition); // Làm mới item trước đó
                notifyItemChanged(selectedPosition); // Làm mới item hiện tại
                Log.d("ITEM", data);

                clickListener.clickItem(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_option.size();
    }
}
