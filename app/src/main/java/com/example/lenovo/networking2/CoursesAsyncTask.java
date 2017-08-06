package com.example.lenovo.networking2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lenovo on 23-07-2017.
 */

public class CoursesAsyncTask extends AsyncTask<String ,Void,ArrayList<Course>> {
    OnDownloadCompleteListener mListener;

    void setOnDownloadCompleteListener(OnDownloadCompleteListener listener){
        mListener=listener;
    }

    @Override
    protected ArrayList<Course> doInBackground(String... params) {

        String urlString=params[0];

        try {
            URL url=new URL(urlString);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream=urlConnection.getInputStream();
            Scanner s=new Scanner(inputStream);
            String str="";
            while (s.hasNext()){
                str+=s.nextLine();
            }
            Log.i("FechedString ",str);
            return parseCourses(str);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private ArrayList<Course> parseCourses(String str)  {

        try {
            JSONObject coursesJson=new JSONObject(str);
            JSONObject data = coursesJson.getJSONObject("data");
            JSONArray coursesJsonArray = data.getJSONArray("courses");

            ArrayList<Course> courseList = new ArrayList<>();

            for (int i = 0; i < coursesJsonArray.length(); i++) {
                JSONObject courseJson = (JSONObject) coursesJsonArray.get(i);
                int id = courseJson.getInt("id");
                String name = courseJson.getString("name");
                String title = courseJson.getString("title");
                String overview = courseJson.getString("overview");
                Course c = new Course(id, title, name, overview);
                courseList.add(c);

            }

            return courseList;


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Course> courses) {
        super.onPostExecute(courses);
        if(mListener!=null){
            mListener.onDownloadComplete(courses);
        }
    }
}

interface OnDownloadCompleteListener{
    void onDownloadComplete(ArrayList<Course> courseList);
}