package com.rodrigofrazao.domain.controllers;

public class ImageStringResponse {

    private String response;

    public ImageStringResponse(String s) {
        this.response = s;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
