package com.sa90.onepreferencedemo;

import com.sa90.onepreference.OnePreferenceActivity;
import com.sa90.onepreference.model.Header;
import com.sa90.onepreferencedemo.fragment.RemovableFragment;

import java.util.List;

/**
 * Created by saurabharora on 25/11/16.
 */

public class HeaderManipulationActivity extends OnePreferenceActivity {

    boolean shouldRemove = false;

    private static final int ID_HEADER_REMOVABLE = 1000;

    @Override
    public void touchUpHeadersBeforeDisplay(List<Header> targets) {
        if(shouldRemove) {

        }
        else {
            Header header = new Header();
            header.titleRes = R.string.header4;
            header.id = ID_HEADER_REMOVABLE;
            header.fragment = RemovableFragment.class.getName();
            targets.add(header);
        }
    }

    public void setShouldRemove(boolean shouldRemove) {
        this.shouldRemove = shouldRemove;
    }
}
