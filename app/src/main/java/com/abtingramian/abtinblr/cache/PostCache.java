package com.abtingramian.abtinblr.cache;

import com.abtingramian.abtinblr.model.PostItem;

import java.util.ArrayList;

import de.devland.esperandro.SharedPreferenceMode;
import de.devland.esperandro.annotations.SharedPreferences;

@SharedPreferences(name = "postPrefs", mode = SharedPreferenceMode.PRIVATE)
public interface PostCache {

    void postList(ArrayList<PostItem> stringList);

    ArrayList<PostItem> postList();

}
