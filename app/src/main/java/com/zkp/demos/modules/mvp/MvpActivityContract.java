package com.zkp.demos.modules.mvp;

import com.zkp.demos.beans.LoginBean;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.modules.mvp
 * @time: 2019/4/4 17:12
 * @description: 契约类，将接口全部写到这里，减少项目中的类的数量
 */
public interface MvpActivityContract {

    interface View {

        void showLoading();

        void hideLoading();

        void loginSuccess(LoginBean data);

        void loginFailed(String errMsg);

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
