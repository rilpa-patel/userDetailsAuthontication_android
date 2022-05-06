package com.example.tabviewwithdatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class fragment extends FragmentPagerAdapter {
    Context context;
    int tabCount = 0;

    public fragment(Context context, @NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.context = context;
        this.tabCount = tabCount;

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Insert();
            case 1:
                return new update();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}


