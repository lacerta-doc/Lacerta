package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuMetaDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DebugMenuMetaDataFragment extends Fragment {
    public DebugMenuMetaDataFragment() {
        // Required empty public constructor
    }

    public static DebugMenuMetaDataFragment newInstance() {
        DebugMenuMetaDataFragment fragment = new DebugMenuMetaDataFragment();
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
        return inflater.inflate(R.layout.fragment_debug_menu_meta_data, container, false);
    }
}