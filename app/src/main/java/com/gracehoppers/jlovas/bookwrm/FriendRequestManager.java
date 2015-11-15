package com.gracehoppers.jlovas.bookwrm;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * keeps track of all friendrequests
 * @author nlovas
 */
public class FriendRequestManager {

    private static final String URL = "http://cmput301.softwareprocess.es:8080/cmput301f15t06/friendrequests/";
    private static final String TAG = "FriendRequests";
    private Gson gson;

    public FriendRequestManager() {
        gson = new Gson();
    }

    /**
     * stores a friendrequest on the server
     * @param friendrequest
     */
    public void addFriendRequest(FriendRequest friendrequest) {
        //System.out.print(URL+account.getUsername());
        HttpClient httpClient = new DefaultHttpClient();
        try {
            Log.e("friend request sender: ", friendrequest.getSender());
            HttpPost addRequest = new HttpPost(URL + friendrequest.getSender() + "-"+friendrequest.getReceiver());
            StringEntity stringEntity = new StringEntity(gson.toJson(friendrequest));


            addRequest.setEntity(stringEntity);
            addRequest.setHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(addRequest);
            String status = response.getStatusLine().toString();

            Log.i(TAG, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FriendRequest getFriendRequest(String senderusername) {
        SearchHit<FriendRequest> sr = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL + senderusername + "/"); //change this !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.print(URL + senderusername);

        HttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
        } catch (ClientProtocolException e1) {
            throw new RuntimeException(e1);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }

        Type searchHitType = new TypeToken<SearchHit<FriendRequest>>() {
        }.getType();

        try {
            sr = gson.fromJson(
                    new InputStreamReader(response.getEntity().getContent()),
                    searchHitType);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sr.getSource();
    }


}
