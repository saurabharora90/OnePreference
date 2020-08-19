package com.sa90.onepreference.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.XmlRes;

interface OnePreference {

    /**
     * Get the id of the header file that contains the information about the preference.
     * Have a look at the sample to understand how to create the Header File.
     *
     * @return
     */
    @NonNull
    @XmlRes
    int getHeaderFile();
}
