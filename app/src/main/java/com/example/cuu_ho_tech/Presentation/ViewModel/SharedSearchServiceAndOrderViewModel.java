package com.example.cuu_ho_tech.Presentation.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedSearchServiceAndOrderViewModel extends ViewModel {
    private final MutableLiveData<String> messageActFromFra = new MutableLiveData<String>();
    private final MutableLiveData<String> messageFraFromAct = new MutableLiveData<String>();

    public void setTextActFromFra(String item) {
        messageActFromFra.setValue(item);
    }

    public LiveData<String> getTextFromEditText() {
        return messageActFromFra;
    }

    public void setTextFraFromAct(String item) {
        messageFraFromAct.setValue(item);
    }

    public LiveData<String> getTextFromItem() {
        return messageFraFromAct;
    }
}
