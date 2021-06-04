package com.example.bigproject1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.bigproject1.Adapter.BookReVAdapter;
import com.example.bigproject1.Adapter.BottomNavigationAdapter;
import com.example.bigproject1.Model.Book;
import com.example.bigproject1.SQLite.SQLiteBookHelper;
import com.example.bigproject1.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth ;

    BottomNavigationAdapter navAdapter;
    ViewPager viewPager;
    BottomNavigationView navigationView;

    private SQLiteBookHelper sqLiteBookHelper;
    private BookReVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();


        sqLiteBookHelper = new SQLiteBookHelper(MainActivity.this);
        List<Book> listBook = sqLiteBookHelper.getAll();
        adapter = new BookReVAdapter();



        navigationView = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewPager);
        navAdapter = new BottomNavigationAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(navAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.mHome).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.mManagement).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.mUser).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mHome:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mManagement:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mUser:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }
}