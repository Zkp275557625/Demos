package com.zkp.demos.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.coder.zzq.smartshow.toast.SmartToast;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.zkp.demos.App;
import com.zkp.demos.R;
import com.zkp.demos.modules.excel.ExcelActivity;
import com.zkp.demos.modules.mvp.MvpActivity;
import com.zkp.demos.modules.rader.RadarActivity;
import com.zkp.demos.permission.RuntimeRationale;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zkp
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SETTING = 1;
    private ListView mListView;

    private List<String> mTitles = new ArrayList<>();
    private MainListViewAdapter mAdapter;

    private String[] mPermissions = {
            //读取外部存储权限
            Permission.READ_EXTERNAL_STORAGE,
            //写入外部存储权限
            Permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        App.getApplication().initUnCaughtHandler();
//        App.getApplication().addActivity(this);

        requestPermission(mPermissions);

        initUI();
    }

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        SmartToast.show("获取权限成功");
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        SmartToast.show("获取权限失败");
                        if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, permissions)) {
                            showSettingDialog(MainActivity.this, permissions);
                        }
                    }
                })
                .start();
    }

    private void initUI() {
        mListView = findViewById(R.id.listView);
        mTitles.add("团队数据");
        mTitles.add("mvp架构");
        mTitles.add("雷达图");
        mAdapter = new MainListViewAdapter(this, mTitles);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mAdapter.getItem(position)) {
                    case "团队数据":
                        startActivity(new Intent(MainActivity.this, ExcelActivity.class));
                        break;
                    case "mvp架构":
                        startActivity(new Intent(MainActivity.this, MvpActivity.class));
                        break;
                    case "雷达图":
                        startActivity(new Intent(MainActivity.this, RadarActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });


    }

    public void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed,
                TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context).setCancelable(false)
                .setTitle("设置")
                .setMessage(message)
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermission();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private void setPermission() {
        AndPermission.with(this).runtime().setting().start(REQUEST_CODE_SETTING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SETTING:
                SmartToast.show("获取权限成功");
                break;
            default:
                break;
        }
    }
}
