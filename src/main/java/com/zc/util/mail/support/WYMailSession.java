package com.zc.util.mail.support;

import com.zc.util.mail.MailSession;
import com.zc.util.mail.EmailConstant;

import java.util.Properties;

/**
 * @author 曾晨
 * @date 2019/5/1
 */
public class WYMailSession extends MailSession {

    public WYMailSession(String srcEmail, String authCode) {
        super(srcEmail, authCode);
    }

    @Override
    protected void doCreateSession(Properties properties) {
        properties.setProperty(MAIL_HOST, EmailConstant.HOST_163);
    }

}
