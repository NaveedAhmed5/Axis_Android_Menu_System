package com.example.axis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.axis.utils.PreferenceManager

open class BaseActivity : AppCompatActivity() {
    lateinit var basePreferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        basePreferenceManager = PreferenceManager(this)
        applyTheme()
        super.onCreate(savedInstanceState)
    }

    private fun applyTheme() {
        val themeName = basePreferenceManager.getTheme()
        when (themeName) {
            "Dark Theme" -> setTheme(R.style.Theme_Axis_Dark)
            "Sunset Theme" -> setTheme(R.style.Theme_Axis_Sunset)
            else -> setTheme(R.style.Theme_Axis) // Light/Default
        }
    }
    
    override fun onResume() {
        super.onResume()
        applyWallpaper()
    }

    private fun applyWallpaper() {
        val wallpaperName = basePreferenceManager.getWallpaper()
        val rootView = window.decorView.rootView
        
        if (wallpaperName.startsWith("content://") || wallpaperName.startsWith("file://")) {
            try {
                val uri = android.net.Uri.parse(wallpaperName)
                val inputStream = contentResolver.openInputStream(uri)
                val drawable = android.graphics.drawable.Drawable.createFromStream(inputStream, uri.toString())
                rootView.background = drawable
                inputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
                // Fallback to default if image load fails
                rootView.setBackgroundColor(android.graphics.Color.TRANSPARENT)
            }
        } else {
            // Map wallpaper names to colors
            val color = when (wallpaperName) {
                "Ocean Sunset" -> android.graphics.Color.parseColor("#5E60CE")
                "Rose Garden" -> android.graphics.Color.parseColor("#E63946")
                "Forest Mist" -> android.graphics.Color.parseColor("#06A77D")
                "Fire Sky" -> android.graphics.Color.parseColor("#FF6D00")
                "Deep Blue" -> android.graphics.Color.parseColor("#4361EE")
                "Aurora" -> android.graphics.Color.parseColor("#B5179E")
                "White" -> android.graphics.Color.WHITE
                else -> android.graphics.Color.TRANSPARENT // Default/None
            }
            
            if (color != android.graphics.Color.TRANSPARENT) {
                rootView.setBackgroundColor(color)
            } else {
                // Reset to theme default if needed
                // We can't easily "unset" a background color to theme default without recreating,
                // but setting it to null or transparent usually reveals the window background.
                // However, since we are setting the window background itself, we might want to clear it.
                // For now, let's assume transparent means "no custom wallpaper".
                rootView.setBackgroundColor(android.graphics.Color.WHITE) // Or theme default
            }
        }
    }
}
