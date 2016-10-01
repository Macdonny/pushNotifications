package com.hackgsu.notifications.pushnotifications;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Krpto_Mac on 9/27/2016.
 */
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Generates the InstanceID token, so this is where you retrieve the token.
     * Called if InstanceID token is updated.
     *
     */
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);

        // Send the instance ID token to app server to
        // send messages to this application instance or
        // manage subscriptions on the server side.
        registerToken(token);
    }

    /**
     * Persist token to third-party server.
     *
     * @param token
     */
    private void registerToken(String token) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        Request request = new Request.Builder()
                .url("url")
                .post(body)
                .build();

        try {
            //Response received from request
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
