package fi.ottooks.dreamcatcherdemo;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;

public class Firebase {

    private Object object;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Firebase(Object object) {

        FirebaseFirestore firebase = FirebaseFirestore.getInstance();
        System.out.println(object.toString());
        firebase.collection("kaikkiunet").document(LocalDate.now().toString()).set(object);

    }








}
