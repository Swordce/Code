package com.cxmedia.goods.MVP.api;

import com.cxmedia.goods.App;
import com.cxmedia.goods.utils.RetrofitFactory;

import retrofit2.Retrofit;

public class ApiService {
    private static Api api;

    public static Api getApi() {
        if (api == null) {
            Retrofit retrofit = RetrofitFactory.getRetrofit();
            api = retrofit.create(Api.class);
        }
        return api;
    }

}
