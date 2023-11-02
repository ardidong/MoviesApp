// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.1" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

ext{
    extra["compose_ui_version"] = "1.4.3"
    extra["nav_version"] = "2.6.0"
    extra["hilt_version"] = "2.44"
    extra["retrofit_version"] = "2.9.0"
    extra["logging_interceptor_version"] = "4.10.0"
    extra["lifecycle_version"] = "2.6.0"
    extra["paging_version"] = "3.2.1"
    extra["mockito_version"] = "5.6.0"
    extra["mockk_version"] = "1.13.8"

    extra["mockk"] = "io.mockk:mockk:${extra["mockk_version"]}"
    extra["mockito"] = "org.mockito:mockito-core:${extra["mockito_version"]}"
    extra["daggerHilt"] = "com.google.dagger:hilt-android:${extra["hilt_version"]}"
    extra["hiltKapt"] = "com.google.dagger:hilt-compiler:${extra["hilt_version"]}"
    extra["retrofit"] = "com.squareup.retrofit2:retrofit:${extra["retrofit_version"]}"
    extra["gsonConverter"] = "com.squareup.retrofit2:converter-gson:${extra["retrofit_version"]}"
    extra["loggingInterceptor"] = "com.squareup.okhttp3:logging-interceptor:${extra["logging_interceptor_version"]}"
    extra["paging"] = "androidx.paging:paging-runtime-ktx:${rootProject.extra["paging_version"]}"
}