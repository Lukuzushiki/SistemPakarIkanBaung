package com.example.sistempakarikanbaung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistempakarikanbaung.Model.GejalaPenyakitModel;
import com.example.sistempakarikanbaung.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GejalaAdapter extends RecyclerView.Adapter<GejalaAdapter.MyViewHolder> {
    Context context;
    List<GejalaPenyakitModel> gejalaPenyakit;

    public GejalaAdapter(Context c, List<GejalaPenyakitModel> p){
        context = c;
        gejalaPenyakit = p;
    }

    @NonNull
    @Override
    public GejalaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gejala_penyakit, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GejalaAdapter.MyViewHolder holder, int position) {
        holder.txt_gejala.setText(gejalaPenyakit.get(position).getGejala());
    }

    @Override
    public int getItemCount() {
        return gejalaPenyakit == null ? 0 : gejalaPenyakit.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_gejala;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_gejala = itemView.findViewById(R.id.txt_list_gejala);
        }
    }
}
