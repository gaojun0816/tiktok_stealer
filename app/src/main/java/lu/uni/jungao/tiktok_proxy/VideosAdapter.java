package lu.uni.jungao.tiktok_proxy;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoViewHolder> {
    private List<String> thumbnails;
    private List<String> videos;
    private Context parent;

    public VideosAdapter(List<String> thumbnails, List<String> videos, Context parent) {
        this.thumbnails = thumbnails;
        this.videos = videos;
        this.parent = parent;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;
        public TextView tv;
        public CardView cv;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.thumbnail_image);
            tv = itemView.findViewById(R.id.desc_text);
            cv = itemView.findViewById(R.id.video_card);
        }
    }

    @NonNull
    @Override
    public VideosAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);
        VideoViewHolder vh = new VideoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final VideosAdapter.VideoViewHolder videoViewHolder, int i) {
        Picasso.get().load(thumbnails.get(i)).into(videoViewHolder.iv);
        final String video_url = videos.get(i);
        // TODO put desc into textview
        videoViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(parent);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.video_dialog);
                dialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.copyFrom(dialog.getWindow().getAttributes());
                dialog.getWindow().setAttributes(lp);
                final VideoView videoview = dialog.findViewById(R.id.dialogVideoView);
                videoview.setVideoURI(Uri.parse(video_url));
                videoview.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return thumbnails.size();
    }
}
