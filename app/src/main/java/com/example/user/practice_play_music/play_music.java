package com.example.user.practice_play_music;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

/**
 * Created by User on 2016/8/16.
 */
public class play_music extends Activity {

    private Context context;
    private final Handler handler = new Handler();//looper
    Button start;
    Button pause;
    Button reset;
    SeekBar seekBar;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_music);
        context=this;

        ini_music();
        ini_button();
    }

    public void ini_music(){//初始化音樂物件
        mp = MediaPlayer.create(context, R.raw.first);
    }
    public void ini_button(){//初始化所有按鈕
        start =(Button)findViewById(R.id.button4);
        pause =(Button)findViewById(R.id.button5);
        reset =(Button)findViewById(R.id.button6);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(mp.getDuration());
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                seekChange(view);
                return false;
            }
        });


        start.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    mp.start();
                    startPlayProgressUpdater();
                }
                catch(IllegalStateException e){
                    mp.pause();
                }
            }
        });
        pause.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                mp.pause();
                seekBar.setProgress(mp.getCurrentPosition());
            }
        });
        reset.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                mp.reset();
                mp= MediaPlayer.create(context, R.raw.first);

                seekBar.setProgress(0);
            }
        });
    }
    //以播放進度改變seekbar目前的位置
    public void startPlayProgressUpdater() {
        seekBar.setProgress(mp.getCurrentPosition());

        if (mp.isPlaying()){
            Runnable notification = new Runnable() {
                public void run() {
                    startPlayProgressUpdater();//每一秒更新一次進度條
                }
            };
            handler.postDelayed(notification,1000);
        }
    }
    // This is event handler thumb moving event
    private void seekChange(View v){
        if(mp.isPlaying()){
            SeekBar sb = (SeekBar)v;
            mp.seekTo(sb.getProgress());
        }
    }


}
