package com.zc.util.mail;

import com.zc.util.logger.Logger;
import com.zc.util.logger.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class MailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSender.class);

    private final Queue<MailSession> queue = new ConcurrentLinkedQueue<>();

    private final Executor executor;

    public MailSender(Executor executor) {
        this.executor = executor;
    }

    public void sendTo(MailSession mailSession, MailMessage mailMessage) {
        if (mailSession == null) {
            String msg = "MailSender sendTo(), mailSession can not null!";
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(msg);
            }
            throw new NullPointerException(msg);
        }

        if (!queue.contains(mailSession)) {
            addSender(mailSession);
        }

        executor.execute(new Runnable() {
            @Override
            public void run() {
                Message message = new MimeMessage(mailSession.getSession());
                try {
                    message.setFrom(new InternetAddress(mailSession.getSrcEmail()));
                    // 设置接收人
                    message.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(mailMessage.getTagEmail()));
                    // 设置邮件主题
                    message.setSubject(mailMessage.getSubJect());
                    // 设置邮件内容
                    message.setContent(mailMessage.getContent(), "text/html;charset=UTF-8");
                    // 发送邮件
                    Transport.send(message);

                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("MailSender [thread:" + Thread.currentThread().getName()
                                + "] send email["
                                + "from: " + mailSession.getSrcEmail()
                                + ", to: " + mailMessage.getTagEmail()
                                + ", subject: " + mailMessage.getSubJect()
                                + ", content: " + mailMessage.getContent()
                                + "]");
                    }
                } catch (MessagingException e) {
                    String msg = "MailSender send Exception, case: " + e.getMessage();

                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error(msg, e);
                    }
                    throw new IllegalStateException(msg, e);
                }

            }
        });
    }

    public void sendTo(MailMessage mailMessage) {
        if (mailMessage == null) {
            String msg = "MailSender sendTo(), mailMessage not defined!";
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(msg);
            }
            throw new NullPointerException(msg);
        }

        MailSession mailSession = queue.poll();
        queue.add(mailSession);

        sendTo(mailSession, mailMessage);
    }

    public void addSender(MailSession mailSession) {
        if (mailSession == null) {
            String msg = "MailSender addSender(), sender not defined!";
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(msg);
            }
            throw new NullPointerException(msg);
        }

        queue.add(mailSession);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("MailSender add sender:[" + mailSession + "]");
        }
    }

    public MailSession removeSender(MailSession mailSession) {
        if (queue.remove(mailSession)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("MailSender remove sender:[" + mailSession + "]");
            }
        }

        return mailSession;
    }

}
