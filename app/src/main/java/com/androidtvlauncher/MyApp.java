package com.androidtvlauncher;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by leo on 13/04/2017.
 */

public class MyApp {
    CharSequence label;
    CharSequence name;
    Drawable icon;
    private ActivityInfo activityInfo;
    public ActivityInfo getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(ActivityInfo activityInfo) {
        this.activityInfo = activityInfo;
    }

    public CharSequence getLabel() {
        return label;
    }

    public void setLabel(CharSequence label) {
        this.label = label;
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
