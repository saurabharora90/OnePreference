package com.sa90.onepreference.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.XmlRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;

import com.sa90.onepreference.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saurabh Arora on 20/11/16.
 *
 * This is the helper for doing the heavy lifting to be able to use the Preference v14 support library to work with both Tablets and Phones
 *
 * The process adopted involves following the standard practise of using a {@link PreferenceActivity} for tablets
 * but for phone, we parse the header file to extract all the {@link android.support.v14.preference.PreferenceFragment}
 * and then place all those fragments in a Normal Activity inside in a vertically oriented LinearLayout.
 * This allows us to ignore the headers on the Phone view and directly display the Preference List.
 */

public class PreferenceHelper {

    private static final long HEADER_ID_UNDEFINED = -1;

    /**
     * Parses the header file to return a list of fragments referred to by each header item.
     * This should be called only on phones as we tend to skip the headers and display all the preferences as a list.
     *
     * @param headerResource
     * @param activity
     * @return
     */
    public static List<PreferenceFragmentItem> getPreferenceFragmentList(@XmlRes int headerResource, Activity activity) {
        List<PreferenceActivity.Header> headerList = new ArrayList<>();
        loadHeadersFromResource(headerResource, headerList, activity);

        List<PreferenceFragmentItem> fragmentItems = new ArrayList<>(headerList.size());
        for (PreferenceActivity.Header header : headerList) {
            fragmentItems.add(new PreferenceFragmentItem(header.fragment, header.fragment, header.fragmentArguments));
        }
        return fragmentItems;
    }

    /**
     * Parse the given XML file as a header description, adding each
     * parsed Header into the target list. Adopted from AOSP
     *
     * @param resid  The XML resource to load and parse.
     * @param target The list in which the parsed headers should be placed.
     */
    public static void loadHeadersFromResource(@XmlRes int resid, List<PreferenceActivity.Header> target, Activity mActivity) {
        XmlResourceParser parser = null;
        try {
            parser = mActivity.getResources().getXml(resid);
            AttributeSet attrs = Xml.asAttributeSet(parser);

            int type;
            while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
                    && type != XmlPullParser.START_TAG) {
                // Parse next until start tag is found
            }

            String nodeName = parser.getName();
            if (!"preference-headers".equals(nodeName)) {
                throw new RuntimeException(
                        "XML document must start with <preference-headers> tag; found"
                                + nodeName + " at " + parser.getPositionDescription());
            }

            Bundle curBundle = null;

            final int outerDepth = parser.getDepth();
            while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
                    && (type != XmlPullParser.END_TAG || parser.getDepth() > outerDepth)) {
                if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
                    continue;
                }

                nodeName = parser.getName();
                if ("header".equals(nodeName)) {
                    PreferenceActivity.Header header = new PreferenceActivity.Header();

                    TypedArray sa = mActivity.obtainStyledAttributes(
                            attrs, R.styleable.PreferenceHeader);
                    header.id = sa.getResourceId(
                            R.styleable.PreferenceHeader_id,
                            (int) HEADER_ID_UNDEFINED);
                    TypedValue tv = sa.peekValue(
                            R.styleable.PreferenceHeader_title);
                    if (tv != null && tv.type == TypedValue.TYPE_STRING) {
                        if (tv.resourceId != 0) {
                            header.titleRes = tv.resourceId;
                        } else {
                            header.title = tv.string;
                        }
                    }
                    tv = sa.peekValue(
                            R.styleable.PreferenceHeader_summary);
                    if (tv != null && tv.type == TypedValue.TYPE_STRING) {
                        if (tv.resourceId != 0) {
                            header.summaryRes = tv.resourceId;
                        } else {
                            header.summary = tv.string;
                        }
                    }
                    tv = sa.peekValue(
                            R.styleable.PreferenceHeader_breadCrumbTitle);
                    if (tv != null && tv.type == TypedValue.TYPE_STRING) {
                        if (tv.resourceId != 0) {
                            header.breadCrumbTitleRes = tv.resourceId;
                        } else {
                            header.breadCrumbTitle = tv.string;
                        }
                    }
                    tv = sa.peekValue(
                            R.styleable.PreferenceHeader_breadCrumbShortTitle);
                    if (tv != null && tv.type == TypedValue.TYPE_STRING) {
                        if (tv.resourceId != 0) {
                            header.breadCrumbShortTitleRes = tv.resourceId;
                        } else {
                            header.breadCrumbShortTitle = tv.string;
                        }
                    }
                    header.iconRes = sa.getResourceId(
                            R.styleable.PreferenceHeader_icon, 0);
                    header.fragment = sa.getString(
                            R.styleable.PreferenceHeader_fragment);
                    sa.recycle();

                    if (curBundle == null) {
                        curBundle = new Bundle();
                    }

                    final int innerDepth = parser.getDepth();
                    while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
                            && (type != XmlPullParser.END_TAG || parser.getDepth() > innerDepth)) {
                        if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
                            continue;
                        }

                        String innerNodeName = parser.getName();
                        if (innerNodeName.equals("extra")) {
                            mActivity.getResources().parseBundleExtra("extra", attrs, curBundle);
                            XmlUtils.skipCurrentTag(parser);

                        } else if (innerNodeName.equals("intent")) {
                            header.intent = Intent.parseIntent(mActivity.getResources(), parser, attrs);

                        } else {
                            XmlUtils.skipCurrentTag(parser);
                        }
                    }

                    if (curBundle.size() > 0) {
                        header.fragmentArguments = curBundle;
                        curBundle = null;
                    }

                    target.add(header);
                } else {
                    XmlUtils.skipCurrentTag(parser);
                }
            }

        } catch (XmlPullParserException e) {
            throw new RuntimeException("Error parsing headers", e);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing headers", e);
        } finally {
            if (parser != null) parser.close();
        }
    }
}
