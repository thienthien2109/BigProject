package com.example.bigproject1.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.bigproject1.Adapter.BookReVAdapter;
import com.example.bigproject1.MainActivity;
import com.example.bigproject1.Model.Book;
import com.example.bigproject1.R;
import com.example.bigproject1.SQLite.SQLiteBookHelper;

import java.util.List;

public class FragmentHome extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView tvHome;
    private RecyclerView rev;
    private BookReVAdapter adapter;
    private SQLiteBookHelper sqLiteBookHelper;


    private String mParam1;
    private String mParam2;

    public FragmentHome() {
    }

    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvHome = view.findViewById(R.id.tvHome);
        rev = view.findViewById(R.id.ReV);

        adapter = new BookReVAdapter();
        sqLiteBookHelper = new SQLiteBookHelper(getContext());
        rev.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        List<Book> listBook= sqLiteBookHelper.getAll();
        adapter.setListBooks(listBook);
        rev.setAdapter(adapter);

        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> listBook= sqLiteBookHelper.getAll();
                adapter.setListBooks(listBook);
                rev.setAdapter(adapter);
            }
        });

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home,menu);
        MenuItem item = menu.findItem(R.id.mSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Book> list = sqLiteBookHelper.search(newText);
                adapter.setListBooks(list);
                return true;
            }
        });
         super.onCreateOptionsMenu(menu, inflater);
    }
}