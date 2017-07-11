package com.gzql.mlqy.qule;

/**
 * ============================
 * 作者： 吴浩
 * 时间： 2017/7/10.
 * 描述：
 * =============================
 */

public class MailSenderFactory  implements Produce {
    @Override
    public MailSender send() {
        return new MailSender();
    }
//    @Override
//    public MailSender send() {
//        return new MailSender();
//    }
}
