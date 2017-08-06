package com.example.lenovo.networking2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 23-07-2017.
 */

public class CourseListFragment extends Fragment {

    ArrayList<Course> courses;
    ListView courseListView;
    ArrayAdapter<String> adapter;
    ArrayList<String> courseNames;
    CoursesFragmentListItemClick mListener;

    void  setCoursesFragmentListItemClick(CoursesFragmentListItemClick listener){
        this.mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_list, container, false);
        courseListView = (ListView) v.findViewById(R.id.courseListView);

        courses = new ArrayList<>();
        courseNames = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,courseNames);
        courseListView.setAdapter(adapter);
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mListener != null){
                    mListener.onListItemClicked(courses.get(position));
                }
            }
        });
        fetchCourses();

        return v;
    }


    private void fetchCourses()  {

//        String urlString = "https://codingninjas.in/api/v1/courses";
//        CoursesAsyncTask courseAsyncTask = new CoursesAsyncTask();
//        courseAsyncTask.execute(urlString);
//        courseAsyncTask.setOnDownloadCompleteListener(this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://codingninjas.in/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface =  retrofit.create(ApiInterface.class);

        Call<CourseResponse> call  =  apiInterface.getCourses();
//        Call<CourseResponse> call  =  apiInterface.getPost(1);

        // apiInterface.getPost(1, 3);

//        Response<CourseResponse> response    = call.execute();


        call.enqueue(new Callback<CourseResponse>() {
            @Override
            public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {
                CourseResponse courseResponse =  response.body();
                ArrayList<Course> courseArrayList = courseResponse.data.getCoursesList();
                onDownloadComplete(courseArrayList);

            }
            @Override
            public void onFailure(Call<CourseResponse> call, Throwable t) {

            }
        });

    }

    public void onDownloadComplete(ArrayList<Course> coursesList){
        courses.clear();
        courses.addAll(coursesList);
        for(int i = 0; i < courses.size(); i++){
            courseNames.add(courses.get(i).name);
        }
        adapter.notifyDataSetChanged();

//        for(Course c : courses){
//
//        }

    }

   public interface CoursesFragmentListItemClick{
        void onListItemClicked(Course c);
    }

}
