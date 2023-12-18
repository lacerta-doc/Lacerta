package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import one.nem.lacerta.data.repository.SharedPref;

public class DebugClearPrefFragment extends Fragment {
    @Inject
    SharedPref sharedPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_debug_clear_pref);
    }

    public DebugClearPrefFragment(){

    }
}
