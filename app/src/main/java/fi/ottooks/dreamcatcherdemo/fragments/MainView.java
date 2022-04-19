package fi.ottooks.dreamcatcherdemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fi.ottooks.dreamcatcherdemo.R;

public class MainView extends Fragment {

    @Nullable
    @Override
    public View onCreateView

    (
        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState
    )

    { return (ViewGroup)inflater.inflate(R.layout.page_1,container,false); }

}
