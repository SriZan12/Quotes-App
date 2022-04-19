package com.srijan.quotes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance{

    private static Retrofit retrofit;
    private static String BaseUrl = "https://type.fit/api/";

     public static Retrofit getRetrofit() {
         if(retrofit == null){
             retrofit = new Retrofit.Builder()
                     .baseUrl(BaseUrl)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }
        return retrofit;
    }
}
