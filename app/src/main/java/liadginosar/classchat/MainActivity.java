package liadginosar.classchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import liadginosar.classchat.models.DataHolder;
import liadginosar.classchat.models.Discussion;

public class MainActivity extends AppCompatActivity {

    final public static String CLASSROOM = "classroom";
    final public static String DISCUSSION = "discussion";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button joinButton = findViewById(R.id.buttonJoinClass);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextClassroom = findViewById(R.id.joinClassInput);
                String classNumber = editTextClassroom.getText().toString();

                DataHolder.getInstance().setData(classNumber);

                //FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
                //DatabaseReference mFirebaseRef = mFirebaseDatabase.getReference("/classrooms/");

                //DatabaseReference pushedDiscussionRef = mFirebaseRef.push();
                //pushedDiscussionRef.setValue(classNumber);

                Intent intent = new Intent(v.getContext(), ClassroomActivity.class);
                v.getContext().startActivity(intent);

            }
        });
    }
}
