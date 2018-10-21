package cn.dnui_dx602.dnuifood16110100602.service;


import cn.dnui_dx602.dnuifood16110100602.bean.UserBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("userLogin.do")
    Call<UserBean> doLogin(
            @Query("username") String username,
            @Query("userpass") String password);
}
