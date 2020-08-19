package com.sa90.onepreferencedemo.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.preference.Preference;

import com.sa90.onepreference.fragment.BaseOnePreferenceFragment;
import com.sa90.onepreferencedemo.HeaderManipulationActivity;
import com.sa90.onepreferencedemo.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RemovableFragment extends BaseOnePreferenceFragment {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_removable, rootKey);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(findPreference(getString(R.string.pref_key_remove)))
                .setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        if (requireActivity() instanceof HeaderManipulationActivity)
                            ((HeaderManipulationActivity) requireActivity()).setShouldRemove(true);
                        ((HeaderManipulationActivity) requireActivity()).invalidateHeaders();
                        return true;
                    }
                });
    }
}
