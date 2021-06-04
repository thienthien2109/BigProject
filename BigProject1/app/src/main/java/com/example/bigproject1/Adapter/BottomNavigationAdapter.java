package com.example.bigproject1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bigproject1.Fragment.FragmentHome;
import com.example.bigproject1.Fragment.FragmentManagement;
import com.example.bigproject1.Fragment.FragmentUser;

public class BottomNavigationAdapter extends FragmentStatePagerAdapter {
    private int num=3;
    public BottomNavigationAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHome();
            case 1: return new FragmentManagement();
            case 2: return new FragmentUser();
            default: return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
