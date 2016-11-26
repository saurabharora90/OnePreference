OnePreference
=================
A library to abstract out the preference implementation for Tablets and Phones. It presents a single list of all the preferences on the phone and the usual two pane layout on tablets. See the blog post here: https://medium.com/@saurabharora90/one-pref-for-androids-pride-b254e79f7d88

![Tablet - Phone Layout](https://github.com/saurabharora90/OnePreference/blob/develop/screenshot.png?raw=true)

Change-logs
-------
Check out the [Release Notes](https://github.com/saurabharora90/OnePreference/releases "Releases") for the change-logs in each release.

Usage
-------
Add a dependency to your `build.gradle`:

    dependencies {
    compile 'com.sa90.onepreference:library:1.0.1'
}

The library provides an abstract class (BaseOnePreferenceActivity) and an out of box implementation (OnePreferenceActivity). If you are comfortable with using the out of box implementation, then include it in your manifest file.

    <activity android:name="com.sa90.onepreference.OnePreferenceActivity" />

Also remember to setup the preference and toolbar style for the OnePreferenceActivity. We are using the material theme for the preference.

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="preferenceTheme">@style/PreferenceThemeOverlay.v14.Material</item>
        <item name="onePref_toolbarStyle">@style/AppTheme.AppBarOverlay</item>
    </style>

Next you would a headers.xml file (just like the PreferenceActivity) along with their corresponding fragments. 
**Note**: The preference fragments should extend from BaseOnePreferenceFragment.

    <?xml version="1.0" encoding="utf-8"?>
    <preference-headers xmlns:onepref="http://schemas.android.com/apk/res-auto">

    <header onepref:fragment="com.sa90.onepreferencedemo.fragment.Header1Fragment"
        onepref:title="@string/header1" />
        
    <header onepref:fragment="com.sa90.onepreferencedemo.fragment.Header2Fragment"
        onepref:title="@string/header2" />
        
    <header onepref:fragment="com.sa90.onepreferencedemo.fragment.AboutFragment"
        onepref:title="@string/header3" />
    
    </preference-headers>

Lastly you can start the OnePreferenceActivity by using the OnePreferenceHelper

    OnePreferenceHelper.startActivity(@XmlRes int headerRes, String title, Activity callingActivity)

Check out the [Wiki](https://github.com/saurabharora90/OnePreference/wiki) if you need more information about how to customise the out of box toolbar or how to use your own layout for the PreferencesActivity.
License
-------

    Copyright 2016 Saurabh Arora

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
