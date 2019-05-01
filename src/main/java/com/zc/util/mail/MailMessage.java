package com.zc.util.mail;

public class MailMessage {

    private String tagEmail;

    private String subJect;

    private String content;

    public MailMessage(String tagEmail, String subJect, String content) {
        this.tagEmail = tagEmail;
        this.subJect = subJect;
        this.content = content;
    }

    public String getTagEmail() {
        return tagEmail;
    }

    public String getSubJect() {
        return subJect;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "MailMessage{" +
                "tagEmail='" + tagEmail + '\'' +
                ", subJect='" + subJect + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
