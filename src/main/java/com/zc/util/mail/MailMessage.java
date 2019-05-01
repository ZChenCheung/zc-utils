package com.zc.util.mail;

public class MailMessage {

    private String tagEmail;

    private String subject;

    private String content;

    public MailMessage(String tagEmail, String subject, String content) {
        this.tagEmail = tagEmail;
        this.subject = subject;
        this.content = content;
    }

    public String getTagEmail() {
        return tagEmail;
    }

    public String getSubJect() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "MailMessage{" +
                "tagEmail='" + tagEmail + '\'' +
                ", subJect='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
