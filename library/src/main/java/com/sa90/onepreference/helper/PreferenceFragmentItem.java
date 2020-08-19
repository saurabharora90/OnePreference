package com.sa90.onepreference.helper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;

/**
 * Note: Use this only to wrap {@link Fragment} which extend from {@link androidx.preference.PreferenceFragmentCompat} and not for anything else
 */
class PreferenceFragmentItem implements Parcelable {

    private String mClassName;
    private String tag;
    private Bundle extras;

    PreferenceFragmentItem(String className, String tag, Bundle extras) {
        this.mClassName = className;
        this.tag = tag;
        this.extras = extras;
    }

    void addFragmentPage(String fragmentPage) {
        this.tag = fragmentPage + "-" + tag;
    }

    /**
     * @param activity
     * @return
     */
    Fragment createFragment(Activity activity) {
        return Fragment.instantiate(activity, mClassName, extras);
    }

    String getTag() {
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
