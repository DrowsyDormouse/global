package com.example.song.global;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Song on 2017-11-08.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        /*try{
            Thread.sleep(4000); // 대기 초 설정
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        startActivity(new Intent(this, MapsActivity.class));
        finish();
    }
}
