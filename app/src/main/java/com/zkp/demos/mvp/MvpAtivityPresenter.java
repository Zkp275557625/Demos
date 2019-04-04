package com.zkp.demos.mvp;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.mvp
 * @time: 2019/4/4 17:09
 * @description:
 */
public class MvpAtivityPresenter implements MvpActivityContract.Presenter {

    private MvpActivityModel mModel;
    private MvpActivityContract.View mView;

    public MvpAtivityPresenter() {
        this.mModel = new MvpActivityModel();
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

        mView.showLoading();

        mModel.login(account, password);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (account.equals("zkp") && password.equals("123")) {
            mView.loginSuccess();
        } else {
            mView.loginFailed();
        }
        mView.hideLoading();
    }
}
