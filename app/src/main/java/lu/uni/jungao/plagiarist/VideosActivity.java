package lu.uni.jungao.plagiarist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class VideosActivity extends AppCompatActivity {
    private RecyclerView videoRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        Intent i = getIntent();
        List<String> videos = (List<String>) i.getSerializableExtra("videos");
        List<String> thumbnails = (List<String>) i.getSerializableExtra("thumbnails");

        videoRV = findViewById(R.id.video_list);
        videoRV.setHasFixedSize(true);
        videoRV.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        videoRV.setAdapter(new VideosAdapter(thumbnails, videos, this));
    }
}
