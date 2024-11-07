package com.example.cuu_ho_tech.Domain.Response;

public class MessagerResponse {
    int messager_id, sender_id, receiver_id, request_id;
    String created_at, updated_at;

    public MessagerResponse(int messager_id, int sender_id, int receiver_id, int request_id, String created_at, String updated_at) {
        this.messager_id = messager_id;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.request_id = request_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getMessager_id() {
        return messager_id;
    }

    public void setMessager_id(int messager_id) {
        this.messager_id = messager_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
