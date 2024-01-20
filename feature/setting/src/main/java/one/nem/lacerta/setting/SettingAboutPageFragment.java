package one.nem.lacerta.setting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingAboutPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingAboutPageFragment extends Fragment {

    public SettingAboutPageFragment() {
        // Required empty public constructor
    }

    public static SettingAboutPageFragment newInstance() {
        SettingAboutPageFragment fragment = new SettingAboutPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting_about_page, container, false);


        return view;
    }
}