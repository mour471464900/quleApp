package com.gzql.mlqy.qule.utils;

import android.support.v4.app.DialogFragment;

/**
 * @author yanyan
 * @date 2017/6/28
 */

public interface InterfaceShowToastAndProgress {
    /**
     * 显示长时间的提示框
     *
     * @param msg 提示信息
     */
    void showLongToast(String msg);

    /**
     * 显示短时间的提示框
     *
     * @param msg 提示信息
     */
    void showShortToast(String msg);


    /**
     * 显示 DialogFragment
     * @param fragment DialogFragment 对象
     */
    void showDialogFragment(DialogFragment fragment);


    void hideDialogFragment(DialogFragment fragment);

}
