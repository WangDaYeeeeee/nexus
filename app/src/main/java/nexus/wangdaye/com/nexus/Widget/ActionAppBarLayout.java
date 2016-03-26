package nexus.wangdaye.com.nexus.Widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

/**
 * Action appbar layout, hide while scrolling.
 * */

public class ActionAppBarLayout extends AppBarLayout {
    // data
    public boolean showing;

    public ActionAppBarLayout(Context context) {
        super(context);
        this.initialize();
    }

    public ActionAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize();
    }

    private void initialize() {
        this.showing = true;
    }

    public void show(Toolbar toolbar) {
        if (!showing) {
            showing = true;
            ObjectAnimator toolbarShow = ObjectAnimator.ofFloat(this, "translationY", -1 * toolbar.getMeasuredHeight(), 0);
            toolbarShow.setDuration(300);
            toolbarShow.start();
        }
    }

    public void hide(Toolbar toolbar) {
        if (showing) {
            showing = false;
            ObjectAnimator toolbarHide = ObjectAnimator.ofFloat(this, "translationY", 0, -1 * toolbar.getMeasuredHeight());
            toolbarHide.setDuration(300);
            toolbarHide.start();
        }
    }
}
