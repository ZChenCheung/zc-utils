package com.zc.util.mail;

import com.zc.util.mail.support.QQMailSession;
import com.zc.util.mail.support.WYMailSession;

import java.util.regex.Pattern;

public class MailUtil {

    private static final Pattern MAIL_PATTERN = Pattern.compile("^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$");

    private static final Pattern MAIL_QQ_PATTERN = Pattern.compile("^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@(qq|QQ).+[a-z]{2,}$");

    private MailUtil() {}

    public static boolean checkEmail(String email) {
        if (email == null
                || email.length() <= 5
                || !MAIL_PATTERN.matcher(email).matches()) {
            return false;
        }

        return true;
    }

    public static MailSession creatMailSession(String srcEmail, String  authCode) {
        if (!checkEmail(srcEmail)) {
            throw new IllegalArgumentException("email[" +srcEmail + "] illegal!");
        }

        if (MAIL_QQ_PATTERN.matcher(srcEmail).matches()) {
            return new QQMailSession(srcEmail, authCode);
        } else {
            return new WYMailSession(srcEmail, authCode);
        }
    }


}
