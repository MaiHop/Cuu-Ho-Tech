package com.example.cuu_ho_tech.Utils;

public interface ClickListener {
    void clickItem(String tech);
    interface OnClickListItemListener {
        void onClick(String data, int position);
    }
    interface OnClickListTechListener {
        void onClick(String tech);
    }
}
