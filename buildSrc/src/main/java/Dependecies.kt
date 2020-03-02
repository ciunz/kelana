/**
 * Created by korneliussendy on 01/03/20,
 * at 09.50.
 * My Application
 */
@Suppress("UNUSED_PARAMETER")
object Version {
    const val kotlin = "1.3.61"
    const val kotlinCoroutines = "1.0.1"
    const val androidSupport = "28.0.0"
    const val design = "28.0.0"
    const val constraint = "1.1.3"
    const val recyclerView = "1.1.0-beta04"
    const val playServiceBase = "17.1.0"

    const val glide = "4.10.0"
    const val dagger = "2.24"
    const val gson = "2.8.6"
    const val chrome = "28.0.0"
    const val browser = "1.0.0"
    const val multiDex = "1.0.3"

    const val lifeCycle = "2.2.0"

    const val androidX = "1.1.0"
    const val material = "1.1.0-beta01"
    const val viewPager = "1.0.0-rc01"
    const val swipeRefreshLayout = "1.1.0-alpha03"

    const val rxJava = "2.2.13"
    const val rxAndroid = "2.1.1"
    const val rxKotlin = "2.4.0"

    const val timber = "4.7.1"

    //firebase
    const val fAnalytics = "17.2.3"
    const val fStorage = "19.1.1"
    const val fAuth = "19.2.0"
    const val fDatabase = "19.2.1"
    const val fFirestore = "19.0.2"

    //testing
    const val jUnit = "4.12"
    const val testRunner = "1.2.0-beta01"
    const val espresso = "3.1.0"
    const val mockito = "2.8.47"
    const val mockKotlin = "2.1.0"
    const val mockTestRunner = "0.3.1"
}


object AndroidSupport {
    const val design = "com.android.support:design:${Version.androidSupport}"
    const val constraint = "com.android.support.constraint:constraint-layout:${Version.constraint}"
    const val support = "com.android.support:support-v4:${Version.androidSupport}"
    const val playServiceBase =
        "com.google.android.gms:play-services-base:${Version.playServiceBase}"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${Version.androidX}"
    const val androidCore = "androidx.core:core-ktx:${Version.androidX}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Version.constraint}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val chrome = "com.android.support:customtabs:${Version.chrome}"
    const val browser = "androidx.browser:browser:${Version.browser}"
    const val multiDex = "com.android.support:multidex:${Version.multiDex}"
    const val viewPager = "androidx.viewpager2:viewpager2:${Version.viewPager}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"
}

object Lifecycle {
    const val extensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifeCycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycle}"
}

object Firebase {
    const val core = ""
    const val analytics = "com.google.firebase:firebase-analytics:${Version.fAnalytics}"
    const val auth = "com.google.firebase:firebase-auth:${Version.fAuth}"
    const val storage = "com.google.firebase:firebase-storage-ktx:${Version.fStorage}"
    const val database = "com.google.firebase:firebase-database-ktx:${Version.fDatabase}"
    const val firestore = "com.google.firebase:firebase-functions-ktx:${Version.fFirestore}"
}

object Timber{
    const val timber = "com.jakewharton.timber:timber:${Version.timber}"
}

object Gson {
    const val gson = "com.google.code.gson:gson:${Version.gson}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Version.glide}"
}

object RXJava {
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Version.rxJava}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Version.dagger}"
    const val android = "com.google.dagger:dagger-android:${Version.dagger}"
    const val androidSupport = "com.google.dagger:dagger-android-support:${Version.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
    const val processor = "com.google.dagger:dagger-android-processor:${Version.dagger}"
}

object Kotlin {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutines}"
}

object Reactivex {
    const val android = "io.reactivex.rxjava2:rxandroid:${Version.rxAndroid}"
    const val kotlin = "io.reactivex.rxjava2:rxkotlin:${Version.rxKotlin}"
}

object Testing {
    const val jUnit = "junit:junit:${Version.jUnit}"
    const val testRunner = "androidx.test:runner:${Version.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
    const val mockito = "org.mockito:mockito-inline:${Version.mockito}"
    const val mockKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockKotlin}"
    const val mockKtRunner = "de.jodamob.kotlin:kotlin-runner-junit4:${Version.mockTestRunner}"
}
