package com.example.bigproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bigproject1.Model.Book;
import com.example.bigproject1.SQLite.SQLiteBookHelper;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {
    private EditText edID,edTitle,edAuthor,edPrice;
    private Spinner spType;
    private TextView tvDate;
    private Button btnDate,btnUpdate,btnDel;

    private SQLiteBookHelper sqLiteBookHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edID = findViewById(R.id.edIdItem);
        edTitle = findViewById(R.id.edTitleItem);
        edAuthor = findViewById(R.id.edAuthorItem);
        edPrice = findViewById(R.id.edPriceItem);
        spType = findViewById(R.id.spTypeItem);
        tvDate = findViewById(R.id.tvDateItem);
        btnDate = findViewById(R.id.btnDateItem);
        btnUpdate = findViewById(R.id.btUpdate);
        btnDel = findViewById(R.id.btDel);

        sqLiteBookHelper = new SQLiteBookHelper(this);

        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");

        edID.setText(book.getId()+"");
        edTitle.setText(book.getTitle());
        edAuthor.setText(book.getAuthor());
        for (int i=0; i<spType.getCount();i++){
            if (spType.getItemAtPosition(i).toString()==book.getType()){
                spType.setSelection(i);
            }
        }
        tvDate.setText(book.getDate());
        edPrice.setText(book.getPrice()+"");

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                int mMonth = c.get(Calendar.MONTH);
                int mYear = c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                tvDate.setText(day+"/"+(month-1)+"/"+year);
                            }
                        },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edID.getText().toString());
                String title = edTitle.getText().toString();
                String author = edAuthor.getText().toString();
                String type = spType.getSelectedItem().toString();
                String date = tvDate.getText().toString();
                double price = Double.parseDouble(edPrice.getText().toString());
                Book b = new Book(id,title,author,type,date,price);
                sqLiteBookHelper.update(b);
                Toast.makeText(EditActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edID.getText().toString());
                sqLiteBookHelper.delete(id);
                Toast.makeText(EditActivity.this, "Delete Successful", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}