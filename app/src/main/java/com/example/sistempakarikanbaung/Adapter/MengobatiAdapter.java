package com.example.sistempakarikanbaung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistempakarikanbaung.Model.ObatModel;
import com.example.sistempakarikanbaung.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MengobatiAdapter extends RecyclerView.Adapter<MengobatiAdapter.MyViewHolder> {
    Context context;
    List<ObatModel> obatAdapters;

    public MengobatiAdapter(Context c, List<ObatModel> p){
        context = c;
        obatAdapters = p;
    }

    @NonNull
    @Override
    public MengobatiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mengobati, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MengobatiAdapter.MyViewHolder holder, int position) {
        holder.txt_mengobati.setText(obatAdapters.get(position).getObat());
    }

    @Override
    public int getItemCount() {
        return obatAdapters == null ? 0 : obatAdapters.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_mengobati;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_mengobati = itemView.findViewById(R.id.txt_list_obat);
        }
    }
}
