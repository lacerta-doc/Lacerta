package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

import one.nem.lacerta.data.repository.DeviceMeta;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DebugMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
public class DebugMenuFragment extends Fragment {

    @Inject
    DeviceMeta deviceMeta;

    @Inject


    public DebugMenuFragment() {
        // Required empty public constructor
    }

    public static DebugMenuFragment newInstance() {
        DebugMenuFragment fragment = new DebugMenuFragment();
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
        return inflater.inflate(R.layout.fragment_debug_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_debug_menu_scan).setOnClickListener( v -> {
            // スキャン機能呼び出し
            Toast.makeText(getContext(), "testMessage", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.btn_debug_menu_shared_pref_editor).setOnClickListener( v -> {
            // Fragment移動
            Navigation.findNavController(view).navigate(R.id.action_debugMenuFragment_to_debugSharedPrefEditorFragment);
        });

        view.findViewById(R.id.btn_debug_menu_clear_pref).setOnClickListener( v -> {
            // SharedPrefClear
        });

        view.findViewById(R.id.btn_debug_menu_call_play_ground).setOnClickListener( v -> {
            Navigation.findNavController(view).navigate(R.id.action_debugMenuFragment_to_debugPlayGroundFragment);
        });

        view.findViewById(R.id.btn_debug_menu_get_external_path).setOnClickListener( v -> {
            Toast.makeText(getContext(), getContext().getExternalFilesDir(null).toPath().toString(), Toast.LENGTH_SHORT).show();
        });
    }
}