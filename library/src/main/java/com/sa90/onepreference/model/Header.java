package com.sa90.onepreference.model;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceActivity;
import android.text.TextUtils;

import androidx.annotation.StringRes;

/**
 * Adopted from AOSP
 * Description of a single Header item that the user can select.
 */
public final class Header implements Parcelable {

    /**
     * Default value for {@link PreferenceActivity.Header#id Header.id} indicating that no
     * identifier value is set.  All other values (including those below -1)
     * are valid.
     */
    public static final long HEADER_ID_UNDEFINED = -1;

    /**
     * Identifier for this header, to correlate with a new list when
     * it is updated.  The default value is
     * {@link PreferenceActivity#HEADER_ID_UNDEFINED}, meaning no id.
     * @attr ref android.R.styleable#PreferenceHeader_id
     */
    public long id = HEADER_ID_UNDEFINED;

    /**
     * Resource ID of title of the header that is shown to the user.
     * @attr ref android.R.styleable#PreferenceHeader_title
     */
    @StringRes
    public int titleRes;

    /**
     * Title of the header that is shown to the user.
     * @attr ref android.R.styleable#PreferenceHeader_title
     */
    public CharSequence title;

    /**
     * Resource ID of optional summary describing what this header controls.
     * @attr ref android.R.styleable#PreferenceHeader_summary
     */
    @StringRes
    public int summaryRes;

    /**
     * Optional summary describing what this header controls.
     * @attr ref android.R.styleable#PreferenceHeader_summary
     */
    public CharSequence summary;

    /**
     * Optional icon resource to show for this header.
     * @attr ref android.R.styleable#PreferenceHeader_icon
     */
    public int iconRes;

    /**
     * Full class name of the fragment to display when this header is
     * selected.
     * @attr ref android.R.styleable#PreferenceHeader_fragment
     */
    public String fragment;

    /**
     * Optional arguments to supply to the fragment when it is
     * instantiated.
     */
    public Bundle fragmentArguments;

    /**
     * Intent to launch when the preference is selected.
     */
    public Intent intent;

    public Header() {
        // Empty
    }

    /**
     * Return the currently set title.  If {@link #titleRes} is set,
     * this resource is loaded from <var>res</var> and returned.  Otherwise
     * {@link #title} is returned.
     */
    public CharSequence getTitle(Resources res) {
        if (titleRes != 0) {
            return res.getText(titleRes);
        }
        return title;
    }

    /**
     * Return the currently set summary.  If {@link #summaryRes} is set,
     * this resource is loaded from <var>res</var> and returned.  Otherwise
     * {@link #summary} is returned.
     */
    public CharSequence getSummary(Resources res) {
        if (summaryRes != 0) {
            return res.getText(summaryRes);
        }
        return summary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(titleRes);
        TextUtils.writeToParcel(title, dest, flags);
        dest.writeInt(summaryRes);
        TextUtils.writeToParcel(summary, dest, flags);
        dest.writeInt(iconRes);
        dest.writeString(fragment);
        dest.writeBundle(fragmentArguments);
        if (intent != null) {
            dest.writeInt(1);
            intent.writeToParcel(dest, flags);
        } else {
            dest.writeInt(0);
        }
    }

    public void readFromParcel(Parcel in) {
        id = in.readLong();
        titleRes = in.readInt();
        title = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        summaryRes = in.readInt();
        summary = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        iconRes = in.readInt();
        fragment = in.readString();
        fragmentArguments = in.readBundle(getClass().getClassLoader());
        if (in.readInt() != 0) {
            intent = Intent.CREATOR.createFromParcel(in);
        }
    }

    Header(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Header> CREATOR = new Creator<Header>() {
        public Header createFromParcel(Parcel source) {
            return new Header(source);
        }
        public Header[] newArray(int size) {
            return new Header[size];
        }
    };
}
