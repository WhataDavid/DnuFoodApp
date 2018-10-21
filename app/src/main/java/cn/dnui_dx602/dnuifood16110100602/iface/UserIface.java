package cn.dnui_dx602.dnuifood16110100602.iface;


import cn.dnui_dx602.dnuifood16110100602.listener.onRetrofitListener;

public interface UserIface {
    void login(String username,
               String userpass,
               onRetrofitListener listener);

    void register(String username,
                  String userpass,
                  String mobilenum,
                  onRetrofitListener listener);
}
