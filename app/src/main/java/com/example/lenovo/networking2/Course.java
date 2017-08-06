package com.example.lenovo.networking2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lenovo on 23-07-2017.
 */

public class Course implements Serializable {
/*
Java class example,

public class Person {

    @SerializedName("name")
    private String personName;

    @SerializedName("bd")
    private String birthDate;

}
This class has two fields that represent the person name and birth date
of a person. These fields are annotated with the @SerializedName annotation.
 The parameter (value) of this annotation is the name to be used
 when serialising and deserialising objects. For example, the Java field
 personName is represented as name in JSON.

JSON Example,

{
    "name":"chintan",
    "bd":"01-01-1990"
}

 */
    @SerializedName("i_d")
    private int id;
    private String title;
    String name;
    String overview;

    @SerializedName("is_active")
    boolean isActive;

    public Course(int id, String title, String name, String overview) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }
}
