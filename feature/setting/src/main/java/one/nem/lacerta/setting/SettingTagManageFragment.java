package one.nem.lacerta.setting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingTagManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingTagManageFragment extends Fragment {

    public SettingTagManageFragment() {
        // Required empty public constructor
    }

    public static SettingTagManageFragment newInstance() {
        SettingTagManageFragment fragment = new SettingTagManageFragment();
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
        return inflater.inflate(R.layout.fragment_setting_tag_manage, container, false);
    }
}