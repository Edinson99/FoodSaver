# ProGuard rules for FoodSaverCompose project

# Keep the main activity and all the screens
-keep class com.foodsavercompose.MainActivity
-keep class com.foodsavercompose.ui.screens.** { *; }

# Keep the model classes
-keep class com.foodsavercompose.model.** { *; }

# Keep the components
-keep class com.foodsavercompose.ui.components.** { *; }

# Keep all annotations
-keepattributes *Annotation*

# Keep the Kotlin metadata
-keep class kotlin.Metadata { *; }

# Keep the Parcelable interface
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}