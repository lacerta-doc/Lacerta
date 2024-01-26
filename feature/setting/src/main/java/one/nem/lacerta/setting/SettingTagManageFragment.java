package one.nem.lacerta.setting;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.common.LacertaCreateTagDialog;
import one.nem.lacerta.component.common.LacertaCreateTagDialogListener;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.document.tag.DocumentTag;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingTagManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class SettingTagManageFragment extends Fragment {

    @Inject
    LacertaLibrary lacertaLibrary;

    private RecyclerView recyclerView;

    private TagListItemAdapter adapter;

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

        this.recyclerView = view.findViewById(R.id.tag_item_recycler_view);
        this.adapter = new TagListItemAdapter((tagId, tagName, tagColor) -> {
            Toast.makeText(getContext(), "Tag Clicked", Toast.LENGTH_SHORT).show();
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateTagList();
    }

    /**
     * タグリストを更新する
     */
    private void updateTagList() {
        lacertaLibrary.getTagList().thenAccept(documentTags -> {
            int currentTagCount = this.adapter.getItemCount();
            this.adapter.setDocumentTags(documentTags);
            if (currentTagCount != this.adapter.getItemCount()) {
                this.adapter.notifyItemRangeRemoved(0, currentTagCount);
                this.adapter.notifyItemRangeInserted(0, this.adapter.getItemCount());
            }
        });
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
                    LacertaCreateTagDialog dialog = new LacertaCreateTagDialog();
                    dialog.setListener(new LacertaCreateTagDialogListener() {
                        @Override
                        public void onPositiveClick(String tagName, String tagColor) {
                            Toast.makeText(getContext(), "Positive Clicked", Toast.LENGTH_SHORT).show();
                            DocumentTag newTag = new DocumentTag();
                            newTag.setName(tagName);
                            newTag.setColor(tagColor);
                            lacertaLibrary.createTag(newTag).join();
                            updateTagList();
                        }

                        @Override
                        public void onNegativeClick() {
                            Toast.makeText(getContext(), "Negative Clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show(getParentFragmentManager(), "create_tag_dialog");
                    return true;
                } else {
                    return false;
                }
            });
        });
    }
}