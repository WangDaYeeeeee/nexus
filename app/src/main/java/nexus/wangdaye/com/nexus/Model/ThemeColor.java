package nexus.wangdaye.com.nexus.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import nexus.wangdaye.com.nexus.R;

/**
 * Theme color.
 * */

public class ThemeColor {
    // data
    private int colorMain;
    private int colorDark;
    private int colorAccent;

    public ThemeColor(Context context) {
        this.initColor(context);
    }

    public void initColor(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        colorMain = sharedPreferences.getInt(context.getString(R.string.key_theme_color_main), R.color.colorPrimary);
        colorAccent = sharedPreferences.getInt(context.getString(R.string.key_theme_color_accent), R.color.colorAccent);

        switch (colorMain) {
            case R.color.red_main:
                colorDark = R.color.red_dark;
                break;
            case R.color.pink_main:
                colorDark = R.color.pink_dark;
                break;
            case R.color.purple_main:
                colorDark = R.color.purple_dark;
                break;
            case R.color.deep_purple_main:
                colorDark = R.color.deep_purple_dark;
                break;
            case R.color.indigo_main:
                colorDark = R.color.indigo_dark;
                break;
            case R.color.blue_main:
                colorDark = R.color.blue_dark;
                break;
            case R.color.light_blue_main:
                colorDark = R.color.light_blue_dark;
                break;
            case R.color.cyan_main:
                colorDark = R.color.cyan_dark;
                break;
            case R.color.teal_main:
                colorDark = R.color.teal_dark;
                break;
            case R.color.green_main:
                colorDark = R.color.green_dark;
                break;
            case R.color.light_green_main:
                colorDark = R.color.light_green_dark;
                break;
            case R.color.lime_main:
                colorDark = R.color.lime_dark;
                break;
            case R.color.yellow_main:
                colorDark = R.color.yellow_dark;
                break;
            case R.color.amber_main:
                colorDark = R.color.amber_dark;
                break;
            case R.color.orange_main:
                colorDark = R.color.orange_dark;
                break;
            case R.color.deep_orange_main:
                colorDark = R.color.deep_orange_dark;
                break;
            case R.color.brown_main:
                colorDark = R.color.brown_dark;
                break;
            case R.color.grey_main:
                colorDark = R.color.grey_dark;
                break;
            case R.color.blue_grey_main:
                colorDark = R.color.blue_grey_dark;
                break;
            default:
                colorDark = R.color.colorPrimaryDark;
                break;

        }
    }

    public int[] getColor() {
        return new int[] {colorMain, colorDark, colorAccent};
    }
}
