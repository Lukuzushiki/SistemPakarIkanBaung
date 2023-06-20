package com.example.sistempakarikanbaung.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistempakarikanbaung.Activity.DetailListScreen;
import com.example.sistempakarikanbaung.Model.PenyakitModel;
import com.example.sistempakarikanbaung.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PenyakitAdapter extends RecyclerView.Adapter<PenyakitAdapter.MyViewHolder> {
    Context context;
    List<PenyakitModel> penyakitModels;

    public PenyakitAdapter(Context c, List<PenyakitModel> p){
        context = c;
        penyakitModels = p;
    }

    @NonNull
    @Override
    public PenyakitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_penyakit, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PenyakitAdapter.MyViewHolder holder, int position) {
        holder.txt_nama.setText(penyakitModels.get(position).getPenyakit());

        final String idPenyakit = penyakitModels.get(position).getId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, DetailListScreen.class);
                detailIntent.putExtra("id_penyakit", idPenyakit);
                context.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return penyakitModels == null ? 0 : penyakitModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_nama;
        ImageView img_penyakit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_nama = itemView.findViewById(R.id.txt_namaPenyakit);
            img_penyakit = itemView.findViewById(R.id.txt_imgPenyakit);
        }
    }
}
