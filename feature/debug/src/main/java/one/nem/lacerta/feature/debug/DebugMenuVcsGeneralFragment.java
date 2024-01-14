package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import one.nem.lacerta.feature.debug.common.adapter.DebugMenuListItemAdapter;
import one.nem.lacerta.feature.debug.common.model.DebugMenuListItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuVcsGeneralFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DebugMenuVcsGeneralFragment extends Fragment {
    public DebugMenuVcsGeneralFragment() {
        // Required empty public constructor
    }

    public static DebugMenuVcsGeneralFragment newInstance() {
        DebugMenuVcsGeneralFragment fragment = new DebugMenuVcsGeneralFragment();
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
        View view = inflater.inflate(R.layout.fragment_debug_menu_vcs_general, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));
        List<DebugMenuListItem> debugMenuListItems = new ArrayList<>();

        debugMenuListItems.add(new DebugMenuListItem("General Action", "placeholder", R.id.action_debugMenuVcsGeneralFragment_to_debugMenuVcsGeneralActionFragment, true));
        debugMenuListItems.add(new DebugMenuListItem("Log Record", "placeholder", R.id.action_debugMenuVcsGeneralFragment_to_debugMenuVcsLogRecordFragment, true));
        debugMenuListItems.add(new DebugMenuListItem("Rev Record", "placeholder", R.id.action_debugMenuVcsGeneralFragment_to_debugMenuVcsRevRecordFragment, true));

        DebugMenuListItemAdapter adapter = new DebugMenuListItemAdapter(debugMenuListItems);
        recyclerView.setAdapter(adapter);

        return view;
    }
}