package liadginosar.classchat.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by liadginosar on 27/03/2018.
 */

public class Discussion {
    public String title;
    public List<String> options;

    public static final String CLASSROOM_PATH = "/classroom/";
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mFirebaseRef = mFirebaseDatabase.getReference(CLASSROOM_PATH);


}
