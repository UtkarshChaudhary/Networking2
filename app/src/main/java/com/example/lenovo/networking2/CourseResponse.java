package com.example.lenovo.networking2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by lenovo on 23-07-2017.
 */

public class CourseResponse {

    public Data data;
    public int status;

    public static class Data{

        @SerializedName("courses")
        public ArrayList<Course> coursesList;

        public ArrayList<Course> getCoursesList() {
            return coursesList;
        }

        public void setCoursesList(ArrayList<Course> coursesList) {
            this.coursesList = coursesList;
        }
    }

}
