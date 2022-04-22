package fi.ottooks.dreamcatcherdemo;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;

public class Firebase {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Firebase(Object object) {

        FirebaseFirestore firebase = FirebaseFirestore.getInstance();

        firebase.collection("kaikkiunet").document(LocalDate.now().toString()).set(object);

    }





}
