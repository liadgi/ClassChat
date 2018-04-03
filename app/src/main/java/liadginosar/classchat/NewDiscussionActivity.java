package liadginosar.classchat;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

import liadginosar.classchat.models.DataHolder;
import liadginosar.classchat.models.Discussion;
import liadginosar.classchat.viewModels.DiscussionsViewModel;


public class NewDiscussionActivity extends AppCompatActivity {

    List<String> options;

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_discussion);

        options = new LinkedList<>();
        EditText opt = findViewById(R.id.editTextDiscussionOption);


        Button buttonAddDiscussionOption = findViewById(R.id.buttonAddDiscussionOption);
        buttonAddDiscussionOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.add(opt.getText().toString());
            }
        });

        Button buttonSubmitDiscussion = findViewById(R.id.buttonSubmitDiscussion);
        DiscussionsViewModel model = ViewModelProviders.of(this).get(DiscussionsViewModel.class);
        buttonSubmitDiscussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Discussion discussion = new Discussion();

                EditText title = findViewById(R.id.editTextDiscussionTitle);
                discussion.setTitle(title.getText().toString());
                discussion.setOptions(options);


                model.addDiscussion(discussion);

                Intent intent = new Intent(v.getContext(), ClassroomActivity.class);
                v.getContext().startActivity(intent);

            }
        });
    }
}
