# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/gramian/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

### Okio
-dontwarn okio.**

### Retrofit2
-dontwarn retrofit2.Platform$Java8

# esperandro
-keepnames class de.devland.** { *; }
-keep class **$$Impl { public *;}

# keep the annotated things annotated
-keepattributes *Annotation*, EnclosingMethod, Signature, InnerClasses

# for dagger also preserve the interfaces
# assuming they reside in the sub-package 'preferences' and all end with 'Prefs'
#-keep class preferences.**Prefs { public *;}

# jackson
#-dontwarn com.fasterxml.jackson.databind.**
#-keepnames class com.fasterxml.jackson.** { *; }

# for gson see their documentation at
# https://code.google.com/p/google-gson/source/browse/trunk/examples/android-proguard-example/proguard.cfg