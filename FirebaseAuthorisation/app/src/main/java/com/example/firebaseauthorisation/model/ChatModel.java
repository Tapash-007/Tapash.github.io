package com.example.firebaseauthorisation.model;

public class ChatModel {

    String receiverId,receiverName,senderId,message,timeStamp;

    public ChatModel(String receiverId, String receiverName, String senderId, String message, String timeStamp) {
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.senderId = senderId;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
