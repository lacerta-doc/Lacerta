package one.nem.lacerta.feature.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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


    public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

        private List<String> documentList;

        public DocumentAdapter(List<String> documentList) {
            this.documentList = documentList;
        }

        @NonNull
        @Override
        public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new DocumentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
            holder.bind(documentList.get(position));
        }

        @Override
        public int getItemCount() {
            return documentList.size();
        }

        class DocumentViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;

            DocumentViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }

            void bind(String document) {
                textView.setText(document);
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library_top, container, false);

        // Use view.findViewById instead of findViewById
        RecyclerView documentRecyclerView = view.findViewById(R.id.document_list);


        if (documentRecyclerView != null) {
            List<String> documentList = new ArrayList<>();
            documentList.add("Document A");
            documentList.add("Document B");
            documentList.add("Document C");

            // Create and set the adapter
            DocumentAdapter adapter = new DocumentAdapter(documentList);
            documentRecyclerView.setAdapter(adapter);

            // Use a LinearLayoutManager to specify the layout
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
            documentRecyclerView.setLayoutManager(layoutManager);
        }

        return view;
    }

}