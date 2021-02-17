package com.example.howimetyourboozer.database.model;

public class Drink {

    private String id;
    private String name;
    private String type;
    private float degree;
    private String description;
    private String icon;
    private int mark;

    public Drink(String id, String name, String type, float degree, String description, String icon, int mark) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.degree = degree;
        this.description = description;
        this.icon = icon;
        this.mark = mark;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
