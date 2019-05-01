package com.zc.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public abstract class MailSession implements EmailConstant {

    protected Session session;

    protected String srcEmail;

    protected String authCode;

    protected MailSession(String srcEmail, String authCode) {
        this.srcEmail = srcEmail;
        this.authCode = authCode;

        createSession();
    }

    protected abstract void doCreateSession(Properties properties);

    private void createSession() {
        Properties properties = System.getProperties();

        doCreateSession(properties);
        properties.setProperty(MAIL_AUTH, "true");

        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(srcEmail, authCode);
            }
        });
    }

    public Session getSession() {
        return session;
    }

    public String getSrcEmail() {
        return srcEmail;
    }

    @Override
    public String toString() {
        return "MailSession{" +
                "session=" + session +
                ", srcEmail='" + srcEmail + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }

}
