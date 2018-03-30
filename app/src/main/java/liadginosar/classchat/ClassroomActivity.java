package liadginosar.classchat;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import liadginosar.classchat.models.Discussion;
import liadginosar.classchat.viewModels.DiscussionsViewModel;

public class ClassroomActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        Intent intent = getIntent();
        String classroom = intent.getStringExtra(MainActivity.CLASSROOM);

        TextView title = findViewById(R.id.textViewClassroomTitle);

        title.setText(classroom);

        DiscussionsViewModel model = ViewModelProviders.of(this).get(DiscussionsViewModel.class);


        Button addDiscussionButton = findViewById(R.id.buttonAddDiscussion);
        addDiscussionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewDiscussionActivity.class);
                v.getContext().startActivity(intent);

            }
        });


        model.getDiscussions().observe(this, item ->
        {
            //TODO
            int index = 0;
            for (Discussion disc : item) {
                LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = vi.inflate(R.layout.activity_classroom, null);

                // fill in any details dynamically here
                TextView tv = (TextView) v.findViewById(R.id.textViewDiscussion);
                /*TextView tv = new TextView(getApplicationContext());
                tv.setSingleLine(false);
                tv.setHeight(100);
                tv.setWidth(100);
                */
                tv.setText(disc.getTitle());
                //this.addText(tv);

                // insert into main view
                ViewGroup insertPoint = (ViewGroup) findViewById(R.id.linearLayoutInsertPoint);
                //insertPoint.addView(v, index++, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
                insertPoint.addView(v, index++);

            }

        }
        );

    }
}
