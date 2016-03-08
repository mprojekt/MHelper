package com.models;


public class SimpleUrlData {
    
    private String name;
    private int id;
    private String url;
    private boolean visible;

    public SimpleUrlData() {
        this.url = new String();
        this.visible = true;
    }

    public SimpleUrlData(String name, int id) {
        this();
        this.name = name;
        this.id = id;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
