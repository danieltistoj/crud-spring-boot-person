package com.api.crud.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomResponse {
    private int status;
    private Object message;
    private String url;
    private String date;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void TimeDate(){
        LocalDateTime currentDateTime= LocalDateTime.now();
        String currentTimeDateFormatted = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.date = currentTimeDateFormatted;
    }
}
