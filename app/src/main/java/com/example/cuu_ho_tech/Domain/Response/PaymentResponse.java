package com.example.cuu_ho_tech.Domain.Response;

public class PaymentResponse {
    int payment_id;
    String payment_method;

    public PaymentResponse(int payment_id, String payment_method) {
        this.payment_id = payment_id;
        this.payment_method = payment_method;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
