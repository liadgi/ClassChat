package liadginosar.classchat.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import liadginosar.classchat.models.Discussion;

/**
 * Created by liadginosar on 30/03/2018.
 */

public class ClassroomViewModel extends ViewModel {

    private final MutableLiveData<List<Discussion>> selected = new MutableLiveData<List<Discussion>>();


}
