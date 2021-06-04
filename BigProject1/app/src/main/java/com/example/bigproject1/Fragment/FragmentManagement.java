package com.example.bigproject1.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bigproject1.Adapter.BookReVAdapter;
import com.example.bigproject1.MainActivity;
import com.example.bigproject1.Model.Book;
import com.example.bigproject1.R;
import com.example.bigproject1.SQLite.SQLiteBookHelper;

import java.util.Calendar;
import java.util.List;

public class FragmentManagement extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView tvDate;
    private EditText edId,edTitle,edAuthor,edPrice;
    private Button btnAdd,btnDate;
    private BookReVAdapter adapter;
    private SQLiteBookHelper sqLiteBookHelper;
    private Spinner spType;

    public FragmentManagement() {
    }

    public static FragmentManagement newInstance(String param1, String param2) {
        FragmentManagement fragment = new FragmentManagement();
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
        return inflater.inflate(R.layout.fragment_management, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edId = view.findViewById(R.id.edId);
        edTitle = view.findViewById(R.id.edTitle);
        edAuthor = view.findViewById(R.id.edAuthor);
        edPrice = view.findViewById(R.id.edPrice);
        tvDate = view.findViewById(R.id.tvDate);
        spType = view.findViewById(R.id.spType);

        btnAdd = view.findViewById(R.id.btAdd);
        btnDate = view.findViewById(R.id.btnDate);
        adapter = new BookReVAdapter();
        sqLiteBookHelper = new SQLiteBookHelper(getContext());

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                int mMonth = c.get(Calendar.MONTH);
                int mYear = c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                tvDate.setText(dayOfMonth+"/"+(month-1)+"/"+year);
                            }
                        },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edTitle.getText().toString();
                String author = edAuthor.getText().toString();
                String type = spType.getSelectedItem().toString();
                String date = tvDate.getText().toString();
                double price = Double.parseDouble(edPrice.getText().toString());
                Book book = new Book(title,author,type,date,price);
                sqLiteBookHelper.addBook(book);
                List<Book> list = sqLiteBookHelper.getAll();
                adapter.setListBooks(list);
            }
        });
    }
}