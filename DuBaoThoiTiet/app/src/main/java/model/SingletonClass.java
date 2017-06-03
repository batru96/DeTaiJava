package model;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gietb on 14/02/2017.
 */

public class SingletonClass {
    private static SingletonClass instance = null;

    protected SingletonClass() {

    }

    public static SingletonClass getInstance() {
        if (instance == null) {
            instance = new SingletonClass();
        }
        return instance;
    }

    public String ConvertUnixToTime(long dt, String paramSdf) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(paramSdf);
        date.setTime((long) dt * 1000);

        return sdf.format(date);
    }

    public String ChuanHoaChuoi(String s) {
        String str = s.trim();

        while (str.indexOf("  ") != -1) {
            str = str.replace("  ", " ");
        }
        str = str.replaceAll(" ", "%20");
        return str;
    }

    public String VietHoaDauChuoi(String s) {
        if (s != null && s.charAt(0) != ' ') {
            char[] chars = s.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            s = "";
            for (int i = 0; i < chars.length; i++) {
                s += chars[i];
            }
        }
        return s;
    }

    public String ChangeStringIcon(String s) {
        char[] chars = s.toCharArray();

        String kq = "";
        kq += chars[2];
        kq += chars[0];
        kq += chars[1];
        return kq;
    }

    public int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
