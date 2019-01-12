package com.iu.myapplication.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectServer implements Serializable {

    private final static String serverURL = "http://13.124.249.254/";

    public interface  JsonResponseHandler {
        void onResponse(JSONObject json);
    }

    public static void  postRequestSignin(Context context,
                                         String user_id,
                                          String password,
                                         final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("user_id", user_id)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(serverURL + "auth")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("서버연결실패", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String body = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    if (handler != null) {
                        handler.onResponse(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



    }
}
