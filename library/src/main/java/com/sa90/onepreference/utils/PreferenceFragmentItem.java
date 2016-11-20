package com.sa90.onepreference.utils;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Saurabh Arora on 20/11/16.
 *
 * Note: Use this only to wrap {@link Fragment} which extend from {@link android.support.v14.preference.PreferenceFragment} and not for anything else
 */
public class PreferenceFragmentItem implements Parcelable {

    private String mClassName;
    private String tag;
    private Bundle extras;

    public PreferenceFragmentItem(String className, String tag, Bundle extras) {
        this.mClassName = className;
        this.tag = tag;
        this.extras = extras;
    }

    public void addFragmentPage(String fragmentPage) {
        this.tag = fragmentPage + "-" + tag;
    }

    /**
     * Beacuse of some weird reason, the preference support library extends form {@link Fragment} and not from {@link android.support.v4.app.Fragment}
     * @param activity
     * @return
     */
    public Fragment createFragment(Activity activity) {
        return Fragment.instantiate(activity, mClassName, extras);
    }

    public String getTag() {
        return tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mClassName);
        dest.writeString(this.tag);
        dest.writeBundle(this.extras);
    }

    protected PreferenceFragmentItem(Parcel in) {
        this.mClassName = in.readString();
        this.tag = in.readString();
        this.extras = in.readBundle();
    }

    public static final Creator<PreferenceFragmentItem> CREATOR = new Creator<PreferenceFragmentItem>() {
        @Override
        public PreferenceFragmentItem createFromParcel(Parcel source) {
            return new PreferenceFragmentItem(source);
        }

        @Override
        public PreferenceFragmentItem[] newArray(int size) {
            return new PreferenceFragmentItem[size];
        }
    };
}
