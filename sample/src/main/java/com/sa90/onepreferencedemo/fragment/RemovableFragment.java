package com.sa90.onepreferencedemo.fragment;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.view.View;

import com.sa90.onepreference.fragment.BaseOnePreferenceFragment;
import com.sa90.onepreferencedemo.HeaderManipulationActivity;
import com.sa90.onepreferencedemo.R;

/**
 * Created by saurabharora on 20/11/16.
 */

public class RemovableFragment extends BaseOnePreferenceFragment {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_removable, rootKey);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findPreference(getString(R.string.pref_key_remove)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if(getActivity() instanceof HeaderManipulationActivity)
                    ((HeaderManipulationActivity) getActivity()).setShouldRemove(true);
                    ((HeaderManipulationActivity) getActivity()).invalidateHeaders();
                return true;
            }
        });
    }
}
