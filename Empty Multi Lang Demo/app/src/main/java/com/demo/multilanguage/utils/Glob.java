package com.demo.multilanguage.utils;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class Glob {
    public static boolean isFromNotyfication;
    public static int img_pos;
    public static final String FROM_NOTICE="FromNotice";
    public static final String FROM_ASSIGNMENT="FromAssignment";
    public static final String FROM_QUESTIONPAPER="FromQuestionPaper";


    //Links
    //MainStartActivity
    //demo.windexapp.com

    public static final String ApiName = "http://api.astro-panchang.com/WebServices/Astro9WebService.asmx/ApiName?";

    public static void netNotAvail(Context context) {
        Toast.makeText(context, "Netwot not available", Toast.LENGTH_SHORT).show();
    }

    public static int getDisplayWidth(Context ctx) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((AppCompatActivity) ctx).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return width;
    }
    public static int getDisplayHeight(Context ctx) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((AppCompatActivity) ctx).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return height;
    }

    public static String Get_ThoughtConstant="";
    public static String Get_PanchagMainConstant="";
    public static String Get_Permitin="";
    public static String Get_event_today="";
    public static String GetDesNotice="";
    public static String GetPDFNotice="";
    public static String GetHomeWorktoay="";
    public static String GetBrithdayToday="";

    public static String adapterDayChodhidiya="";
    public static String nightChoghadiya="";
    public static String Panchang="";
    public static String StudentMenu="";

                                                   // common.setpreferencestring(todaynewactivity.this,"todayapimin",""+minute);


}
