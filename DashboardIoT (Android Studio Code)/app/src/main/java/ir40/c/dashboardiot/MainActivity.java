package ir40.c.dashboardiot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private TextView nilai_cahaya;
    private TextView nilai_suhu;

    VideoView videoView;

    private Firebase mRef;

    int CCTVNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_cctv_1, bt_cctv_2, bt_cctv_3, bt_cctv_4, btnTitle, btnSubtitle;
        final TextView tv_active_cctv_num, Title, Subtitle;

        btnTitle = findViewById(R.id.bt_set_title);
        btnSubtitle = findViewById(R.id.bt_set_subtitle);
        Title = findViewById(R.id.edt_set_title);
        Subtitle = findViewById(R.id.edt_set_subtitle);

        bt_cctv_1 = findViewById(R.id.bt_cctv_1);
        bt_cctv_2 = findViewById(R.id.bt_cctv_2);
        bt_cctv_3 = findViewById(R.id.bt_cctv_3);
        bt_cctv_4 = findViewById(R.id.bt_cctv_4);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();

        btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Text = Title.getText().toString();
                if (Text.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Masukkan data terlebih dahulu !", Toast.LENGTH_SHORT).show();
                }
                else {
                    String input = Title.getText().toString();
                    reference.child("title").setValue(input);
                    Title.setText("");
                    Toast.makeText(getApplicationContext(), "Tersimpan !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSubtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Text = Subtitle.getText().toString();
                if (Text.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Masukkan data terlebih dahulu !", Toast.LENGTH_SHORT).show();
                }
                else {
                    String input = Subtitle.getText().toString();
                    reference.child("subtitle").setValue(input);
                    Subtitle.setText("");
                    Toast.makeText(getApplicationContext(), "Tersimpan !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        videoView = findViewById(R.id.videoView);

        tv_active_cctv_num = findViewById(R.id.tv_active_cctv_num);
        if(tv_active_cctv_num != null) tv_active_cctv_num.setText("CCTV "+String.valueOf(CCTVNum));

        if(bt_cctv_1 != null) bt_cctv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CCTVNum = 1;
                if(tv_active_cctv_num != null) tv_active_cctv_num.setText("CCTV "+String.valueOf(CCTVNum));

                Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hosea-iot.appspot.com/o/videoclip20210112_152819.384.mp4?alt=media&token=d24590f7-ef48-4fec-a662-ae63a88bc5a7");
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });

        if(bt_cctv_2 != null) bt_cctv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CCTVNum = 2;
                if(tv_active_cctv_num != null) tv_active_cctv_num.setText("CCTV "+String.valueOf(CCTVNum));

                Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hosea-iot.appspot.com/o/video20210112_152817.384.mp4?alt=media&token=ef63e958-b22d-42b1-8dc9-00d0674bde4e");
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });

        if(bt_cctv_3 != null) bt_cctv_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CCTVNum = 3;
                if(tv_active_cctv_num != null) tv_active_cctv_num.setText("CCTV "+String.valueOf(CCTVNum));

                Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hosea-iot.appspot.com/o/video20210112_152734.384.mp4?alt=media&token=2707dbef-f2a3-4b04-ac16-e9295ca0009b");
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });

        if(bt_cctv_4 != null) bt_cctv_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CCTVNum = 4;
                if(tv_active_cctv_num != null) tv_active_cctv_num.setText("CCTV "+String.valueOf(CCTVNum));

                Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hosea-iot.appspot.com/o/20000731-174637.384.mp4?alt=media&token=6136de2e-2c33-4e4a-8ba3-c1d046132bb1");
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });

        nilai_cahaya = (TextView)findViewById(R.id.nilai_cahaya);
        mRef = new Firebase("https://dashboard-iot-cd3ab-default-rtdb.firebaseio.com/value");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                nilai_cahaya.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        nilai_suhu = (TextView)findViewById(R.id.nilai_suhu);
        mRef = new Firebase("https://dashboard-iot-cd3ab-default-rtdb.firebaseio.com/value2");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value2 = dataSnapshot.getValue(String.class);
                nilai_suhu.setText(value2);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}