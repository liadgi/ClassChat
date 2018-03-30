package liadginosar.classchat.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import liadginosar.classchat.models.Discussion;

/**
 * Created by liadginosar on 27/03/2018.
 */

public class DiscussionsViewModel extends ViewModel {
    private MutableLiveData<List<Discussion>> discussions;

    public LiveData<List<Discussion>> getDiscussions() {
        if (discussions == null) {
            discussions = new MutableLiveData<List<Discussion>>();
            discussions.setValue(new LinkedList<Discussion>());
            addListener();

            loadDiscussions();
        }
        return discussions;
    }

    private void loadDiscussions() {
        // Do an asynchronous operation to fetch users.


    }

    private void addListener() {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseRef = mFirebaseDatabase.getReference(Discussion.CLASSROOM_PATH);

        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                try {
                    Discussion disc = dataSnapshot.getValue(Discussion.class);
                    addDiscussionFromDB(disc);
                } catch (Exception e) {
                    System.out.println("Exception");
                }

                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void addDiscussionFromDB(Discussion disc) {
        getDiscussions().getValue().add(disc);
        discussions.setValue(discussions.getValue());
    }

    public void addDiscussion(Discussion discussion) {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseRef = mFirebaseDatabase.getReference(Discussion.CLASSROOM_PATH);


        mFirebaseRef.setValue(discussion);
    }

}
