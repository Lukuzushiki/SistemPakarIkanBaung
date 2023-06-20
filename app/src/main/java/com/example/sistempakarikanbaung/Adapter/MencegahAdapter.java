package com.example.sistempakarikanbaung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistempakarikanbaung.Model.CegahModel;
import com.example.sistempakarikanbaung.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MencegahAdapter extends RecyclerView.Adapter<MencegahAdapter.MyViewHolder> {
    Context context;
    List<CegahModel> mencegahAdapters;

    public MencegahAdapter(Context c, List<CegahModel> p){
        context = c;
        mencegahAdapters = p;
    }

    @NonNull
    @Override
    public MencegahAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mencegah, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MencegahAdapter.MyViewHolder holder, int position) {
        holder.txt_mencegah.setText(mencegahAdapters.get(position).getCara_mencegah());
    }

    @Override
    public int getItemCount() {
        return mencegahAdapters == null ? 0: mencegahAdapters.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_mencegah;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_mencegah = itemView.findViewById(R.id.txt_list_mencegah);
        }
    }
}
