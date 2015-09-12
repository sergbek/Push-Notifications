package com.example.sergbek.pushnotifications.model;


public class NotificationEntity {
    private String mId;
    private String mMessage;
    private String mTitle;
    private String mSubtitle;
    private String mTickerText;

    public NotificationEntity() {
    }

    public NotificationEntity(String message, String title, String subtitle, String tickerText) {
        mMessage = message;
        mTitle = title;
        mSubtitle = subtitle;
        mTickerText = tickerText;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String subtitle) {
        mSubtitle = subtitle;
    }

    public String getTickerText() {
        return mTickerText;
    }

    public void setTickerText(String tickerText) {
        mTickerText = tickerText;
    }

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "mId='" + mId + '\'' +
                ", mMessage='" + mMessage + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mSubtitle='" + mSubtitle + '\'' +
                ", mTickerText='" + mTickerText + '\'' +
                '}';
    }
}
