package fi.ottooks.dreamcatcherdemo.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import fi.ottooks.dreamcatcherdemo.StatsSorting;
import fi.ottooks.dreamcatcherdemo.UseSharedPreferences;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserAgeQuestion#newInstance} factory method to
 * create an instance of this fragment.
 * @author Mohammed and Otto
 */
public class UserAgeQuestion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


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

        final UserAgeQuestion fragment = new UserAgeQuestion();
        final Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    /**
     * the view in question compares the inputted age to an sleeptime recommendation.
     * The onClick listener validates the users data using vertaaUniAika() method.
     * Unvalid data returns an error message.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return returns recommended sleeptime according to inputted age.
     */

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view =
        inflater.inflate(fi.ottooks.dreamcatcherdemo.R.layout.fragment_user_age_question, container, false);
        final EditText userAge = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.userAge);
        final TextView ageInfoTv = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.ageInfo);
        final TextView sleepCompare = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.compareTv);

        final Button ageBtn = view.findViewById(fi.ottooks.dreamcatcherdemo.R.id.ageBtn);
        ageBtn.setOnClickListener(view1 -> {

            if (userAge.getText().toString().trim().length() != 0) {

                final int age = Integer.parseInt(userAge.getText().toString());

                ageInfoTv.setVisibility(View.VISIBLE);
                sleepCompare.setVisibility(View.VISIBLE);
                //tiedot ovat Sleep Foundation netti sivulta.
                if (age > 0 && age < 3) {
                    ageInfoTv.setText("Suositeltu uniaika ikäisellesi on 11-14 tuntia/vrk");
                    sleepCompare.setText(vertaaUniAika(11,14));
                } else if (age > 2 && age < 6) {
                    ageInfoTv.setText("Suositeltu uniaika ikäisellesi on 10-13 tuntia/vrk.");
                    sleepCompare.setText(vertaaUniAika(10,13));
                } else if (age > 5 && age < 10) {
                    ageInfoTv.setText("Suositeltu uniaika ikäisellesi on 9-11 tuntia/vrk.");
                    sleepCompare.setText(vertaaUniAika(9,11));
                } else if (age > 9 && age < 14) {
                    ageInfoTv.setText("Suositeltu uniaika ikäisellesi on 9-11 tuntia/vrk.");
                    sleepCompare.setText(vertaaUniAika(9,11));
                } else if (age > 13 && age < 18) {
                    ageInfoTv.setText("Suositeltu uniaika ikäisellesi on 8-10 tuntia/vrk.");
                    sleepCompare.setText(vertaaUniAika(8,10));
                } else if (age > 17 && age < 65) {
                    ageInfoTv.setText("Suositeltu uniaika ikäisellesi on 7-9 tuntia/vrk.");
                    sleepCompare.setText(vertaaUniAika(7,9));
                } else if (age >= 65) {
                    ageInfoTv.setText("Suositeltu uniaika ikäisellesi on 7-8 tuntia/vrk.");
                    sleepCompare.setText(vertaaUniAika(7,8));
                } else {
                    sleepCompare.setVisibility(View.INVISIBLE);
                    ageInfoTv.setText("Iän pitäisi olla enemmän kuin 0.");
                }
            }else {
                ageInfoTv.setVisibility(View.INVISIBLE);
                sleepCompare.setVisibility(View.INVISIBLE);
            }

            userAge.onEditorAction(EditorInfo.IME_ACTION_DONE);

        });
        // Inflate the layout for this fragment
        return view ;

        }

    /**
     * The method compares user's average sleeping time and the recommended time based on the inputted age.
     * @param alku the least recommended sleeping time (int)
     * @param loppu the most recommended sleeping time (int)
     * @return returns a string that tells the user the recommended sleeping time. (String)
     */
    @SuppressLint("DefaultLocale")
    public String vertaaUniAika (int alku, int loppu){

        final double sleepAvg =

                 new StatsSorting(new UseSharedPreferences().
                 getListFromPreferences()).
                 getUniKeskiArvoToDouble();

            double aika;

                 if (sleepAvg < alku){

                        aika = alku - sleepAvg;
                        return
                        "Sinun tulisi nukkua " +
                        Double.parseDouble(String.format("%.1f",aika)) +
                        " tuntia vuorokaudessa enemmän.";

                 }else if(sleepAvg > loppu){

                        aika = sleepAvg - loppu;
                        return
                        "Sinun tulisi nukkua " +
                        Double.parseDouble(String.format("%.1f",aika)) +
                        " tuntia vuorokaudessa vähemmän.";

                 }return

                         "Keskimääräinen uniaikasi " +
                         Double.parseDouble(String.format("%.1f",sleepAvg)) +
                         " tuntia, on suositusten mukainen";
         }
}