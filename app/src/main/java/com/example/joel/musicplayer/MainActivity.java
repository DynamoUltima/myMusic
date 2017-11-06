package com.example.joel.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<SongInfo> _songs = new ArrayList<>();
    RecyclerView recyclerView;
    SeekBar seekBar;
    SongAdapter songAdapter;
    MediaPlayer mediaPlayer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =(RecyclerView)findViewById(R.id.rvItem);
        seekBar =(SeekBar)findViewById(R.id.seekBar);

        SongInfo s = new SongInfo("5 mins","Pastor Oti","https://podcast.pastoroti.org/podcast-download/592/6th-november-2017-run-to-obtain-5-mins-with-pastor-oti-love-economy.mp3?ref=download");
        _songs.add(s);

        songAdapter = new SongAdapter(this,_songs);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songAdapter);


        songAdapter.setOnItemClickListener(new SongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Button b, View v, SongInfo obj, int position) {
               try {
                   if (b.getText().toString().equals("Stop")){
                       b.setText("Play");
                       mediaPlayer.stop();
                       mediaPlayer.reset();
                       mediaPlayer.release();
                       mediaPlayer = null;
                   }else {

                       mediaPlayer = new MediaPlayer();
                       mediaPlayer.setDataSource(obj.getSongUrl());
                       mediaPlayer.prepareAsync();
                       mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                           @Override
                           public void onPrepared(MediaPlayer mp) {
                               mp.start();
                               b.setText("Stop");
                           }
                       });
                   }
               }catch (IOException e){
               }
            }
        });

        mediaPlayer = new MediaPlayer();


    }
}
