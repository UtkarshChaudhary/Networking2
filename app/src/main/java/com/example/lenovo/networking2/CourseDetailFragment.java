package com.example.lenovo.networking2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lenovo on 23-07-2017.
 */

public class CourseDetailFragment extends Fragment {

    TextView nameTextView;
    TextView titleTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_course_detail, container, false);
        nameTextView = (TextView) v.findViewById(R.id.courseNameTextView);
        titleTextView = (TextView) v.findViewById(R.id.courseTitleTextView);
        Bundle b = getArguments(); // null if in Portrait mode
        if(b != null) {
            Course c = (Course) b.getSerializable("course");
            setCourse(c);
        }
          setHasOptionsMenu(true);
        //only then fragment will call its oncreateMenu to contribute in menu of activity
        return  v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
    }

    void setCourse(Course c){

        if(c != null){
            nameTextView.setText(c.name);
            titleTextView.setText(c.getTitle());
        }

    }

}
