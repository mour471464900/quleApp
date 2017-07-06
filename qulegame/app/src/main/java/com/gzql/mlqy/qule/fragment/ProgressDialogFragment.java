package com.gzql.mlqy.qule.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;


import com.gzql.mlqy.qule.R;


/**
 * @author yanyan
 * @date 2017/7/4
 * 等待对话框
 */

public class ProgressDialogFragment extends DialogFragment {
    private TextView msgTV;
    private String msg;

    public static ProgressDialogFragment newInstance(String msg) {
        Bundle bundle=new Bundle();
        bundle.putString("msg",msg);
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(ProgressDialogFragment.STYLE_NORMAL,R.style.selectorDialog);
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_progress, container);

        msgTV = (TextView) view.findViewById(R.id.login_tv);
        msg = getArguments().getString("msg", "");
        msgTV.setText(msg);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        return view;
    }


}
