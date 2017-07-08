package com.gzql.mlqy.qule.adapter;

import android.content.Context;
import android.widget.TextView;

import com.gzql.mlqy.qule.R;
import com.gzql.mlqy.qule.utils.ListBaseAdapter;
import com.gzql.mlqy.qule.utils.SuperViewHolder;

/**
 * ============================
 * 作者： 吴浩
 * 时间： 2017/7/7.
 * 描述： 这个空的视图没有实现任何功能的
 * =============================
 */

public class EmptyDataAdapter extends ListBaseAdapter<String> {
    public EmptyDataAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_empty;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        TextView tv_name = holder.getView(R.id.tv_name);
        tv_name.setText(mDataList.get(position));
    }
}
