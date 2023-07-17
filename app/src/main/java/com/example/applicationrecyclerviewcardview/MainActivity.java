package com.example.applicationrecyclerviewcardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.applicationrecyclerviewcardview.adaptadores.AdaptadorProducto;
import com.example.applicationrecyclerviewcardview.modelos.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Aplicando web service
        String url = "https://dummyjson.com/products";

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService(url,
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        //Formatear JSON:
        JSONObject jProductos = new JSONObject(result);
        JSONArray productos = jProductos.getJSONArray("products");

        ArrayList<Producto> productoArrayList = Producto.JsonObjectsBuild(productos);

        int resId = R.anim.layout_animation_down_to_up;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerView.setLayoutAnimation(animation);

        //Crear adaptador para los productos y establecerlo en el recyclerview
        AdaptadorProducto adaptadorProducto= new AdaptadorProducto(this, productoArrayList);
        recyclerView.setAdapter(adaptadorProducto);
    }

}