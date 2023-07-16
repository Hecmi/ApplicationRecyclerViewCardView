package com.example.applicationrecyclerviewcardview.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applicationrecyclerviewcardview.ProductoActivity;
import com.example.applicationrecyclerviewcardview.R;
import com.example.applicationrecyclerviewcardview.modelos.Producto;

import java.util.List;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ProductoViewHolder>
{
    private Context Ctx;
    private List<Producto> lstProductos;

    public AdaptadorProducto(Context mCtx, List<Producto> products) {
        this.lstProductos = products;
        Ctx = mCtx;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.ly_item_producto, null);
        return new ProductoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        Producto producto = lstProductos.get(position);
        holder.textViewTitle.setText(producto.getTitle());
        holder.textViewValor.setText(producto.getPrice());
        holder.textViewDescripcion.setText(producto.getDescription());

        //Establecer el evento click en el cardview creado.
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Ctx, producto.getTitle(), Toast.LENGTH_SHORT).show();
                //Crear el bundle para el envío de la información.
                Bundle b = new Bundle();

                //Simplemente se envía el array o el conjunto de imágenes para evitar realizar
                //otra ejecución y formateo al web service de los productos.
                b.putStringArray("Imagenes", producto.getUrlsImagenes());

                //Creación del intent, agregación de extras (bundle) y ejecución de actividad.
                Intent intent = new Intent(Ctx, ProductoActivity.class);
                intent.putExtras(b);
                Ctx.startActivity(intent);
            }
        });


        Glide.with(Ctx)
                .load(producto.getThumbnail())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lstProductos.size();
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewValor, textViewDescripcion;
        ImageView imageView;
        CardView cardView;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.txtTitle);
            textViewValor = itemView.findViewById(R.id.txtValor);
            textViewDescripcion = itemView.findViewById(R.id.txtDescripcion);
            imageView = itemView.findViewById(R.id.imgProductoD);
            cardView = itemView.findViewById(R.id.cardViewItemProducto);
        }
    }
}