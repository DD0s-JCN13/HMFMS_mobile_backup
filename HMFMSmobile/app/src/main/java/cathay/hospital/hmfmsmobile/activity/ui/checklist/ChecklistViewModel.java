package cathay.hospital.hmfmsmobile.activity.ui.checklist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChecklistViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChecklistViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("Testing 030, Checklist here");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
