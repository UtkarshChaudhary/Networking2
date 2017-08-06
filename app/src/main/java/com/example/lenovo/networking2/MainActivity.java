package com.example.lenovo.networking2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements CourseListFragment.CoursesFragmentListItemClick{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       CourseListFragment courseListFragment = (CourseListFragment) getSupportFragmentManager().findFragmentById(R.id.courseListFragment);

        courseListFragment.setCoursesFragmentListItemClick(this);

        Course c = new Course(12,"Android","Envision","xyz");
        Gson gson = new Gson();

        String json = gson.toJson(c);
        Log.i("JSONString", json);
        Course c2 = gson.fromJson(json, Course.class);
//        Log.i("JSONString", "Object "+ c2.title);


    }


    @Override
    public void onListItemClicked(Course c) {
        Toast.makeText(this, c.name+" clicked ", Toast.LENGTH_SHORT).show();

        FrameLayout frameLayout =  (FrameLayout) findViewById(R.id.containerLayout);
        if(frameLayout == null){
            //portrait
            Intent i=new Intent(this,CourseDetailActivity.class);
            i.putExtra("course",c);
            startActivity(i);
        }else{
            // Landscape
            //            CourseDetailFragment courseDetailFragment = (CourseDetailFragment)
//                    getSupportFragmentManager().findFragmentById(R.id.courseDetailFragment);
//            courseDetailFragment.setCourse(c);

            CourseDetailFragment courseDetailFragment = new CourseDetailFragment();
            Bundle b = new Bundle();
            b.putSerializable("course", c);
            courseDetailFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, courseDetailFragment ).commit();
//            courseDetailFragment.setCourse(c);


        }


    }
}
