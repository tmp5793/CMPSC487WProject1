package com.example.cmpsc487wproject1;

public class users {
    int id;
    String name, title;
    Boolean access;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getAccess() {
        return access;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public users(int id, String name, String title, Boolean access) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.access = access;
    }
}
