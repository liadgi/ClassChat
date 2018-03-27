package liadginosar.classchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClassroomActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirebaseRef;



    private static final String CLASSROOM_PATH = "/classroom/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        Intent intent = getIntent();
        String classroom = intent.getStringExtra(MainActivity.CLASSROOM);

        TextView title = findViewById(R.id.textViewClassroomTitle);

        title.setText(classroom);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseRef = mFirebaseDatabase.getReference(CLASSROOM_PATH);


    }
}
