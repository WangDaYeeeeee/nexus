package nexus.wangdaye.com.nexus.View;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;

import nexus.wangdaye.com.nexus.R;
import nexus.wangdaye.com.nexus.Widget.ThemeActivity;

/**
 *
 * */

public class AboutActivity extends ThemeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStatusBarTransparent();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
