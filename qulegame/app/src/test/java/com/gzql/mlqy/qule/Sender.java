package com.gzql.mlqy.qule;

/**
 * ============================
 * 作者： 吴浩
 * 时间： 2017/7/10.
 * 描述：
 * =============================
 */

public interface Sender {
    void sendMsg();  // 发送消息
    String  receiveMsg();  // 回传消息
}
