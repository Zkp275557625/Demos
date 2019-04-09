package com.zkp.demos.modules.mvp;

import android.content.Context;

import com.zkp.demos.beans.LoginBean;
import com.zkp.demos.http.HttpUtil;
import com.zkp.demos.http.Api;
import com.zkp.demos.utils.Constant;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.modules.mvp
 * @time: 2019/4/4 17:09
 * @description:
 */
public class MvpAtivityPresenter implements MvpActivityContract.Presenter {

    private MvpActivityContract.View mView;

    private Context mContext;

    public MvpAtivityPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void attachView(MvpActivityContract.View view) {
        if (mView == null) {
            this.mView = view;
        }
    }

    public void detachView() {
        if (mView != null) {
            this.mView = null;
        }
    }

    @Override
    public void login(String account, String password) {
        if (mView != null) {
            mView.showLoading();

            HttpUtil.request(HttpUtil.createApi(mContext, Constant.BASE_URL, Api.class).login(account, password),
                    new HttpUtil.IResponseListener<LoginBean>() {
                        @Override
                        public void onSuccess(LoginBean data) {
                            if (data.getErrorCode() == 0) {
                                mView.loginSuccess(data);
                            } else {
                                mView.loginFailed(data.getErrorMsg());
                            }
                            mView.hideLoading();
                        }

                        @Override
                        public void onFail(String errMsg) {
                            mView.loginFailed(errMsg);
                            mView.hideLoading();
                        }
                    });
        }
    }
}
