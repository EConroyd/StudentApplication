package com.example.emily.smellslikebakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Emily on 3/25/2017.
 * represent list seen earlier on the phone
 * displays new fragment list layout
 * bc fragment - extend Fragment class
 */
public class ListFragment extends Fragment {

    public interface OnRecipeSelectedInterface {
        void onListRecipeSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*Log.d(LoggingFragment.TAG, "onCreateView");*/
        OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();

            //2nd argument provided as viewgroup
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        //returning view that represents fragment list layout

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);
        ListAdapter listAdapter = new ListAdapter(listener);
        recyclerView.setAdapter(listAdapter );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
