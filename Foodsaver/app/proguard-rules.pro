# ProGuard rules for the Foodsaver app

# Keep the main application class
-keep class com.foodsaver.MainActivity { *; }

# Keep all activities
-keep class com.foodsaver.ui.** { *; }

# Keep all model classes
-keep class com.foodsaver.model.** { *; }

# Keep all string resources
-keep class * {
    public static <fields>;
}

# Keep annotations
-keepattributes *Annotation*

# Keep the names of the classes and methods for reflection
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep the Gson model classes
-keep class com.foodsaver.model.** { *; }

# Keep the notification channel
-keep class android.app.NotificationChannel { *; }

# Keep the notification manager
-keep class android.app.NotificationManager { *; }

# Keep the application class
-keep class com.foodsaver.App { *; }