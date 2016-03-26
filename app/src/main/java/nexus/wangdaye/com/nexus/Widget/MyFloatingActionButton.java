package nexus.wangdaye.com.nexus.Widget;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import nexus.wangdaye.com.nexus.R;

/**
 * Floating action button group.
 * */

public class MyFloatingActionButton extends FloatingActionButton {
    // data
    public boolean showing;
    public boolean opening;

    public MyFloatingActionButton(Context context) {
        super(context);
        this.initialize(context);
    }

    public MyFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize(context);
    }

    public MyFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initialize(context);
    }

    private void initialize(Context context) {
        this.showing = false;
        this.opening = false;
    }

    public void show(Context context) {
        if (!showing) {
            showing = true;
            AnimatorSet fabShow = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.fab_show);
            fabShow.setTarget(this);
            fabShow.start();
        }
    }

    public void hide(Context context) {
        if (showing) {
            showing = false;
            AnimatorSet fabHide = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.fab_hide);
            fabHide.setTarget(this);
            fabHide.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });
            fabHide.start();
        }
    }

    public void open(Context context) {
        if (!opening) {
            opening = true;
            AnimatorSet fabOpen = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.fab_open);
            fabOpen.setTarget(this);
            fabOpen.start();
        }
    }

    public void close(Context context) {
        if (opening) {
            opening = false;
            AnimatorSet fabClose = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.fab_close);
            fabClose.setTarget(this);
            fabClose.start();
        }
    }
}
