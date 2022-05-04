package fi.ottooks.dreamcatcherdemo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import fi.ottooks.dreamcatcherdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserAgeQuestion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserAgeQuestion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserAgeQuestion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserAgeQuestion.
     */
    // TODO: Rename and change types and number of parameters
    public static UserAgeQuestion newInstance(String param1, String param2) {
        UserAgeQuestion fragment = new UserAgeQuestion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(fi.ottooks.dreamcatcherdemo.R.layout.fragment_user_age_question, container, false);
        EditText userAge = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.userAge);
        TextView ageInfoTv = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.ageInfo);

        Button ageBtn = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.ageBtn);
        ageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userAge.getText().toString().trim().length() != 0) {
                    int age = Integer.parseInt(userAge.getText().toString());
                    //tiedot ovat Sleep Foundation netti sivulta.
                    if (age > 0 && age < 3) {
                        ageInfoTv.setText("Suositelttu uniaika vauvallesi: 11-14 tuntia");
                    } else if (age > 2 && age < 6) {
                        ageInfoTv.setText("Suositeltu uniaika lapsellesi: 10-13 tuntia.");
                    } else if (age > 5 && age < 10) {
                        ageInfoTv.setText("suositeltu uniaika lapsellesi: 9-11 tuntia.");
                    } else if (age > 9 && age < 14) {
                        ageInfoTv.setText("Suositeltu uniaika sinulle: 9-11 tuntia.");
                    } else if (age > 13 && age < 18) {
                        ageInfoTv.setText("Suositeltu uniaika sinulle: 8-10 tuntia.");
                    } else if (age > 17 && age < 65) {
                        ageInfoTv.setText("Suositeltu uniaika sinulle: 7-9 tuntia.");
                    } else if (age >= 65) {
                        ageInfoTv.setText("Suositeltu unia aika sinulle: 7-8 tuntia.");
                    } else {
                        ageInfoTv.setText("Ikä pitäisi olla enemmän kuin 1.");
                    }
                }

            }
        });
        // Inflate the layout for this fragment
        return view ;
    }
}