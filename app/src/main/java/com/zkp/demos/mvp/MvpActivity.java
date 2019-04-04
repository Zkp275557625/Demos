package com.zkp.demos.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.coder.zzq.smartshow.dialog.SmartDialog;
import com.coder.zzq.smartshow.dialog.creator.type.impl.DialogCreatorFactory;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.zkp.demos.App;
import com.zkp.demos.R;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.mvp
 * @time: 2019/4/4 16:49
 * @description:
 */
public class MvpActivity extends AppCompatActivity implements MvpActivityContract.View {

    private EditText mEtAccount, mEtPassword;
    private Button mBtnLogin;

    private SmartDialog mLoadingDialog;

    private MvpAtivityPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        App.getApplication().addActivity(this);

        initUI();

        mPresenter = new MvpAtivityPresenter();
        mPresenter.attachView(this);
    }

    private void initUI() {
        mEtAccount = findViewById(R.id.etAccount);
        mEtPassword = findViewById(R.id.etPassword);
        mBtnLogin = findViewById(R.id.btnLogin);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtAccount.getText().toString().length() > 0
                        && mEtPassword.getText().toString().length() > 0) {
                    mPresenter.login(mEtAccount.getText().toString(), mEtPassword.getText().toString());
                } else {
                    SmartToast.show("账号或者密码不能为空");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = SmartDialog.newInstance(
                    DialogCreatorFactory
                            .loading()
                            .middle()
                            .message("处理中...")
            )
                    .reuse(true);
        }
        mLoadingDialog.show(this);
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = SmartDialog.newInstance(
                    DialogCreatorFactory
                            .loading()
                            .middle()
                            .message("处理中...")
            )
                    .reuse(true);
        }
        mLoadingDialog.dismiss(this);
    }

    @Override
    public void loginSuccess() {
        SmartToast.show("登录成功");
    }

    @Override
    public void loginFailed() {
        SmartToast.show("登录失败");
    }
}
