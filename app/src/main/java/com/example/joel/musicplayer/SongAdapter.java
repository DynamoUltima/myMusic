package com.example.joel.musicplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by joel on 11/4/2017.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    ArrayList<SongInfo> _songs;
    Context context;

    OnItemClickListener onItemClickListener;

    SongAdapter(Context context,ArrayList<SongInfo> _songs){
        this.context =context;
        this._songs =_songs;
    }
    public interface OnItemClickListener{
        void onItemClick(Button b,View v,SongInfo obj,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View myView = LayoutInflater.from(context).inflate(R.layout.row_songs,parent,false);
        return new SongHolder(myView);
    }

    @Override
    public void onBindViewHolder(final SongHolder holder, final int position) {
        final SongInfo c = _songs.get(position);
        holder.SongName.setText(c.songName);
        holder.ArtistName.setText(c.artistName);
        holder.button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(holder.button2,v,c,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return _songs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        TextView SongName,ArtistName;
        Button button2;
        public SongHolder(View itemView) {
            super(itemView);
            SongName =(TextView)itemView.findViewById(R.id.tvSongName);
            ArtistName =(TextView)itemView.findViewById(R.id.tvArtistName);
            button2 =(Button) itemView.findViewById(R.id.button2);
        }
    }
}
