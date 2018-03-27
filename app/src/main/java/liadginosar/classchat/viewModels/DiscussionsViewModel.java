package liadginosar.classchat.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
            loadDiscussions();
        }
        return discussions;
    }

    private void loadDiscussions() {
        // Do an asynchronous operation to fetch users.

    }

    public void addDiscussion(Discussion discussion) {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseRef = mFirebaseDatabase.getReference(Discussion.CLASSROOM_PATH);

        mFirebaseRef.setValue(discussion);
    }

}
