package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import one.nem.lacerta.source.pref.repository.Common; //TODO-rca: 名前変えるべきかも

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugSharedPrefEditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class DebugSharedPrefEditorFragment extends Fragment {

    @Inject
    Common prefCommon;

    public DebugSharedPrefEditorFragment() {
        // Required empty public constructor
    }

    public static DebugSharedPrefEditorFragment newInstance() {
        DebugSharedPrefEditorFragment fragment = new DebugSharedPrefEditorFragment();
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
        return inflater.inflate(R.layout.fragment_debug_shared_pref_editor, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText loadKeyEditText = view.findViewById(R.id.loadKeyEditText);
        EditText saveKeyEditText = view.findViewById(R.id.saveKeyEditText);

        view.findViewById(R.id.loadButton).setOnClickListener(v -> {
            String key = loadKeyEditText.getText().toString();
            String value = prefCommon.getStringValue(key);
            view.findViewById(R.id.loadValueTextView).setContentDescription(value);
        });

        view.findViewById(R.id.saveButton).setOnClickListener(v -> {
            String KeyValue = saveKeyEditText.getText().toString();
            String[] split = KeyValue.split(":", 2);
            prefCommon.setStringValue(split[0], split[1]);
        });

    }
}