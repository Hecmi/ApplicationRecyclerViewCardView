package com.example.applicationrecyclerviewcardview.modelos;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Producto {
    String id;
    String thumbnail;
    String category;
    String title;
    String description;
    String price;
    String discountPercentage;

    String [] urlsImagenes;

    public Producto(JSONObject jProducto) throws JSONException {
        this.id = jProducto.getString("id");
        this.thumbnail = jProducto.getString("thumbnail");
        this.category = jProducto.getString("category");
        this.title = jProducto.getString("title");
        this.description = jProducto.getString("description");
        this.price = jProducto.getString("price");
        this.discountPercentage = jProducto.getString("discountPercentage");

        JSONArray imagenes = jProducto.getJSONArray("images");
        this.urlsImagenes = new String [imagenes.length()];

        for (int i = 0; i < imagenes.length(); i++){
            this.urlsImagenes[i] = imagenes.get(i).toString();
        }
    }
    public String [] getUrlsImagenes() {
        return urlsImagenes;
    }

    public void setUrlsImagenes(String [] urlsImagenes) {
        this.urlsImagenes = urlsImagenes;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public static ArrayList<Producto> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        for (int i = 0; i < datos.length(); i++) {
            productos.add(new Producto(datos.getJSONObject(i)));
        }
        return productos;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " " + description;
    }
}
