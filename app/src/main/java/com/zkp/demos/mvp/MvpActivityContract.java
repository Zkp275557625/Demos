package com.zkp.demos.mvp;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.mvp
 * @time: 2019/4/4 17:12
 * @description:
 */
public interface MvpActivityContract {

    interface Model {

        void login(String account, String password);

    }

    interface View {

        void showLoading();

        void hideLoading();

        void loginSuccess();

        void loginFailed();

    }

    interface Presenter {

        /**
         * 登录
         *
         * @param account  账号
         * @param password 密码
         */
        void login(String account, String password);

    }

}
