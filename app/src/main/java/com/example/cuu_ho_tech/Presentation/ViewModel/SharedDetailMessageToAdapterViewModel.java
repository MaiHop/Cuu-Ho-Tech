package com.example.cuu_ho_tech.Presentation.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedDetailMessageToAdapterViewModel extends ViewModel {
    private final MutableLiveData<String> selected = new MutableLiveData<String>();

    public void sendMessage(String item) {
        selected.setValue(item);
    }

    public LiveData<String> receiveMessage() {
        return selected;
    }
}