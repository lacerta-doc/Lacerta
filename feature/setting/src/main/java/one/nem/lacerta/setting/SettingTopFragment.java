package one.nem.lacerta.setting;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import one.nem.lacerta.setting.model.SettingListItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingTopFragment extends Fragment {

    public SettingTopFragment() {
        // Required empty public constructor
    }

    public static SettingTopFragment newInstance() {
        SettingTopFragment fragment = new SettingTopFragment();
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
        View view = inflater.inflate(R.layout.fragment_setting_top, container, false);

        // Setting items
        ArrayList<SettingListItem> settingListItems = new ArrayList<>();
        settingListItems.add(
                new SettingListItem(
                        "Display",
                        "Display settings",
                        ContextCompat.getDrawable(getContext(), one.nem.lacerta.shared.ui.R.drawable.palette_24px),
                        R.id.action_settingTopFragment_to_settingDisplayPageFragment
                )
        );

        settingListItems.add(
                new SettingListItem(
                        "Data",
                        "Data settings",
                        ContextCompat.getDrawable(getContext(), one.nem.lacerta.shared.ui.R.drawable.settings_backup_restore_24px),
                        R.id.action_settingTopFragment_to_settingDataPageFragment
                )
        );

        settingListItems.add(
                new SettingListItem(
                        "Scan",
                        "Scan settings",
                        ContextCompat.getDrawable(getContext(), one.nem.lacerta.shared.ui.R.drawable.document_scanner_24px),
                        R.id.action_settingTopFragment_to_settingScanPageFragment
                )
        );

        settingListItems.add(
                new SettingListItem(
                        "About",
                        "About this app",
                        ContextCompat.getDrawable(getContext(), one.nem.lacerta.shared.ui.R.drawable.info_24px),
                        R.id.action_settingTopFragment_to_settingAboutPageFragment
                )
        );

        // Init RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.setting_item_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ListItemAdapter(settingListItems));

        return view;
    }
}