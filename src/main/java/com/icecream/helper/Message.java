package com.icecream.helper;

public class Message {
    // this class is for showing custom messages in case of errors
    private String name;
    private String type;

    public Message(String name , String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
