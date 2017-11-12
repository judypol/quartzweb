package com.judysen.quartz.util;

import okhttp3.*;

/**
 * Created by Administrator on 2017/11/11 0011.
 */
public class OkHttpUtil {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    static OkHttpClient client=new OkHttpClient();

    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {
        Request request=new Request.Builder().url(url).build();

        Response response=client.newCall(request).execute();
        return response.body().string();
    }

    /**
     *
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String doPost(String url,String json) throws Exception{
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
