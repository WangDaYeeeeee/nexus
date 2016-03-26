package nexus.wangdaye.com.nexus.Widget;

import android.app.ActivityManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

import nexus.wangdaye.com.nexus.Model.ThemeColor;
import nexus.wangdaye.com.nexus.R;

/**
 * Theme activity, extends AppCompatActivity class.
 * */

public class ThemeActivity extends AppCompatActivity {
    // data
    public int[] themeColors;

    public void initColorTheme(boolean isInit, String name, FrameLayout statusBar, CoordinatorLayout.LayoutParams layoutParams) {
        this.setThemeColors();
        this.setWindowTopColor(name);
        this.initNavigationBar();
        this.setStatusBarColor(statusBar,layoutParams, isInit);
    }

    public void setThemeColors() {
        ThemeColor themeColor = new ThemeColor(this);
        this.themeColors = themeColor.getColor();
    }

    public void setWindowTopColor(String name) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityManager.TaskDescription taskDescription;
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            taskDescription = new ActivityManager.TaskDescription(name, icon, ContextCompat.getColor(this, themeColors[0]));
            setTaskDescription(taskDescription);
            icon.recycle();
        }
    }

    public void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void initNavigationBar () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean navigationBarColorSwitch = sharedPreferences.getBoolean(getString(R.string.key_navigationBar_color_switch), false);
            if (navigationBarColorSwitch && themeColors != null) {
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, themeColors[1]));
            } else if (navigationBarColorSwitch) {
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            } else {
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, android.R.color.black));
            }
        }
    }

    public void setStatusBarColor(FrameLayout statusBar, CoordinatorLayout.LayoutParams layoutParams, boolean isInit) {
        if (isInit) {
            Class<?> c;
            Object obj;
            Field field;
            int x, statusBarHeight = 0;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            layoutParams.height = statusBarHeight;
            statusBar.setLayoutParams(layoutParams);
        }
        if (themeColors != null) {
            statusBar.setBackgroundColor(ContextCompat.getColor(this, themeColors[1]));
        } else {
            statusBar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
    }
}
