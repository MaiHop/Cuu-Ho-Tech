package com.example.cuu_ho_tech.Presentation.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.databinding.ItemReceivedMessageBinding;
import com.example.cuu_ho_tech.databinding.ItemSendMessagerBinding;

import java.util.List;

public class DetailMessageViewHolder  extends  RecyclerView.ViewHolder{
    ItemSendMessagerBinding bindingSend;
    ItemReceivedMessageBinding bindingReceived;
    public DetailMessageViewHolder(ItemSendMessagerBinding bindingSend) {
        super(bindingSend.getRoot());
        this.bindingSend = bindingSend;
    }

    public DetailMessageViewHolder(ItemReceivedMessageBinding bindingReceived) {
        super(bindingReceived.getRoot());
        this.bindingReceived = bindingReceived;
    }

    public void bindSend(List<String> data) {
        bindingSend.tvSendMessager.setText(data.get(0));
        bindingSend.tvTimeSendMessager.setText(data.get(1));
        bindingSend.dateSendMessager.setText(data.get(2));
        bindingSend.tvIsSeenMessager.setText(Integer.parseInt(data.get(3)) != 0 ? "Đã xem" : "Đã gửi");
    }

    public void bindReceived(List<String> data) {
        bindingReceived.tvReceivedMessageText.setText(data.get(0));
        bindingReceived.tvTimeReceivedMessager.setText(data.get(1));
        bindingReceived.dateReceivedMessager.setText(data.get(2));
        bindingReceived.tvIsSeenMessager.setText(Integer.parseInt(data.get(3)) != 0 ? "Đã xem" : "Đã gửi");
    }
}