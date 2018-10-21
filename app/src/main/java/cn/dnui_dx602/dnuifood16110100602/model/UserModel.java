package cn.dnui_dx602.dnuifood16110100602.model;

import cn.dnui_zjs008.dnuifood_iamzjs.bean.UserBean;
import cn.dnui_zjs008.dnuifood_iamzjs.iface.UserIface;
import cn.dnui_zjs008.dnuifood_iamzjs.listener.onRetrofitListener;
import cn.dnui_zjs008.dnuifood_iamzjs.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserModel extends BaseModel implements UserIface {
    private UserService service;
    @Override
    public void login(String username, String userpass,
                      final onRetrofitListener listener) {
        service =retrofit.create(UserService.class);
        Call<UserBean> call=service
                .doLogin(username,userpass);
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                if(response.body()!=null)
                    listener.onSuccess(response.body());
                else
                    listener.onFalure("解析错误！");
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                listener.onFalure(t.toString());
            }
        });

    }

    @Override
    public void register(String username, String userpass, String mobilenum, onRetrofitListener listener) {

    }
}
