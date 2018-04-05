package liadginosar.classchat;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import liadginosar.classchat.models.Message;
import liadginosar.classchat.viewModels.DiscussionViewModel;

public class DiscussionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MessagesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);


        Intent intent = getIntent();
        String currentClassroom = intent.getStringExtra(MainActivity.CLASSROOM);

        DiscussionViewModel model = ViewModelProviders.of(this).get(DiscussionViewModel.class);
        model.setClassroom(currentClassroom);

        model.getMessages().observe(this, (List<Message> item) ->
        {
            mAdapter.setMessages(item);
            mAdapter.notifyDataSetChanged();
        });
    }
}
