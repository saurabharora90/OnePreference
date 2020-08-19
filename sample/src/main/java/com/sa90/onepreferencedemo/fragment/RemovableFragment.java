package com.sa90.onepreferencedemo.fragment;

import android.os.Bundle;
import android.view.View;

import com.sa90.onepreference.fragment.BaseOnePreferenceFragment;
import com.sa90.onepreferencedemo.HeaderManipulationActivity;
import com.sa90.onepreferencedemo.R;

import org.jetbrains.annotations.NotNull;

public class RemovableFragment extends BaseOnePreferenceFragment {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_removable, rootKey);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findPreference(getString(R.string.pref_key_remove))
                .setOnPreferenceClickListener(preference -> {
                    if (requireActivity() instanceof HeaderManipulationActivity)
                        ((HeaderManipulationActivity) requireActivity()).setShouldRemove(true);
                    ((HeaderManipulationActivity) requireActivity()).invalidateHeaders();
                    return true;
                });
    }
}
