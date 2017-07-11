package com.gzql.mlqy.qule;

/**
 * ============================
 * 作者： 吴浩
 * 时间： 2017/7/10.
 * 描述：
 * =============================
 */

public class MailSender implements Sender {
    @Override
    public void sendMsg() {
        System.out.print("发送了一封邮件");
    }

    @Override
    public String receiveMsg() {
        return "收到了一封邮件";
    }
}
