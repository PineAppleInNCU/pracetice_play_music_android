package com.example.user.practice_play_music;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Context context;
    Button btn1;
    Button btn2;
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        init();//initialize every button and give them actionlistener

    }





    private void init(){
        btn1=(Button)findViewById(R.id.button);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);

        btn1.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v){

                Intent intent=new Intent(context,play_music.class);
                Bundle extras = new Bundle();
                extras.putInt("music_index",1);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });



    }




}
