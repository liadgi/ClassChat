package liadginosar.classchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import liadginosar.classchat.models.DataHolder;

public class MainActivity extends AppCompatActivity {

    final public static String CLASSROOM = "classroom";
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

                Intent intent = new Intent(v.getContext(), ClassroomActivity.class);
                v.getContext().startActivity(intent);

            }
        });
    }
}
