package liadginosar.classchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DiscussionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);


        Intent intent = getIntent();
        String discussion = intent.getStringExtra(MainActivity.DISCUSSION);

        // get discussion details from db
    }
}
