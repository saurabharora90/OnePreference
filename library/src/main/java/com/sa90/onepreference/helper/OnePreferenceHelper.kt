package com.sa90.onepreference.helper

import android.content.Context
import android.content.Intent
import android.content.res.XmlResourceParser
import android.os.Bundle
import android.util.TypedValue
import android.util.Xml
import androidx.annotation.XmlRes
import androidx.core.content.withStyledAttributes
import com.sa90.onepreference.R
import com.sa90.onepreference.model.Header
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

/**
 * The helper parses the header file to create a list of [Header] objects
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

            val outerDepth = parser.depth
            while (parser.next().also { type = it } != XmlPullParser.END_DOCUMENT
                && (type != XmlPullParser.END_TAG || parser.depth > outerDepth)
            ) {
                if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
                    continue
                }
                nodeName = parser.name
                if ("header" == nodeName) {
                    context.withStyledAttributes(attrs, R.styleable.PreferenceHeader) {
                        val header = Header()
                        var curBundle: Bundle? = null

                        header.id = getResourceId(
                            R.styleable.PreferenceHeader_id,
                            HEADER_ID_UNDEFINED.toInt()
                        ).toLong()
                        var tv = peekValue(
                            R.styleable.PreferenceHeader_title
                        )
                        if (tv != null && tv.type == TypedValue.TYPE_STRING) {
                            if (tv.resourceId != 0) {
                                header.titleRes = tv.resourceId
                            } else {
                                header.title = tv.string
                            }
                        }
                        tv = peekValue(
                            R.styleable.PreferenceHeader_summary
                        )
                        if (tv != null && tv.type == TypedValue.TYPE_STRING) {
                            if (tv.resourceId != 0) {
                                header.summaryRes = tv.resourceId
                            } else {
                                header.summary = tv.string
                            }
                        }
                        header.iconRes = getResourceId(
                            R.styleable.PreferenceHeader_icon, 0
                        )
                        header.fragment = getString(
                            R.styleable.PreferenceHeader_fragment
                        )
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
                                parser.skipCurrentTag()
                            } else if (innerNodeName == "intent") {
                                header.intent =
                                    Intent.parseIntent(context.resources, parser, attrs)
                            } else {
                                parser.skipCurrentTag()
                            }
                        }
                        if (curBundle.size() > 0) {
                            header.fragmentArguments = curBundle
                            curBundle = null
                        }
                        target.add(header)
                    }
                } else {
                    parser.skipCurrentTag()
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