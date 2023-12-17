package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import one.nem.lacerta.feature.debug.common.model.DebugMenuListItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DebugMenuTopFragment extends Fragment {
    public DebugMenuTopFragment() {
        // Required empty public constructor
    }
    public static DebugMenuTopFragment newInstance() {
        DebugMenuTopFragment fragment = new DebugMenuTopFragment();
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
        View view = inflater.inflate(R.layout.fragment_debug_menu_top, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.debug_menu_recycler_view);

        List<DebugMenuListItem> debugMenuListItems = new ArrayList<>();
        debugMenuListItems.add(new DebugMenuListItem("Meta Data", "View meta data", R.id.action_debugMenuTopFragment_to_debugMenuMetaDataFragment, true));

        return view;
    }
}