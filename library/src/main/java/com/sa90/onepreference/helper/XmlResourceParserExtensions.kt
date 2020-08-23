package com.sa90.onepreference.helper

import org.xmlpull.v1.XmlPullParser

internal fun XmlPullParser.skipCurrentTag(): Unit {
    val outerDepth: Int = depth
    var type: Int
    while (next().also { type = it } != XmlPullParser.END_DOCUMENT
        && (type != XmlPullParser.END_TAG
                || depth > outerDepth)
    ) {
    }
}