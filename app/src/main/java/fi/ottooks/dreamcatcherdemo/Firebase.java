package fi.ottooks.dreamcatcherdemo;

import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to save data to Firebase/Firestore and read data from there
 * @author Otto
 * https://firebase.google.com/docs/firestore
 */
public class Firebase {

    private final FirebaseFirestore firebase = FirebaseFirestore.getInstance();

    /**
     * Non parametric constructor
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Firebase() {}

    /**
     * Save data to Firebase/Firestore
     * @param list
     */
    public void saveToFireBase(List<UserInputs> list) {

        Map<String, UserInputs> data = new HashMap<>();

        for(UserInputs user: list) {

            data.put(user.getDate(), user);

        }

        firebase.collection("kaikkiunet").document("unet")
        .set(data, SetOptions.merge());

    }

    /**
     * Read data from Firebase/Firestore
     * No use at the moment but can be used to retrieve lost data back or many other purposes
     * in future.
     */
    public void getDataFromFireBase() {

        List<UserInputs> userInputsList = new ArrayList<>();
        Map<String,UserInputs> mappi = new HashMap<>();

        firebase.collection("kaikkiunet")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    /**
                     *
                     * @param task
                     */
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                UserInputs userInputs = document.toObject(UserInputs.class);
                                userInputsList.add(userInputs);

                            }

                        } else {

                            Log.d("ode", "Error getting documents: ", task.getException());

                        }
                    }
                });

    }








}
