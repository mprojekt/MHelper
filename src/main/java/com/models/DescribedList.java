package com.models;

import java.util.ArrayList;
import java.util.List;


public class DescribedList<T> {
    
    private String descr;
    private List<T> list;

    public DescribedList(String descr) {
        this.descr = descr;
        this.list = new ArrayList<>();
    }
    
    public void add(T a){
        this.list.add(a);
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    
}
