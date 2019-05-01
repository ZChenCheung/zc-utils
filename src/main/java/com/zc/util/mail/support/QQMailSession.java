package com.zc.util.mail.support;

import com.sun.mail.util.MailSSLSocketFactory;
import com.zc.util.mail.MailSession;
import com.zc.util.mail.EmailConstant;

import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author 曾晨
 * @date 2019/5/1
 */
public class QQMailSession extends MailSession {

    public QQMailSession(String srcEmail, String authCode) {
        super(srcEmail, authCode);
    }

    @Override
    protected void doCreateSession(Properties properties) {
        properties.setProperty(MAIL_HOST, EmailConstant.HOST_QQ);

        try {
            MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
            mailSSLSocketFactory.setTrustAllHosts(true);
            properties.put(MAIL_SSL_ENABLE, "true");
            properties.put(MAIL_SSL_SOCKET_FACTORY, mailSSLSocketFactory);

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }

}
