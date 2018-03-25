package liadginosar.classchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ClassroomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        Intent intent = getIntent();
        String classroom = intent.getStringExtra(MainActivity.CLASSROOM);

        TextView title = findViewById(R.id.textViewClassroomTitle);

        title.setText(classroom);
    }
}
