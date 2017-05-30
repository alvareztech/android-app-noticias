package tech.alvarez.noticias.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import tech.alvarez.noticias.R;
import tech.alvarez.noticias.models.Noticia;

/**
 * Created by Daniel Alvarez on 15/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    private ArrayList<Noticia> dataset;
    private Context context;

    public NoticiasAdapter(Context context) {
        this.dataset = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noticia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Noticia n = dataset.get(position);
        holder.tituloTextView.setText(n.getTitulo());
        holder.descripcionTextView.setText(n.getDescripcion());

        Glide.with(context).load(n.getUrlFoto())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tituloTextView;
        private TextView descripcionTextView;
        private ImageView fotoImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            tituloTextView = (TextView) itemView.findViewById(R.id.tituloTextView);
            descripcionTextView = (TextView) itemView.findViewById(R.id.descripcionTextView);
            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
        }
    }

    public void adicionar(Noticia n) {
        dataset.add(n);
        notifyDataSetChanged();
    }
}