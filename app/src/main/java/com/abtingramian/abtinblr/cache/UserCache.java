package com.abtingramian.abtinblr.cache;

import de.devland.esperandro.SharedPreferenceMode;
import de.devland.esperandro.annotations.Default;
import de.devland.esperandro.annotations.SharedPreferences;

@SharedPreferences(name = "userPrefs", mode = SharedPreferenceMode.PRIVATE)
public interface UserCache {

    @Default(ofBoolean = false)
    boolean loggedIn();

    boolean loggedIn(boolean superFancyPreferenceValue);

}
