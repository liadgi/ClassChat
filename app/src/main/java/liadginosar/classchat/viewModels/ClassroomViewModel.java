package liadginosar.classchat.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import liadginosar.classchat.models.DataHolder;
import liadginosar.classchat.models.Discussion;

/**
 * Created by liadginosar on 27/03/2018.
 */

public class ClassroomViewModel extends ViewModel {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirebaseRef;

    private ValueEventListener valueEventListener;
    private ChildEventListener childEventListener;

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
        String currentClassroom = DataHolder.getInstance().getData();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseRef = mFirebaseDatabase.getReference(Discussion.CLASSROOM_PATH + currentClassroom);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                try {
                    //Discussion disc = dataSnapshot.getValue(Discussion.class);
                    //addDiscussionFromDB(disc);
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
        };

        mFirebaseRef.addValueEventListener(valueEventListener);


        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    Discussion disc = dataSnapshot.getValue(Discussion.class);
                    addDiscussionFromDB(disc);
                } catch (Exception e) {
                    System.out.println("Exception");
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


        mFirebaseRef.addChildEventListener(childEventListener);
    }

    private void addDiscussionFromDB(Discussion disc) {
        List<Discussion> discs = getDiscussions().getValue();
        discs.add(disc);
        discussions.postValue(discs);
    }

    public void addDiscussion(Discussion discussion) {
        String currentClassroom = DataHolder.getInstance().getData();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseRef = mFirebaseDatabase.getReference(Discussion.CLASSROOM_PATH + currentClassroom);

        DatabaseReference pushedDiscussionRef = mFirebaseRef.push();
        pushedDiscussionRef.setValue(discussion);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mFirebaseRef.removeEventListener(childEventListener);
        mFirebaseRef.removeEventListener(valueEventListener);
    }
}
