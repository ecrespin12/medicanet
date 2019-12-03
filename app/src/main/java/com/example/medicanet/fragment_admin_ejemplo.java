package com.example.medicanet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.ibrahimsn.particle.ParticleView;

public class fragment_admin_ejemplo extends Fragment {

    private ParticleView particleView;

    public fragment_admin_ejemplo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fragment_admin_ejemplo, container, false);

        particleView=view.findViewById(R.id.particleView_fragment_admin_ejemplo);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        particleView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        particleView.pause();
    }
}
