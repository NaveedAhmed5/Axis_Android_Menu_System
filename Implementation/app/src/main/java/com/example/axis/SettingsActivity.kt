package com.example.axis

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.axis.adapters.SettingsAdapter
import com.example.axis.adapters.SettingsAdapter.SettingItem
import com.example.axis.adapters.SettingsAdapter.SettingType
import com.example.axis.utils.PreferenceManager
import com.example.axis.utils.SyncManager

class SettingsActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var settingsRecyclerView: RecyclerView
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var syncManager: SyncManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initializeViews()
        setupToolbar()
        setupSettings()
    }

    private fun initializeViews() {
        toolbar = findViewById(R.id.toolbar)
        settingsRecyclerView = findViewById(R.id.settingsRecyclerView)
        preferenceManager = PreferenceManager(this)
        syncManager = SyncManager(this)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupSettings() {
        val settings = listOf(
            SettingItem(
                "Grid Size",
                "Current: ${preferenceManager.gridRows} x ${preferenceManager.gridColumns}",
                SettingType.GRID_SIZE
            ),
            SettingItem(
                "Icon Size",
                "Current: ${preferenceManager.iconSize}dp",
                SettingType.ICON_SIZE
            ),
            SettingItem(
                "Show Labels",
                if (preferenceManager.showLabels) "Labels are shown" else "Labels are hidden",
                SettingType.SHOW_LABELS
            ),
            SettingItem(
                "Drawer Mode",
                if (preferenceManager.drawerMode) "App drawer enabled" else "Classic mode (all apps on home)",
                SettingType.DRAWER_MODE
            ),
            SettingItem(
                "Appearance",
                "Customize theme and colors",
                SettingType.APPEARANCE
            ),
            SettingItem(
                "Gestures & Inputs",
                "Configure swipe gestures",
                SettingType.GESTURES
            ),
            SettingItem(
                "Profile",
                "User info & stats",
                SettingType.PROFILE
            ),
            SettingItem(
                "Backup & Restore",
                "Manage local backups",
                SettingType.BACKUP_RESTORE
            ),
            SettingItem(
                "Cloud Sync",
                "Sync launcher data",
                SettingType.CLOUD_SYNC
            ),
            SettingItem(
                "Notifications",
                "View recent alerts",
                SettingType.NOTIFICATIONS
            ),
            SettingItem(
                "Privacy & Security",
                "Lock & permissions",
                SettingType.PRIVACY_SECURITY
            ),
            SettingItem(
                "About",
                "App info & credits",
                SettingType.ABOUT
            ),
            SettingItem(
                "Icon Packs",
                "Manage icon themes",
                SettingType.ICON_PACKS
            ),
            SettingItem(
                "Wallpapers",
                "Set app background",
                SettingType.WALLPAPERS
            )
        )

        settingsRecyclerView.layoutManager = LinearLayoutManager(this)
        settingsRecyclerView.adapter = SettingsAdapter(settings) { settingItem ->
            handleSettingClick(settingItem)
        }
    }

    private fun handleSettingClick(settingItem: SettingItem) {
        when (settingItem.type) {
            SettingType.GRID_SIZE -> showGridSizeDialog()
            SettingType.ICON_SIZE -> showIconSizeDialog()
            SettingType.SHOW_LABELS -> toggleShowLabels()
            SettingType.DRAWER_MODE -> toggleDrawerMode()
            SettingType.APPEARANCE -> showAppearanceSettings()
            SettingType.GESTURES -> showGestureSettings()
            SettingType.PROFILE -> launch(ProfileActivity::class.java)
            SettingType.BACKUP_RESTORE -> launch(BackupRestoreActivity::class.java)
            SettingType.CLOUD_SYNC -> launch(CloudSyncActivity::class.java)
            SettingType.NOTIFICATIONS -> launch(NotificationsActivity::class.java)
            SettingType.PRIVACY_SECURITY -> launch(PrivacySecurityActivity::class.java)
            SettingType.ABOUT -> launch(AboutActivity::class.java)
            SettingType.ICON_PACKS -> launch(IconPacksActivity::class.java)
            SettingType.WALLPAPERS -> launch(WallpapersActivity::class.java)
        }
    }

    private fun showGridSizeDialog() {
        val rows = arrayOf("4", "5", "6", "7")
        val columns = arrayOf("3", "4", "5")
        
        AlertDialog.Builder(this)
            .setTitle("Grid Size")
            .setMessage("Current: ${preferenceManager.gridRows} x ${preferenceManager.gridColumns}")
            .setPositiveButton("Change Columns") { _, _ ->
                showColumnDialog()
            }
            .setNegativeButton("Change Rows") { _, _ ->
                showRowDialog()
            }
            .setNeutralButton("Cancel", null)
            .show()
    }

    private fun showRowDialog() {
        val rows = arrayOf("4", "5", "6", "7")
        AlertDialog.Builder(this)
            .setTitle("Select Rows")
            .setItems(rows) { _, which ->
                preferenceManager.gridRows = rows[which].toInt()
                Toast.makeText(this, "Grid rows set to ${rows[which]}", Toast.LENGTH_SHORT).show()
                syncManager.syncSettings()
                setupSettings() // Refresh
            }
            .show()
    }

    private fun showColumnDialog() {
        val columns = arrayOf("3", "4", "5")
        AlertDialog.Builder(this)
            .setTitle("Select Columns")
            .setItems(columns) { _, which ->
                preferenceManager.gridColumns = columns[which].toInt()
                Toast.makeText(this, "Grid columns set to ${columns[which]}", Toast.LENGTH_SHORT).show()
                syncManager.syncSettings()
                setupSettings() // Refresh
            }
            .show()
    }

    private fun showIconSizeDialog() {
        val sizes = arrayOf("Small (60dp)", "Medium (70dp)", "Large (80dp)", "Extra Large (90dp)")
        val sizeValues = arrayOf(60, 70, 80, 90)
        
        AlertDialog.Builder(this)
            .setTitle("Icon Size")
            .setItems(sizes) { _, which ->
                preferenceManager.iconSize = sizeValues[which]
                Toast.makeText(this, "Icon size set to ${sizes[which]}", Toast.LENGTH_SHORT).show()
                syncManager.syncSettings()
                setupSettings() // Refresh
            }
            .show()
    }

    private fun toggleShowLabels() {
        preferenceManager.showLabels = !preferenceManager.showLabels
        Toast.makeText(
            this,
            if (preferenceManager.showLabels) "Labels shown" else "Labels hidden",
            Toast.LENGTH_SHORT
        ).show()
        syncManager.syncSettings()
        setupSettings() // Refresh
    }

    private fun toggleDrawerMode() {
        preferenceManager.drawerMode = !preferenceManager.drawerMode
        Toast.makeText(
            this,
            if (preferenceManager.drawerMode) "App drawer enabled" else "Classic mode enabled",
            Toast.LENGTH_SHORT
        ).show()
        syncManager.syncSettings()
        setupSettings() // Refresh
    }

    private fun showAppearanceSettings() {
        val themes = arrayOf("Light Theme", "Dark Theme", "Sunset Theme")
        val currentTheme = preferenceManager.getTheme()
        val checkedItem = when (currentTheme) {
            "Dark Theme" -> 1
            "Sunset Theme" -> 2
            else -> 0
        }

        AlertDialog.Builder(this)
            .setTitle("Select Theme")
            .setSingleChoiceItems(themes, checkedItem) { dialog, which ->
                val selectedTheme = themes[which]
                preferenceManager.setTheme(selectedTheme)
                syncManager.syncSettings()
                Toast.makeText(this, "$selectedTheme Applied. Restarting...", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                
                // Restart activity to apply theme
                val intent = intent
                finish()
                startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showGestureSettings() {
        val gestures = arrayOf("Swipe Up", "Swipe Down", "Double Tap")
        
        AlertDialog.Builder(this)
            .setTitle("Configure Gestures")
            .setItems(gestures) { _, which ->
                when (which) {
                    0 -> showSwipeUpActionDialog()
                    1 -> showSwipeDownActionDialog()
                    2 -> showDoubleTapActionDialog()
                }
            }
            .setPositiveButton("Close", null)
            .show()
    }

    private fun showSwipeUpActionDialog() {
        val actions = arrayOf("App Drawer", "None")
        AlertDialog.Builder(this)
            .setTitle("Swipe Up Action")
            .setSingleChoiceItems(actions, 0) { dialog, _ ->
                // Save preference (mock)
                Toast.makeText(this, "Swipe Up Action Set", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .show()
    }

    private fun showSwipeDownActionDialog() {
        val actions = arrayOf("Notifications", "None")
        AlertDialog.Builder(this)
            .setTitle("Swipe Down Action")
            .setSingleChoiceItems(actions, 0) { dialog, _ ->
                // Save preference (mock)
                Toast.makeText(this, "Swipe Down Action Set", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .show()
    }

    private fun showDoubleTapActionDialog() {
        val actions = arrayOf("Settings", "Screen Lock", "None")
        AlertDialog.Builder(this)
            .setTitle("Double Tap Action")
            .setSingleChoiceItems(actions, 0) { dialog, _ ->
                // Save preference (mock)
                Toast.makeText(this, "Double Tap Action Set", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .show()
    }

    private fun <T> launch(cls: Class<T>) {
        startActivity(android.content.Intent(this, cls))
    }
}
