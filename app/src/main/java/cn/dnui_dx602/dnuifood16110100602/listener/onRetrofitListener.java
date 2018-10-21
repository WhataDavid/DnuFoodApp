package cn.dnui_dx602.dnuifood16110100602.listener;

public interface onRetrofitListener<T> {
    void onSuccess(T object);
    void onFalure(String msg);

}
