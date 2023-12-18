package one.nem.lacerta.feature.library;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryTopFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LibraryTopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibraryTopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibraryTopFragment newInstance(String param1, String param2) {
        LibraryTopFragment fragment = new LibraryTopFragment();
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
        View view =  inflater.inflate(R.layout.fragment_library_top, container, false);
        
 // Use view.findViewById instead of findViewById
          ListView documentListView = view.findViewById(R.id.document_list);
          List<String> documentList = new ArrayList<>();

          documentList.add("Document A");
          documentList.add("Document B");
          documentList.add("Document C");

          // レイアウトリソースを指定する（例: simple_list_item_1）
          ArrayAdapter<String> adapter = new ArrayAdapter<>(
                  requireActivity(), // 使用するActivityに依存する場合は requireActivity() を使う
                  android.R.layout.simple_list_item_1,
                  documentList);

          documentListView.setAdapter(adapter);

          return view;
      }

}