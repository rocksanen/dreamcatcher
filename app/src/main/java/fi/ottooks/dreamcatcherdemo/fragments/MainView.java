package fi.ottooks.dreamcatcherdemo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import fi.ottooks.dreamcatcherdemo.R;
import fi.ottooks.dreamcatcherdemo.clockBreak;

public class MainView extends Fragment implements View.OnClickListener {

    Button activityButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.page_1, container, false);
        activityButton = (Button) fragmentView.findViewById(R.id.breakActivityBtn);
        activityButton.setOnClickListener(this);
        return fragmentView;

    }


    @Override
    public void onClick(View view) {
        Intent nextActivity = new Intent(getActivity(), clockBreak.class);
        startActivity(nextActivity);
    }
}
