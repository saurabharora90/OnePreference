package com.sa90.onepreference.helper

import android.content.Context
import android.content.Intent
import android.content.res.XmlResourceParser
import android.os.Bundle
import android.util.TypedValue
import android.util.Xml
import androidx.annotation.XmlRes
import com.sa90.onepreference.R
import com.sa90.onepreference.model.Header
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

/**
 * This is the helper for doing the heavy lifting to be able to use the Preference v14 support library to work with both Tablets and Phones
 *
 * The process adopted involves following the standard practise of using a two pane layout for tablets
 * but for phone, we parse the header file to extract all the [androidx.preference.PreferenceFragmentCompat]
 * and then place all those fragments in a Normal Activity inside in a vertically oriented LinearLayout.
 * This allows us to ignore the headers on the Phone view and directly display the Preference List.
 */
object OnePreferenceHelper {
    private const val HEADER_ID_UNDEFINED: Long = -1

    /**
     * Parse the given XML file as a header description, adding each
     * parsed Header into the target list. Adopted from AOSP
     *
     * @param resid  The XML resource to load and parse.
     * @param target The list in which the parsed headers should be placed.
     */
    @JvmStatic
    fun loadHeadersFromResource(
        @XmlRes resid: Int,
        target: MutableList<Header>,
        context: Context
    ) {
        var parser: XmlResourceParser? = null
        try {
            parser = context.resources.getXml(resid)
            val attrs = Xml.asAttributeSet(parser)
            var type: Int
            while (parser.next().also { type = it } != XmlPullParser.END_DOCUMENT
                && type != XmlPullParser.START_TAG
            ) {
                // Parse next until start tag is found
            }
            var nodeName = parser.name
            if ("preference-headers" != nodeName) {
                throw RuntimeException(
                    "XML document must start with <preference-headers> tag; found"
                            + nodeName + " at " + parser.positionDescription
                )
            }
            var curBundle: Bundle? = null
            val outerDepth = parser.depth
            while (parser.next().also { type = it } != XmlPullParser.END_DOCUMENT
                && (type != XmlPullParser.END_TAG || parser.depth > outerDepth)
            ) {
                if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
                    continue
                }
                nodeName = parser.name
                if ("header" == nodeName) {
                    val header =
                        Header()
                    val sa = context.obtainStyledAttributes(
                        attrs, R.styleable.PreferenceHeader
                    )
                    header.id = sa.getResourceId(
                        R.styleable.PreferenceHeader_id,
                        HEADER_ID_UNDEFINED.toInt()
                    ).toLong()
                    var tv = sa.peekValue(
                        R.styleable.PreferenceHeader_title
                    )
                    if (tv != null && tv.type == TypedValue.TYPE_STRING) {
                        if (tv.resourceId != 0) {
                            header.titleRes = tv.resourceId
                        } else {
                            header.title = tv.string
                        }
                    }
                    tv = sa.peekValue(
                        R.styleable.PreferenceHeader_summary
                    )
                    if (tv != null && tv.type == TypedValue.TYPE_STRING) {
                        if (tv.resourceId != 0) {
                            header.summaryRes = tv.resourceId
                        } else {
                            header.summary = tv.string
                        }
                    }
                    header.iconRes = sa.getResourceId(
                        R.styleable.PreferenceHeader_icon, 0
                    )
                    header.fragment = sa.getString(
                        R.styleable.PreferenceHeader_fragment
                    )
                    sa.recycle()
                    if (curBundle == null) {
                        curBundle = Bundle()
                    }
                    val innerDepth = parser.depth
                    while (parser.next().also { type = it } != XmlPullParser.END_DOCUMENT
                        && (type != XmlPullParser.END_TAG || parser.depth > innerDepth)
                    ) {
                        if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
                            continue
                        }
                        val innerNodeName = parser.name
                        if (innerNodeName == "extra") {
                            context.resources.parseBundleExtra("extra", attrs, curBundle)
                            XmlUtils.skipCurrentTag(parser)
                        } else if (innerNodeName == "intent") {
                            header.intent =
                                Intent.parseIntent(context.resources, parser, attrs)
                        } else {
                            XmlUtils.skipCurrentTag(parser)
                        }
                    }
                    if (curBundle.size() > 0) {
                        header.fragmentArguments = curBundle
                        curBundle = null
                    }
                    target.add(header)
                } else {
                    XmlUtils.skipCurrentTag(parser)
                }
            }
        } catch (e: XmlPullParserException) {
            throw RuntimeException("Error parsing headers", e)
        } catch (e: IOException) {
            throw RuntimeException("Error parsing headers", e)
        } finally {
            parser?.close()
        }
    }
}