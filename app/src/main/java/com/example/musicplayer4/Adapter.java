package com.example.musicplayer4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private ArrayList<AudioModel> musics;
    private Context context;
    private OnItemClick listener;

    public Adapter(ArrayList<AudioModel> musics, Context context) {
        this.musics = musics;
        this.context = context;
    }

    interface OnItemClick{

        void OnItemClickListener(int position);

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);

        return new Holder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        AudioModel model = musics.get(position);

        holder.name.setText(model.getMusicName());
        holder.artist.setText(model.getMusicArtist());

        Utils.getCover(context, holder.cover, model);


    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView name, artist;


        public Holder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_name);
            artist = itemView.findViewById(R.id.item_artist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.OnItemClickListener(getAdapterPosition());
                    }
                }
            });

        }
    }

    public void setOnItemClickListener(OnItemClick listener){
        this.listener=listener;

    }


}
