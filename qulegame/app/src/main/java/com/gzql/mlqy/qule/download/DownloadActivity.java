package com.gzql.mlqy.qule.download;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gzql.mlqy.qule.R;
import com.gzql.mlqy.qule.base.Base2Activity;
import com.gzql.mlqy.qule.base.BaseRecyclerAdapter;
import com.gzql.mlqy.qule.base.DividerItemDecoration;
import com.gzql.mlqy.qule.bean.ApkModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.download.DownloadManager;
import com.lzy.okserver.download.DownloadService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadActivity extends Base2Activity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.targetFolder)
    TextView targetFolder;
    @BindView(R.id.tvCorePoolSize)
    TextView tvCorePoolSize;
    @BindView(R.id.sbCorePoolSize)
    SeekBar sbCorePoolSize;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.openManager)
    Button openManager;

    private ArrayList<ApkModel> apks;
    private DownloadManager downloadManager;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initToolBar(toolbar, true, "下载管理");

        initData();
        downloadManager = DownloadService.getDownloadManager();
        downloadManager.setTargetFolder(Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaa/");

        targetFolder.setText("下载路径: " + downloadManager.getTargetFolder());
        sbCorePoolSize.setMax(5);
        sbCorePoolSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                downloadManager.getThreadPool().setCorePoolSize(progress);
                tvCorePoolSize.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sbCorePoolSize.setProgress(3);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new MainAdapter(this);
        recyclerView.setAdapter(adapter);
        openManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DownloadManagerActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private class MainAdapter extends BaseRecyclerAdapter<ApkModel, ViewHolder> {

        public MainAdapter(Context context) {
            super(context, apks);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_download_details, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ApkModel apkModel = mDatas.get(position);
            holder.bind(apkModel);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.download)
        Button download;

        private ApkModel apkModel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ApkModel apkModel) {
            this.apkModel = apkModel;
            if (downloadManager.getDownloadInfo(apkModel.getUrl()) != null) {
                download.setText("已在队列");
                download.setEnabled(false);
            } else {
                download.setText("下载");
                download.setEnabled(true);
            }
            name.setText(apkModel.getName());
            Glide.with(getApplicationContext()).load(apkModel.getIconUrl()).error(R.mipmap.ic_launcher).into(icon);
            download.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.download) {
                if (downloadManager.getDownloadInfo(apkModel.getUrl()) != null) {
                    Toast.makeText(getApplicationContext(), "任务已经在下载列表中", Toast.LENGTH_SHORT).show();
                } else {
                    GetRequest request = OkGo.get(apkModel.getUrl())//
                            .headers("headerKey1", "headerValue1")//
                            .headers("headerKey2", "headerValue2")//
                            .params("paramKey1", "paramValue1")//
                            .params("paramKey2", "paramValue2");
                    downloadManager.addTask(apkModel.getUrl(), apkModel, request, null);
                    download.setText("已在队列");
                    download.setEnabled(false);
                }
            } else {
                Intent intent = new Intent(getApplicationContext(), DesActivity.class);
                intent.putExtra("apk", apkModel);
                startActivity(intent);
            }
        }
    }

    private void initData() {
        apks = new ArrayList<>();
        ApkModel apkInfo1 = new ApkModel();
        apkInfo1.setName("权御三国");
        apkInfo1.setIconUrl("https://www.anfeng.com/upload/tj/201706/5937ca51bebd3.png");
        apkInfo1.setUrl("http://apk4.anfan.com/1933_1452-rwu");
        apks.add(apkInfo1);
        ApkModel apkInfo2 = new ApkModel();
        apkInfo2.setName("九阴真经");
        apkInfo2.setIconUrl("https://www.anfeng.com/upload/tj/201706/5937e578608e6.png");
        apkInfo2.setUrl("http://apk4.anfan.com/1933_475-rwu");
        apks.add(apkInfo2);
        ApkModel apkInfo3 = new ApkModel();
        apkInfo3.setName("梦幻西游手游");
        apkInfo3.setIconUrl("https://www.anfeng.com/upload/tj/201706/5937cb0f24bb4.png");
        apkInfo3.setUrl("http://apk4.anfan.com/1933_370-rwu");
        apks.add(apkInfo3);
        ApkModel apkInfo4 = new ApkModel();
        apkInfo4.setName("少年三国志");
        apkInfo4.setIconUrl("https://www.anfeng.com/upload/tj/201706/5937cb261f670.png");
        apkInfo4.setUrl("http://apk4.anfan.com/1933_386-rwu");
        apks.add(apkInfo4);
//        ApkModel apkInfo6 = new ApkModel();
//        apkInfo6.setName("快的打车");
//        apkInfo6.setIconUrl("http://up.apk8.com/small1/1439955061264.png");
//        apkInfo6.setUrl("http://download.apk8.com/soft/2015/%E5%BF%AB%E7%9A%84%E6%89%93%E8%BD%A6.apk");
//        apks.add(apkInfo6);
//        ApkModel apkInfo7 = new ApkModel();
//        apkInfo7.setName("叮当快药");
//        apkInfo7.setIconUrl("http://pic3.apk8.com/small2/14315954626414886.png");
//        apkInfo7.setUrl("http://d2.apk8.com:8020/soft/dingdangkuaiyao.apk");
//        apks.add(apkInfo7);
//        ApkModel apkInfo8 = new ApkModel();
//        apkInfo8.setName("悦跑圈");
//        apkInfo8.setIconUrl("http://pic3.apk8.com/small2/14298490191525146.jpg");
//        apkInfo8.setUrl("http://d2.apk8.com:8020/soft/yuepaoquan.apk");
//        apks.add(apkInfo8);
//        ApkModel apkInfo9 = new ApkModel();
//        apkInfo9.setName("悠悠导航");
//        apkInfo9.setIconUrl("http://pic3.apk8.com/small2/14152456988840667.png");
//        apkInfo9.setUrl("http://d2.apk8.com:8020/soft/%E6%82%A0%E6%82%A0%E5%AF%BC%E8%88%AA2.3.32.1.apk");
//        apks.add(apkInfo9);
//        ApkModel apkInfo10 = new ApkModel();
//        apkInfo10.setName("虎牙直播");
//        apkInfo10.setIconUrl("http://up.apk8.com/small1/1439892235841.jpg");
//        apkInfo10.setUrl("http://download.apk8.com/down4/soft/hyzb.apk");
//        apks.add(apkInfo10);
    }
}