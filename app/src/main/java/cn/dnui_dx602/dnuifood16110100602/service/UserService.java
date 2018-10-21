package cn.dnui_dx602.dnuifood16110100602.service;

import android.telecom.Call;

import cn.dnui_dx602.dnuifood16110100602.bean.UserBean;

public interface UserService {
    @GET("userLogin.do")
    Call<UserBean> doLogin(
            @Query("username") String username,
            @Query("userpass") String password);
}
