package com.gzql.mlqy.qule;

public class DesignDemo {

    public static void main(String[] args) {
        MailSenderFactory factory = new MailSenderFactory();
        MailSender send = factory.send();
        send.sendMsg();
        send.receiveMsg();
    }
}
