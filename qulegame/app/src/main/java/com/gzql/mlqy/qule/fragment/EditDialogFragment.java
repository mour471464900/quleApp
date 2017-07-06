package com.gzql.mlqy.qule.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.gzql.mlqy.qule.R;
import com.gzql.mlqy.qule.event.FirstEvent;
import com.gzql.mlqy.qule.utils.DataUtil;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Administrator on 2017/7/4.
 */

public class EditDialogFragment extends DialogFragment {


    private TextView text_cancel;
    private EditText ed_edit;
    private TextView text_confirm;
    private Context mContext;

    public static EditDialogFragment newInstance(Bundle bundle) {
        EditDialogFragment fragment = new EditDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       mContext=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(ProgressDialogFragment.STYLE_NORMAL, R.style.selectorDialog);

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_edit, container);

        ed_edit = (EditText) view.findViewById(R.id.ed_edit);
        text_cancel = (TextView) view.findViewById(R.id.text_cancel);
        text_confirm = (TextView) view.findViewById(R.id.text_confirm);
        initListener();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        return view;

    }

    private void initListener() {
        text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });
        text_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ed_edit.getText().toString();
                if(TextUtils.isEmpty(text)){
                    Toast.makeText(mContext, "不能为空", Toast.LENGTH_SHORT).show();
                }else {

                    DataUtil.setSharePreferences("nichen",text);
                    EventBus.getDefault().post(new FirstEvent(text));
                    dismiss();
                }
            }
        });
    }


}
