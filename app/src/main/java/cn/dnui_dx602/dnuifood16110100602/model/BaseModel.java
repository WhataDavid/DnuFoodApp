package cn.dnui_dx602.dnuifood16110100602.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BaseModel {
    public Retrofit retrofit;
    private static String BaseUrl="http://172.24.10.175:8080/foodService/";
    public BaseModel(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public BaseModel(String type){
        if(type!="")
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

    }
}
