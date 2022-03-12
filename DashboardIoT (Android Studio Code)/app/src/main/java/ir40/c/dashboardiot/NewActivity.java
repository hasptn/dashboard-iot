package ir40.c.dashboardiot;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class NewActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        MediaController mediaController = new MediaController( this);

        videoView = findViewById(R.id.vid);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hosea-iot.appspot.com/o/CCTV%202.mp4?alt=media&token=cbf030ad-9558-44fc-aa32-ab86d8a527be");
        videoView.setVideoURI(uri);
        videoView.start();
    }
}