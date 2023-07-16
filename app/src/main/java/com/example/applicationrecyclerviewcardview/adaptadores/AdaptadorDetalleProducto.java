package com.example.applicationrecyclerviewcardview.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applicationrecyclerviewcardview.R;

import java.util.List;

public class AdaptadorDetalleProducto extends RecyclerView.Adapter<AdaptadorDetalleProducto.DetalleProductoViewHolder>
{
    private Context Ctx;
    private List<String> lstProductosImagenes;

    public AdaptadorDetalleProducto(Context mCtx, List<String> imagenes) {
        this.lstProductosImagenes = imagenes;
        Ctx = mCtx;
    }

    @Override
    public AdaptadorDetalleProducto.DetalleProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.ly_item_producto_detalle, null);
        return new AdaptadorDetalleProducto.DetalleProductoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AdaptadorDetalleProducto.DetalleProductoViewHolder holder, int position) {
        String imagen = lstProductosImagenes.get(position);

        Glide.with(Ctx)
                .load(imagen)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lstProductosImagenes.size();
    }

    class DetalleProductoViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        CardView cardView;

        public DetalleProductoViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgProductoD);
            cardView = itemView.findViewById(R.id.cvDetalleProducto);
        }
    }
}