package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Map;

import one.nem.lacerta.data.model.shared_pref.enums.SharedPrefType;
import one.nem.lacerta.source.pref.repository.Common; //TODO-rca: 名前変えるべきかも

import one.nem.lacerta.data.repository.SharedPref;

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

    @Inject
    SharedPref sharedPref;

    SharedPrefType sharedPrefType = SharedPrefType.COMMON;

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

        TextView loadValueTextView = view.findViewById(R.id.loadValueTextView);

        view.findViewById(R.id.loadButton).setOnClickListener(v -> {
            String value = sharedPref.getSharedPreferencesByTag(sharedPrefType).getString(loadKeyEditText.getText().toString(), "null");
            ((TextView) view.findViewById(R.id.loadValueTextView)).setText(value);
        });

        view.findViewById(R.id.saveButton).setOnClickListener(v -> {
            String[] split = saveKeyEditText.getText().toString().split(":");
            sharedPref.getSharedPreferencesByTag(sharedPrefType).edit().putString(split[0], split[1]).apply();
            updateList(loadValueTextView);
        });

        // ラジオボタンの変更を監視
        view.findViewById(R.id.radioButtonCommon).setOnClickListener(v -> {
            sharedPrefType = SharedPrefType.COMMON;
            updateList(loadValueTextView);
        });
        view.findViewById(R.id.radioButtonUserData).setOnClickListener(v -> {
            sharedPrefType = SharedPrefType.USERDATA;
            updateList(loadValueTextView);
        });

    }

    public void updateList(TextView textView) {
        // リストの更新
        Map<String, ?> resultMap = sharedPref.getSharedPreferencesByTag(sharedPrefType).getAll();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ?> entry : resultMap.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue().toString()).append("\n");
        }

        textView.setText(sb.toString());
    }
}