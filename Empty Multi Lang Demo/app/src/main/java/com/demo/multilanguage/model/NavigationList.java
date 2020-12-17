package com.demo.multilanguage.model;


import android.content.Context;

import com.demo.multilanguage.R;
import com.demo.multilanguage.utils.BaseActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by UV on 29-Nov-16.
 */
public class NavigationList {
    public static NavigationList _naviNavigationList;
    public static List<Integer> imageList;
    public static List<String> titleList;
    public static String location = "Home";
    public static String cartCount;

    public static NavigationList getInstance(Context context) {
        if (_naviNavigationList == null) {
            _naviNavigationList = new NavigationList();
        }
        imageList = Arrays.asList(R.drawable.ic_iconfinder_misc__home__1276860,
                R.drawable.ic_iconfinder_outline_bussiness_and_media_21_4041212,
                R.drawable.ic_iconfinder_share_853347,
                R.drawable.ic_iconfinder_star_172558);
        titleList = Arrays.asList(context.getResources().getString(R.string.home),
                context.getResources().getString(R.string.l),
                context.getResources().getString(R.string.m),
                context.getResources().getString(R.string.r)
        );
        return _naviNavigationList;
    }

    public static List<Integer> getImageList() {
        return imageList;
    }

    public static List<String> getTitleList() {
        return titleList;
    }

    public static void changeLocation(String mylocation) {
        location = mylocation;
        titleList.set(0, mylocation);
    }
}
