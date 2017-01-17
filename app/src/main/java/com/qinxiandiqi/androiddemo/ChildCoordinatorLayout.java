package com.qinxiandiqi.androiddemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;

/**
 * Created by Jianan on 2017/1/17.
 */
public class ChildCoordinatorLayout extends CoordinatorLayout implements NestedScrollingChild{

    public ChildCoordinatorLayout(Context context) {
        super(context);
    }

    public ChildCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
