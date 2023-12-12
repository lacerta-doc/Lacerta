package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugRepositoryDebuggerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DebugRepositoryDebuggerFragment extends Fragment {

    public DebugRepositoryDebuggerFragment() {
        // Required empty public constructor
    }

    public static DebugRepositoryDebuggerFragment newInstance() {
        DebugRepositoryDebuggerFragment fragment = new DebugRepositoryDebuggerFragment();
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
        return inflater.inflate(R.layout.fragment_debug_repository_debugger, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}