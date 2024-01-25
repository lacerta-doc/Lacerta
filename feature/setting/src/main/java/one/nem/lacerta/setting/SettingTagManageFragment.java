package one.nem.lacerta.setting;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingTagManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingTagManageFragment extends Fragment {

    public SettingTagManageFragment() {
        // Required empty public constructor
    }

    public static SettingTagManageFragment newInstance() {
        SettingTagManageFragment fragment = new SettingTagManageFragment();
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
        View view = inflater.inflate(R.layout.fragment_setting_tag_manage, container, false);

        // Toolbar
        Toolbar toolbar = view.findViewById(R.id.tag_manage_toolbar);
        toolbarSetup(toolbar, true, "タグ管理");

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * ToolbarをInitする
     *
     * @param toolbar Toolbar
     * @param showBackButton 戻るボタンを表示するか
     * @param title タイトル
     */
    private void toolbarSetup(Toolbar toolbar, boolean showBackButton, String title) {
        getActivity().runOnUiThread(() -> {
            if (showBackButton) {
                toolbar.setNavigationIcon(one.nem.lacerta.shared.ui.R.drawable.arrow_back_24px);
                toolbar.setNavigationOnClickListener(v -> {
                    //this.libraryItemPage = lacertaLibrary.getLibraryPage(this.libraryItemPage.getParentId(), 10).join();
                    // Back
                    Navigation.findNavController(requireView()).popBackStack();
                });
            } else {
                toolbar.setNavigationIcon(null);
            }
            toolbar.setTitle(title);
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.setting_tag_manage_menu);
            toolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.setting_tag_manage_menu_add) {
                    Toast.makeText(getContext(), "Add Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return false;
                }
            });
        });
    }
}