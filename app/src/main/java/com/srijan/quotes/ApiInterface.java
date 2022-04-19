package com.srijan.quotes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("quotes")
   Call <List<QuoteList>> getList();
}
