package com.example.applicationrecyclerviewcardview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.example.applicationrecyclerviewcardview.adaptadores.AdaptadorDetalleProducto;

import java.util.Arrays;
import java.util.List;

public class ProductoActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        recyclerView = (RecyclerView) findViewById(R.id.rvDetalleProducto);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Bundle b = this.getIntent().getExtras();

        //Para evitar la ejecución repetida del web service, simplemente se reciben las imágenes
        //basadas en la selección del producto realizado en la actividad principal.
        String [] urlImagenes = b.getStringArray("Imagenes");
        List<String> lstImagenes = Arrays.asList(urlImagenes);

        AdaptadorDetalleProducto adaptadorDetalleProducto = new AdaptadorDetalleProducto(this, lstImagenes);
        recyclerView.setAdapter(adaptadorDetalleProducto);

    }
}