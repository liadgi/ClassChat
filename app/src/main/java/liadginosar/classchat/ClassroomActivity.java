package liadginosar.classchat;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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


    List<TextView> titles;
    LinearLayout ll;

    private void initUI() {
        titles = new LinkedList<TextView>();
        ll = findViewById(R.id.linearLayout);
        ll.setOrientation(LinearLayout.VERTICAL);
    }

    private void addText(TextView textView) {
        ll.addView(textView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        initUI();

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
            for (Discussion disc : item) {
                TextView tv = new TextView(getApplicationContext());
                tv.setText(disc.getTitle());
                this.addText(tv);
                TextView lastTitle = findViewById(R.id.textViewLastTitle);
                lastTitle.setText(disc.getTitle());

            }


        }
        );

    }
}
