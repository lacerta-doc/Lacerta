package one.nem.lacerta.setting;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceFragmentCompat;

import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingScanPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingScanPageFragment extends Fragment {

    public SettingScanPageFragment() {
        // Required empty public constructor
    }
//
//    @Override
//    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
////        addPreferencesFromResource(R.xml.scan_preferences);
//    }

    public static SettingScanPageFragment newInstance() {
        SettingScanPageFragment fragment = new SettingScanPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_scan_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}