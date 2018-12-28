package com.example.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.ak.R;

public class Basefragment extends Fragment {

    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.base_layout_fragment,container,false);
            toolbar = view.findViewById(R.id.toolbar);
            toolbar.setTitle("标题");
            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        return view;
    }

}
