package com.jihan.shohid.utils

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.res.Configuration
import java.util.Locale

class LanguageUtils(private val activity: Activity) {


    fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration(activity.resources.configuration)
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        activity.resources.updateConfiguration(config, activity.resources.displayMetrics)

        activity.getSharedPreferences("Settings", MODE_PRIVATE).edit().putString("app_lang", lang)
            .apply()
    }


    fun loadLocale() {
        val sharedPreferences = activity.getSharedPreferences("Settings", MODE_PRIVATE)
        val language = sharedPreferences.getString("app_lang", "en") ?: "en"
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration(activity.resources.configuration)
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        activity.resources.updateConfiguration(config, activity.resources.displayMetrics)
    }

}