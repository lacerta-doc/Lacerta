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
public class SettingScanPageFragment extends PreferenceFragmentCompat {

    public SettingScanPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.scan_preferences);
    }

    public static SettingScanPageFragment newInstance() {
        SettingScanPageFragment fragment = new SettingScanPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

}