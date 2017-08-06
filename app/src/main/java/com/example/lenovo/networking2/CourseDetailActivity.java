package com.example.lenovo.networking2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lenovo on 23-07-2017.
 */

public class CourseDetailActivity extends AppCompatActivity {

    Course c;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        // Intent
        Intent i = getIntent();
        c = (Course) i.getSerializableExtra("course");
        CourseDetailFragment courseDetailFragment = (CourseDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.courseDetailFragment);
        courseDetailFragment.setCourse(c);
        // Course

    }
}
