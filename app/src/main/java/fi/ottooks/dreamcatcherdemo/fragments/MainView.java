package fi.ottooks.dreamcatcherdemo.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fi.ottooks.dreamcatcherdemo.R;

public class MainView extends Fragment {

    Button btn;

    @Nullable
    @Override
    public View onCreateView
    (

        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState


    )

    {
        View rootView =
        inflater.inflate(R.layout.page_1, container,false);

        btn = (Button) rootView.findViewById(R.id.uusi_heratys);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Log.d("namu","pelle");

            }
        });

        return rootView;
    }

}
