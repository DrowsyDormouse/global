package com.example.song.global;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        TextView tv_title = (TextView)findViewById(R.id.title);
        TextView tv_text = (TextView)findViewById(R.id.text);
        ImageView iv_image = (ImageView)findViewById(R.id.image);

        Intent it = getIntent();
        String tag  = it.getStringExtra("it_tag");

        double i = it.getDoubleExtra("lat", 0);
        double j = it.getDoubleExtra("lon", 0);
        final LatLng pos = new LatLng(i, j);

        Resources res = getResources();

        int id_image = res.getIdentifier("image" + tag, "string", getPackageName());
        String image = res.getString(id_image);
        int id_img = res.getIdentifier(image, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(id_img);
        iv_image.setBackground(drawable);

        int id_title = res.getIdentifier("title"+tag, "string", getPackageName());
        String title = res.getString(id_title);
        tv_title.setText(title);

        int id_text = res.getIdentifier("text" + tag, "string", getPackageName());
        String text = res.getString(id_text);
        tv_text.setText(text);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnTableNavigation(pos);
            }
        });
    }

    public void turnTableNavigation(LatLng pos)
    {
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+pos.latitude+","+pos.longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
