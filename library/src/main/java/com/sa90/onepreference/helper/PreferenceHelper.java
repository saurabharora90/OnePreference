package com.sa90.onepreference.helper;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.XmlRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;

import com.sa90.onepreference.R;
import com.sa90.onepreference.model.Header;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Saurabh Arora on 20/11/16.
 *
 * This is the helper for doing the heavy lifting to be able to use the Preference v14 support library to work with both Tablets and Phones
 *
 * The process adopted involves following the standard practise of using a two pane layout for tablets
 * but for phone, we parse the header file to extract all the {@link android.support.v14.preference.PreferenceFragment}
 * and then place all those fragments in a Normal Activity inside in a vertically oriented LinearLayout.
 * This allows us to ignore the headers on the Phone view and directly display the Preference List.
 */

class PreferenceHelper {

    private static final long HEADER_ID_UNDEFINED = -1;

    /**
     * Parse the given XML file as a header description, adding each
     * parsed Header into the target list. Adopted from AOSP
     *
     * @param resid  The XML resource to load and parse.
     * @param target The list in which the parsed headers should be placed.
     */
    public static void loadHeadersFromResource(@XmlRes int resid, List<Header> target, Activity mActivity) {
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
                    Header header = new Header();

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
