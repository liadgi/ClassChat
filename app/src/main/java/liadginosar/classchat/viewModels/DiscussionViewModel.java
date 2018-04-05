package liadginosar.classchat.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

import liadginosar.classchat.models.DataHolder;
import liadginosar.classchat.models.Discussion;
import liadginosar.classchat.models.Message;

/**
 * Created by liadginosar on 05/04/2018.
 */

public class DiscussionViewModel extends ViewModel {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirebaseRef;

    private ChildEventListener childEventListener;

    private String currentClassroom;
    private MutableLiveData<List<Message>> messages;

    public void setClassroom(String currentClassroom) {
        this.currentClassroom = currentClassroom;
    }

    public void addMessage(String message) {

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseRef = mFirebaseDatabase.getReference(Discussion.CLASSROOM_PATH + currentClassroom);

        mFirebaseRef.push().setValue(message);
    }

    private void addMessageFromDB(Message message) {

    }

    private void addListener() {
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    Message message = dataSnapshot.getValue(Message.class);
                    addMessageFromDB(message);
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

    public MutableLiveData<List<Message>> getMessages() {
        if (messages == null) {
            messages = new MutableLiveData<List<Message>>();
            messages.setValue(new LinkedList<Message>());
            addListener();

            loadMessages();
        }
        return messages;

    }
    private void loadMessages() {
        // Do an asynchronous operation to fetch users.


    }
}
