package liadginosar.classchat;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import liadginosar.classchat.models.DataHolder;
import liadginosar.classchat.models.Discussion;
import liadginosar.classchat.viewModels.DiscussionsViewModel;

public class ClassroomActivity extends AppCompatActivity implements DiscussionsAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;
    private DiscussionsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        String classroom = DataHolder.getInstance().getData();

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


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)

        mAdapter = new DiscussionsAdapter(model.getDiscussions().getValue());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);

        model.getDiscussions().observe(this, (List<Discussion> item) ->
        {

            mAdapter.setDiscussions(item);
            mAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        // open Discussion page
        Intent intent = new Intent(view.getContext(), DiscussionActivity.class);
        TextView tv = (TextView) view;
        intent.putExtra(MainActivity.DISCUSSION, tv.getText().toString());
        view.getContext().startActivity(intent);
    }
}
