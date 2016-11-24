package com.sa90.onepreference.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.XmlRes;

/**
 * Created by Saurabh Arora on 20/11/16.
 */

interface OnePreference {

    /**
     * Get the id of the header file that contains the information about the preference.
     * Have a look at the sample to understand how to create the Header File.
     *
     * @return
     */
    @NonNull @XmlRes
    int getHeaderFile();
}
