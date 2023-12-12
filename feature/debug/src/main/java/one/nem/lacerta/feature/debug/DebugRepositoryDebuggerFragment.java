package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.eclipse.jgit.lib.Repository;
import org.intellij.lang.annotations.JdkConstants;

import java.util.UUID;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import one.nem.lacerta.data.repository.DebugFunc;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugRepositoryDebuggerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class DebugRepositoryDebuggerFragment extends Fragment {

    @Inject
    DebugFunc debugFunc;

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

        EditText editTextRepoId = view.findViewById(R.id.editTextRepoId);

        view.findViewById(R.id.buttonGenerateRepoId).setOnClickListener(v -> {
            editTextRepoId.setText(UUID.randomUUID().toString()); //Generate random UUID
            Toast.makeText(getContext(), "Generated random UUID", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonGetCreateRepository).setOnClickListener(v -> {
            String repoId = editTextRepoId.getText().toString();
            Repository repo = debugFunc.getOrCreateRepositoryById(repoId);
            Toast.makeText(getContext(), "Get or create repository: " + repoId, Toast.LENGTH_SHORT).show();
        });
    }
}