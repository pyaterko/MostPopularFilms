import org.gradle.api.JavaVersion

object Config {
    const val application_id =
        "com.owl_laugh_at_wasted_time.mostpopularfilms"
    const val compile_sdk = 32
    const val min_sdk = 24
    const val target_sdk = 32
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
}

object Versions {

    //ViewBindingDelegate
    const val viewBindingDelegate = "1.5.6"

    //glide
    const val glide = "4.12.0"
    const val glideCompiler = "4.12.0"

    //Fragment
    const val fragment = "1.5.0"
    const val navigationFragmentKtx = "2.5.0"

    //Design
    const val appcompat = "1.4.2"
    const val material = "1.6.1"

    //Kotlin
    const val core = "1.8.0"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.6.1"
    const val coroutinesAndroid = "1.6.1"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "3.12.1"
    const val adapterCoroutines = "0.9.2"

    //okhttp3
    const val okhttp = "4.9.3"

    //Dagger
    const val dagger = "2.40"
    const val daggerCompiler = "2.40"


    //Room
    const val roomKtx = "2.4.2"
    const val runtime = "2.4.2"
    const val roomCompiler = "2.4.2"

    //Test
    const val jUnit = "4.13.2"
    const val testExt = "1.1.3"
    const val espressoCore = "3.4.0"

    //navigation
    const val navigation = "2.3.5"

    //lifecycle
    const val lifecycle = "2.4.1"

}

object Lifecycle {
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
}

//управляет жизненным циклом ViewBinding и очищает ссылку на него для предотвращения утечек памяти
object ViewBindingDelegate {
    const val viewbindingpropertydelegate =
       "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBindingDelegate}"
    const val noreflection =
        "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBindingDelegate}"
}

object Fragment {
    const val fragmentktx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val navigation_fragment_ktx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentKtx}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideIntegration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson =
        "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
}

object Okhttp {
    const val okhttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler =
        "com.google.dagger:dagger-compiler:${Versions.daggerCompiler}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val testext = "androidx.test.ext:junit:${Versions.testExt}"
    const val espresso =
        "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Navigation {
    const val navFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object UnitRunner {
    const val unitRunner = "androidx.test.runner.AndroidJUnitRunner"

}
