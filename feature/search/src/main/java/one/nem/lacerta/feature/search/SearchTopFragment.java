package one.nem.lacerta.feature.search;

import android.app.ActionBar;
import android.app.LauncherActivity;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchTopFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = SearchTopFragment.class.getSimpleName();
    private final SearchTopFragment self = this;

    private SearchView searchView;
    private String searchWord;


    private static final String ARG_PARAM1 = "param1";

    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchTopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchTopFragment.
     */
    // TODO: Rename and change types and number of parameters


    public static SearchTopFragment newInstance(String param1, String param2) {
        SearchTopFragment fragment = new SearchTopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_top, container, false);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);




        this.searchView.setIconifiedByDefault(true);


        this.searchView.setSubmitButtonEnabled(false);


        if (!this.searchWord.equals("")) {

            this.searchView.setQuery(this.searchWord, false);
        } else {
            String queryHint = self.getResources().getString(R.string.hello_blank_fragment);

            this.searchView.setQueryHint(queryHint);
        }
        this.searchView.setOnQueryTextListener(self.onQueryTextListener);
    }

    private final SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {

            return self.setSearchWord(searchWord);
        }

        @Override
        public boolean onQueryTextChange(String query) {

            return false;
        }
    };
    private boolean setSearchWord(String query) {


        if (searchWord != null && !searchWord.equals("")) {

            this.searchWord = searchWord;
        }

        this.searchView.setIconified(false);

        this.searchView.onActionViewCollapsed();

        this.searchView.clearFocus();
        return false;
    }

}


    interface LacertaSearch {
        ArrayList<LauncherActivity.ListItem> autoSearch(String query, int limit);

        ArrayList<LauncherActivity.ListItem> autoSearch(String query, int limit, int offset);
    }




