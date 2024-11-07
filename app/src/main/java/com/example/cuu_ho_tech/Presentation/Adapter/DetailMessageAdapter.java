package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.DetailMessageViewHolder;
import com.example.cuu_ho_tech.Presentation.ViewModel.SharedDetailMessageToAdapterViewModel;
import com.example.cuu_ho_tech.Utils.DeviceUtils;
import com.example.cuu_ho_tech.databinding.ItemReceivedMessageBinding;
import com.example.cuu_ho_tech.databinding.ItemSendMessagerBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;
    private List<List<String>> data;
    private Context context;
    private RecyclerView recyclerView;
    SharedDetailMessageToAdapterViewModel sharedDetailMessageViewModel;

    public DetailMessageAdapter(Context context, List<List<String>> data, RecyclerView recyclerView) {
        this.data = data;
        this.context = context;
        this.recyclerView = recyclerView;
        initialize();
    }

    private void initialize() {
        sharedDetailMessageViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(SharedDetailMessageToAdapterViewModel.class);
        sharedDetailMessageViewModel.receiveMessage().observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String text) {
                Date now = new Date();
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String currentTime = timeFormat.format(now);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'thg' MM yyyy", Locale.getDefault());
                String currentDate = dateFormat.format(now);
                data.add(new ArrayList<>(Arrays.asList(text, currentTime, currentDate, "1", "1")));
                notifyItemInserted(data.size() - 1);
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        String currentTime = timeFormat.format(now);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'thg' MM yyyy", Locale.getDefault());
                        String currentDate = dateFormat.format(now);
                        data.add(new ArrayList<>(Arrays.asList("rep test", currentTime, currentDate, "1", "2")));
                        notifyItemInserted(data.size() - 1);
                        DeviceUtils.playNotificationSound(context);
                    }
                };
                handler.postDelayed(runnable, 5000);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        List<String> item = data.get(position);
        if (Integer.parseInt(item.get(4)) != 2){
            return VIEW_TYPE_SENT;
        }else {
            return VIEW_TYPE_RECEIVED;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_SENT) {
            ItemSendMessagerBinding binding = ItemSendMessagerBinding.inflate(inflater, parent, false);
            return new DetailMessageViewHolder(binding);
        } else {
            ItemReceivedMessageBinding binding = ItemReceivedMessageBinding.inflate(inflater, parent, false);
            return new DetailMessageViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        List<String> message = data.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_SENT) {
            ((DetailMessageViewHolder) holder).bindSend(message);
        } else {
            ((DetailMessageViewHolder) holder).bindReceived(message);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
