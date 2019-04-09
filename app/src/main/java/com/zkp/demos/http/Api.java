package com.zkp.demos.http;

import com.zkp.demos.beans.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author: zkp
 * @project: Demos
 * @package: com.zkp.demos.main
 * @time: 2019/4/9 10:58
 * @description:
 */
public interface Api {
    /**
     * 登录
     *
     * @param account  账号
     * @param password 密码
     */
    @POST("/user/login")
    @FormUrlEncoded
    Observable<LoginBean> login(@Field("username") String account,
                                @Field("password") String password);

}
