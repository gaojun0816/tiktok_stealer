package lu.uni.jungao.plagiarist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TTProxyActivity extends AppCompatActivity {
    private EditText uidEdit, keywordEdit;
    private Button postLoadBtn, searchBtn;
    private RecyclerView usersRV;
    private ProgressBar loadProgress;
    private List<String> video_urls = new ArrayList<>();
    private List<String> thumb_urls = new ArrayList<>();
    private List<String> user_ids = new ArrayList<>();
    private RecyclerView.Adapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttproxy);
        uidEdit = findViewById(R.id.uid_text);
        loadProgress = findViewById(R.id.loading_progress);
        postLoadBtn = findViewById(R.id.load_post_btn);
        keywordEdit = findViewById(R.id.keyword_text);
        searchBtn = findViewById(R.id.search_user_btn);
        usersRV = findViewById(R.id.user_ids_text);
        usersRV.setHasFixedSize(true);
        usersRV.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        usersAdapter = new UsersAdapter(user_ids, uidEdit);
        usersRV.setAdapter(usersAdapter);
    }

    public void findUserIDs(View v) {
        keywordEdit.setEnabled(false);
        searchBtn.setEnabled(false);
        String keyword = keywordEdit.getText().toString().trim();
        if (keyword.equals("")) {
            keywordEdit.setEnabled(true);
            searchBtn.setEnabled(true);
            Toast.makeText(this, "Keywords missing!", Toast.LENGTH_LONG).show();
            return;
        }
        loadProgress.setVisibility(View.VISIBLE);
        String url = URLMaker.getSearchUsersURL(keyword, "0", 20);
        makeUsersRequest(url, keyword);
    }

    private void makeUsersRequest(final String url, final String keyword) {
        // Request a string response from the provided URL.
        final JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String has_more = response.getString("has_more");
                            JSONArray userList = response.getJSONArray("user_list");
                            for (int i = 0; i < userList.length(); i++) {
                                String uid = userList.getJSONObject(i)
                                        .getJSONObject("user_info").getString("uid");
                                addUserIds(uid);
                            }
//                         uncomment below to load all videos. (not working properly)
//                            if (has_more.equals("1")) {
//                                String cursor = response.getString("cursor");
//                                makeUsersRequest(URLMaker.getPostURL(keyword, cursor, 20), keyword);
//                            } else {
//                                userSearchFinish();
//                            }
                            userSearchFinish();
                        } catch (JSONException e) {
                            Log.e("MyApp", "JSON exception", e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        Log.e("MyApp", "Volley request failed", e);
                    }
                });

        // Add the request to the RequestQueue.
        RequestQSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void userSearchFinish() {
        usersAdapter.notifyDataSetChanged();
        keywordEdit.setEnabled(true);
        searchBtn.setEnabled(true);
        loadProgress.setVisibility(View.GONE);
    }

    private void addUserIds(String id) {
        user_ids.add(id);
    }

    public void loadVideo(View v) {
        uidEdit.setEnabled(false);
        postLoadBtn.setEnabled(false);
        String uid = uidEdit.getText().toString().trim();
        if (uid.equals("")) {
            uidEdit.setEnabled(true);
            postLoadBtn.setEnabled(true);
            Toast.makeText(this, "User IDs missing!", Toast.LENGTH_LONG).show();
            return;
        }
        loadProgress.setVisibility(View.VISIBLE);
        String url = URLMaker.getPostURL(uid, "0", 20);
        makePostsRequest(url, uid);
    }

    private void makePostsRequest(final String url, final String uid) {
        // Request a string response from the provided URL.
        final JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String has_more = response.getString("has_more");
                            JSONArray aweme_list = response.getJSONArray("aweme_list");
                            for (int i = 0; i < aweme_list.length(); i++) {
                                JSONObject aweme = aweme_list.getJSONObject(i);
                                String video_url = aweme.getJSONObject("video")
                                        .getJSONObject("download_addr")
                                        .getJSONArray("url_list")
                                        .getString(0);
                                String thumb_url = aweme.getJSONObject("video")
                                        .getJSONObject("origin_cover")
                                        .getJSONArray("url_list").getString(0);
                                addThumbnails(thumb_url);
                                addVideos(video_url);
                            }
                            // uncomment below to load all videos.
//                            if (has_more.equals("1")) {
//                                String max_cursor = response.getString("max_cursor");
//                                makePostsRequest(URLMaker.getPostURL(uid, max_cursor, 20), uid);
//                            } else {
//                                videoLoadFinish();
//                            }
                            videoLoadFinish();
                        } catch (JSONException e) {
                            Log.e("MyApp", "JSON exception", e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        Log.e("MyApp", "Volley request failed", e);
                    }
                });

        // Add the request to the RequestQueue.
        RequestQSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void addVideos(String video_url) {
        video_urls.add(video_url);
    }

    private void addThumbnails(String thumb_url) {
        thumb_urls.add(thumb_url);
    }

    private void videoLoadFinish() {
        uidEdit.setEnabled(true);
        postLoadBtn.setEnabled(true);
        loadProgress.setVisibility(View.GONE);
        Intent i = new Intent(this, VideosActivity.class);
        i.putExtra("thumbnails", (Serializable) thumb_urls);
        i.putExtra("videos", (Serializable) video_urls);
        startActivity(i);
    }


}
