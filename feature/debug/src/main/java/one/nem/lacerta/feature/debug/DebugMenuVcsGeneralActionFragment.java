package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.vcs.LacertaVcs;
import one.nem.lacerta.vcs.factory.LacertaVcsFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuVcsGeneralActionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class DebugMenuVcsGeneralActionFragment extends Fragment {

    @Inject
    LacertaVcsFactory lacertaVcsFactory;

    public DebugMenuVcsGeneralActionFragment() {
        // Required empty public constructor
    }

    public static DebugMenuVcsGeneralActionFragment newInstance() {
        DebugMenuVcsGeneralActionFragment fragment = new DebugMenuVcsGeneralActionFragment();
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
        View view = inflater.inflate(R.layout.fragment_debug_menu_vcs_general_action, container, false);

        view.findViewById(R.id.add_sample_log_rev_button).setOnClickListener(v -> {
            LacertaVcs lacertaVcs = lacertaVcsFactory.create("example_id");
            lacertaVcs.insertPage(1, "example_id");
        });

        return view;
    }
}