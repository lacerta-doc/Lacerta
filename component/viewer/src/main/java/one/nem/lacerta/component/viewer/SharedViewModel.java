package one.nem.lacerta.component.viewer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<Integer> currentFragmentPosition = new MutableLiveData<>();

    public void setCurrentFragmentPosition(int position) {
        currentFragmentPosition.setValue(position);
    }

    public int getCurrentFragmentPosition() {
        assert currentFragmentPosition.getValue() != null;
        return currentFragmentPosition.getValue();
    }

}
