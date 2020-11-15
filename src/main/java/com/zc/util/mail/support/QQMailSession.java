package com.zc.util.mail.support;

import com.sun.mail.util.MailSSLSocketFactory;
import com.zc.util.logger.Logger;
import com.zc.util.logger.LoggerFactory;
import com.zc.util.mail.MailSession;
import com.zc.util.mail.EmailConstant;

import java.security.GeneralSecurityException;
import java.util.Properties;

public class QQMailSession extends MailSession {

    private static final Logger LOGGER = LoggerFactory.getLogger(QQMailSession.class);

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

            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("QQMailSession.doCreateSession error, case:{}", e.getMessage(), e);
            }
        }

    }

}
