package com.example.cocinapp;

import java.io.Serializable;

public class Recipe implements Serializable {

    //acá pongo las columnas que tiene la tabla en la base de datos para las recetas
    private String id;
    private String name;
    private String time;

    public Recipe(String id, String name, String time) {
        this.id   = id;
        this.name = name;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}