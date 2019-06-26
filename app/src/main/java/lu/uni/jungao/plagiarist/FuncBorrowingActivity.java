package lu.uni.jungao.plagiarist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class FuncBorrowingActivity extends AppCompatActivity {
    private final int PICK_IMAGE = 1;
    private ImageView iv;
    private Uri imgUri;
    private Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func_borrowing);
        iv = findViewById(R.id.image_view);
    }

    public void onLoadImg(View v) {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_IMAGE:
                    imgUri = data.getData();
                    try {
                        img = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
                        iv.setImageBitmap(img);
                    } catch (IOException e) {
                        Log.e("Plagiarist/FuncBw:", "Generate bitmap image failed", e);
                    }
            }
        }
    }

    public void onSpotFace(View v) {

    }
}
