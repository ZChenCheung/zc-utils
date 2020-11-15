package com.zc.util.mail.support;

import com.zc.util.mail.MailSession;
import com.zc.util.mail.EmailConstant;

import java.util.Properties;

public class WYMailSession extends MailSession {

    public WYMailSession(String srcEmail, String authCode) {
        super(srcEmail, authCode);
    }

    @Override
    protected void doCreateSession(Properties properties) {
        properties.setProperty(MAIL_HOST, EmailConstant.HOST_163);
    }

}
