object Dependencies {

    object AppPlugins {
        const val application = "com.android.application"
        const val jetbrainsKotlinAndroid = "org.jetbrains.kotlin.android"
        const val hiltAndroid = "dagger.hilt.android.plugin"
        const val kotlinKapt = "kotlin-kapt"
        const val androidLibrary = "com.android.library"
    }

    object GradlePlugin {
        const val hiltAndroid = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

        const val application = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
    }

    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    private const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    // image loader
    private const val coil = "io.coil-kt:coil:${Versions.coil}"

    // networking
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    private const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    private const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    private const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    private const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    // dependency injection
    private const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    private const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    private const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // unit testing
    private const val testJUnit = "junit:junit:${Versions.testJUnit}"

    // UI testing
    private const val testExt = "androidx.test.ext:junit:${Versions.testExt}"
    private const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    val appDependencies = listOf(
        coreKtx,
        appCompat,
        material,
        constraintLayout,
        activityKtx,
        fragmentKtx,
        coil,
        retrofit,
        okhttp3,
        okHttpInterceptor,
        moshi,
        moshiKotlin,
        hiltAndroid,
        timber
    )
    val appKaptDependencies = listOf(
        moshiKotlinCodegen,
        hiltAndroidCompiler
    )
    val networkingDependencies = listOf(
        coreKtx,
        retrofit,
        okhttp3,
        okHttpInterceptor,
        moshi,
        moshiKotlin,
        hiltAndroid,
        timber
    )
    val networkingKaptDependencies = listOf(
        moshiKotlinCodegen,
        hiltAndroidCompiler
    )
    val zoogleDependencies = listOf(
        coreKtx,
        appCompat,
        material
    )
    val testDependencies = listOf(testJUnit)
    val androidTestDependencies = listOf(
        testExt,
        espresso
    )
}