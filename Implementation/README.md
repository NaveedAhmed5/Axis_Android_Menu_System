# Axis - Android Launcher

A modern, customizable Android launcher application built with Kotlin.

## Features Implemented

### ✅ Core Features
- **Splash Screen** - Animated splash screen with app branding
- **Home Screen** - Clean home screen with favorites and recent apps
- **App Drawer** - Full app drawer with all installed applications
- **Search Functionality** - Real-time search across all installed apps
- **Favorites Management** - Add/remove apps to/from favorites
- **Settings** - Comprehensive settings for customization

### ✅ Customization Options
- **Grid Size** - Adjust rows and columns (4-7 rows, 3-5 columns)
- **Icon Size** - Choose from Small (60dp) to Extra Large (90dp)
- **Show/Hide Labels** - Toggle app name labels
- **Drawer Mode** - Switch between drawer mode and classic mode
- **Appearance Settings** - (Coming soon)
- **Gesture Controls** - (Coming soon)

### ✅ User Interface
- Material Design 3 components
- Smooth animations and transitions
- RecyclerView-based efficient list rendering
- Bottom sheet dialogs for app actions
- Responsive grid layouts
- Search with real-time filtering

### ✅ App Management
- Launch any installed app
- View app information
- Uninstall apps
- Add apps to favorites
- Long-press context menus
- App icon display with labels

## Architecture

### Project Structure
```
app/
├── adapters/
│   ├── AppsAdapter.kt - RecyclerView adapter for app icons
│   └── SettingsAdapter.kt - RecyclerView adapter for settings
├── dialogs/
│   └── AppOptionsBottomSheet.kt - Bottom sheet for app actions
├── models/
│   ├── AppInfo.kt - Data class for app information
│   └── WidgetInfo.kt - Data class for widgets (future)
├── utils/
│   ├── AppManager.kt - Manages installed apps
│   └── PreferenceManager.kt - SharedPreferences wrapper
└── Activities:
    ├── MainActivity.kt - Splash screen
    ├── HomeActivity.kt - Main home screen
    ├── AppDrawerActivity.kt - App drawer with search
    └── SettingsActivity.kt - Settings screen
```

### Key Components

#### AppManager
- Retrieves all installed applications
- Launches apps by package name
- Provides app metadata (name, icon, package)

#### PreferenceManager
- Stores user preferences (grid size, icon size, etc.)
- Manages favorite apps list
- Provides easy access to settings

#### RecyclerView Usage
All lists use RecyclerView for optimal performance:
- Home screen favorites (horizontal scroll)
- Home screen recent apps (grid)
- App drawer (grid with search)
- Settings list (linear)

## Permissions

### Required Permissions
- `QUERY_ALL_PACKAGES` - Required to list all installed applications (Android 11+)

## Build Configuration

### Requirements
- Android Studio Hedgehog or later
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 36 (Android 14+)
- Kotlin 2.0.21
- Gradle 8.13

### Dependencies
```kotlin
// Core AndroidX
androidx.core:core-ktx:1.17.0
androidx.appcompat:appcompat:1.7.1
androidx.constraintlayout:constraintlayout:2.2.1
androidx.recyclerview:recyclerview:1.4.0
androidx.cardview:cardview:1.0.0
androidx.coordinatorlayout:coordinatorlayout:1.2.0

// Material Design
com.google.android.material:material:1.13.0

// Activity
androidx.activity:activity:1.11.0
```

## How to Build

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Implementation
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the Implementation folder

3. **Sync Gradle**
   - Android Studio will automatically sync Gradle
   - Wait for dependencies to download

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click Run (Shift+F10)

## Usage Guide

### Setting as Default Launcher
1. Go to Settings > Apps > Default Apps
2. Select "Home app"
3. Choose "Axis Launcher"

### Managing Favorites
1. Long-press any app icon
2. Select "Add to Favorites"
3. Access favorites from home screen

### Customizing Layout
1. Tap Settings icon on home screen
2. Choose "Grid Size" to adjust layout
3. Choose "Icon Size" to resize icons
4. Toggle "Show Labels" to hide/show app names

### Searching Apps
1. Tap the search bar on home screen or
2. Open app drawer and use search
3. Type to filter apps in real-time

## Future Enhancements

### Planned Features
- [ ] Widget support
- [ ] Folder management
- [ ] Custom themes and colors
- [ ] Gesture controls (swipe up, double tap, etc.)
- [ ] App shortcuts
- [ ] Notification badges
- [ ] Backup and restore settings
- [ ] Cloud sync
- [ ] Multiple home screens
- [ ] Wallpaper picker

### Coming Soon
- Theme customization
- Dark mode support
- Icon packs support
- Gesture configuration
- Performance optimizations

## Known Issues

### Current Limitations
- Widgets not yet implemented
- Folders not available
- No custom themes (only default purple theme)
- Gestures not configured
- No notification badges

## Contributing

Contributions are welcome! Please follow these guidelines:
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is part of a university course assignment.

## Credits

- **Development Team**: [Student IDs: i22-1271, i22-1093, i22-0889]
- **Course**: Software for Mobile Devices
- **Institution**: [Your University Name]
- **Date**: November 2025

## Support

For issues, questions, or suggestions:
- Create an issue in the repository
- Contact the development team

---

**Version**: 1.0  
**Last Updated**: November 21, 2025
