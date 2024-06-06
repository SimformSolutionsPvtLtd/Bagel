# keep everything in this package from being removed or renamed
-keep class androidx.** { *; }
# keep everything in this package from being renamed only
-keepnames class androidx.** { *; }
# PubNub
-dontwarn com.pubnub.**
-keep class com.pubnub.** { *; }
# Gson
-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
# OkHttp3
-dontwarn okhttp3.**
-dontwarn org.codehaus.mojo.animal_sniffer.*
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
# Okio
-dontwarn okio.**
# Retrofit 2.X
-dontwarn retrofit2.**
-dontwarn javax.annotation.**
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Keep all members in enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# Keep the names of classes and methods that are used in the RetrofitFactory class through reflection
-keepclassmembers class * {
    *;
}

-printconfiguration