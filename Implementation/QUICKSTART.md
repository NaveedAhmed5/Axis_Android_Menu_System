# 🚀 Axis Launcher - Quick Start Guide

## Prerequisites

Before building, ensure you have:
- ✅ Android Studio (Hedgehog 2023.1.1 or later)
- ✅ Android SDK Platform 36 (Android 14)
- ✅ Android SDK Build Tools 34.0.0+
- ✅ JDK 11 or higher

---

## 🔧 Setup Instructions

### Method 1: Using Android Studio (Recommended)

1. **Open the Project**
   ```
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to: d:\Project_SMD\Project\Implementation
   - Click "OK"
   ```

2. **Sync Gradle**
   ```
   - Android Studio will prompt to sync Gradle
   - Click "Sync Now"
   - Wait for dependencies to download (first time may take 5-10 minutes)
   ```

3. **Configure SDK**
   ```
   - Go to File > Project Structure
   - Under "SDK Location", ensure Android SDK is set
   - If not set, Android Studio will auto-configure it
   ```

4. **Run the App**
   ```
   - Connect an Android device via USB (enable USB debugging)
   - OR start an Android Virtual Device (AVD)
   - Click the "Run" button (Green Play icon)
   - OR press Shift + F10
   ```

---

### Method 2: Command Line Build

1. **Set ANDROID_HOME Environment Variable**
   
   **Windows PowerShell:**
   ```powershell
   $env:ANDROID_HOME = "C:\Users\<YourUsername>\AppData\Local\Android\Sdk"
   ```

   **Windows Command Prompt:**
   ```cmd
   set ANDROID_HOME=C:\Users\<YourUsername>\AppData\Local\Android\Sdk
   ```

2. **Build the APK**
   ```powershell
   cd "d:\Project_SMD\Project\Implementation"
   .\gradlew clean assembleDebug
   ```

3. **Install on Device**
   ```powershell
   .\gradlew installDebug
   ```

4. **Find the APK**
   ```
   Location: app\build\outputs\apk\debug\app-debug.apk
   ```

---

## 📱 Running on Device

### Enable Developer Options (if not already enabled)
1. Go to Settings > About Phone
2. Tap "Build Number" 7 times
3. Go back to Settings > Developer Options
4. Enable "USB Debugging"

### Install via Android Studio
1. Connect device via USB
2. Allow USB debugging on device
3. Device will appear in Android Studio
4. Click Run

### Install APK Manually
1. Build the APK (see above)
2. Transfer APK to device
3. Open APK on device
4. Allow installation from unknown sources if prompted
5. Install and open

---

## 🧪 Testing on Emulator

### Create an AVD (Android Virtual Device)
1. In Android Studio, click Tools > Device Manager
2. Click "Create Device"
3. Select a phone model (e.g., Pixel 6)
4. Select System Image: API 34 or 36 (Android 14)
5. Click "Finish"
6. Click "Play" button to start emulator

### Run on Emulator
1. Start the emulator
2. Wait for it to fully boot
3. Click "Run" in Android Studio
4. Select the running emulator
5. App will install and launch automatically

---

## 🎯 Setting as Default Launcher

After installation:

1. Press the Home button on your device
2. Android will ask "Which app do you want to use?"
3. Select "Axis Launcher"
4. Choose "Always" or "Just once"

**To make it permanent:**
1. Go to Settings > Apps > Default Apps
2. Tap "Home app"
3. Select "Axis Launcher"

---

## 📦 What You Get

### Installed App Features
- ✅ Custom home screen with favorites
- ✅ Full app drawer with search
- ✅ Customizable grid layout
- ✅ Adjustable icon sizes
- ✅ Favorites management
- ✅ Settings screen
- ✅ App launch, info, and uninstall

### First Launch Experience
1. **Splash Screen** (3 seconds)
   - Axis logo and tagline
   
2. **Home Screen**
   - Greeting based on time of day
   - Current date
   - Favorites section (empty initially)
   - Recent apps grid
   - Floating action button for app drawer

3. **Initial Setup**
   - Add favorite apps by long-pressing
   - Access settings to customize
   - Use search to find apps quickly

---

## ⚙️ Configuration Options

### Grid Size
```
Rows: 4, 5, 6, or 7
Columns: 3, 4, or 5
Default: 5 rows x 4 columns
```

### Icon Size
```
Small: 60dp
Medium: 70dp (default)
Large: 80dp
Extra Large: 90dp
```

### Display Options
```
Show Labels: On/Off
Drawer Mode: Enabled/Disabled
```

---

## 🔍 Troubleshooting

### "SDK location not found" Error
**Solution:**
- Open Android Studio
- Let it configure SDK automatically
- Or manually set ANDROID_HOME environment variable

### Build Fails with "compileSdk 36 not found"
**Solution:**
```
1. Open Android Studio
2. Go to Tools > SDK Manager
3. Install Android SDK Platform 36
4. Sync Gradle again
```

### App Crashes on Launch
**Solution:**
```
1. Check Android version (must be Android 7.0+)
2. Grant all permissions if prompted
3. Check logcat for error details
```

### "Unable to list installed apps"
**Solution:**
```
1. Ensure QUERY_ALL_PACKAGES permission is granted
2. On Android 11+, this is automatically handled
3. Try uninstalling and reinstalling
```

### No apps shown in drawer
**Solution:**
```
1. Grant "Display over other apps" permission
2. Restart the app
3. Check if other launcher apps work
```

---

## 📊 Build Variants

### Debug Build (Development)
```bash
.\gradlew assembleDebug
```
- Debugging enabled
- ProGuard disabled
- Larger APK size

### Release Build (Production)
```bash
.\gradlew assembleRelease
```
- Optimized code
- ProGuard enabled
- Smaller APK size
- Requires signing configuration

---

## 🛠️ Development Setup

### Required Android Studio Plugins
- Kotlin (pre-installed)
- Android SDK Tools
- Android SDK Platform-Tools

### Useful Commands
```powershell
# Clean build
.\gradlew clean

# Build debug APK
.\gradlew assembleDebug

# Install on connected device
.\gradlew installDebug

# Uninstall from device
.\gradlew uninstallDebug

# Run tests
.\gradlew test

# Check for updates
.\gradlew dependencyUpdates
```

---

## 📝 Project Structure Quick Reference

```
Implementation/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/axis/
│   │   │   │   ├── MainActivity.kt           ← Splash screen
│   │   │   │   ├── HomeActivity.kt           ← Main launcher
│   │   │   │   ├── AppDrawerActivity.kt      ← All apps
│   │   │   │   ├── SettingsActivity.kt       ← Settings
│   │   │   │   ├── adapters/                 ← RecyclerView adapters
│   │   │   │   ├── models/                   ← Data models
│   │   │   │   ├── utils/                    ← Utility classes
│   │   │   │   └── dialogs/                  ← Bottom sheets
│   │   │   ├── res/
│   │   │   │   ├── layout/                   ← XML layouts
│   │   │   │   ├── drawable/                 ← Icons & shapes
│   │   │   │   ├── values/                   ← Strings, colors, dimens
│   │   │   │   └── mipmap/                   ← App icons
│   │   │   └── AndroidManifest.xml           ← App configuration
│   │   └── test/                              ← Unit tests
│   └── build.gradle.kts                       ← App build config
├── gradle/                                     ← Gradle wrapper
├── build.gradle.kts                            ← Root build config
└── settings.gradle.kts                         ← Project settings
```

---

## 🎓 For Students/Developers

### Code Review Checklist
- ✅ All activities implemented
- ✅ RecyclerView used (no ListView)
- ✅ Material Design 3 components
- ✅ Proper resource organization
- ✅ No hardcoded strings
- ✅ Responsive layouts
- ✅ Clean architecture
- ✅ Error handling

### Learning Points
1. **RecyclerView**: Efficient list rendering
2. **SharedPreferences**: Data persistence
3. **PackageManager**: System app integration
4. **Material Design**: Modern UI/UX
5. **Kotlin**: Modern Android development
6. **Activity Lifecycle**: Proper state management

---

## 📞 Support

### Common Issues
- Check README.md for detailed documentation
- Review IMPLEMENTATION_SUMMARY.md for features
- Check logcat for runtime errors

### Resources
- [Android Developer Guide](https://developer.android.com)
- [Kotlin Documentation](https://kotlinlang.org/docs)
- [Material Design Guidelines](https://material.io/design)

---

## ✅ Final Checklist

Before submission/demo:
- [ ] Project builds without errors
- [ ] App installs on device
- [ ] All features work as expected
- [ ] Settings persist across restarts
- [ ] Search filters apps correctly
- [ ] Favorites can be added/removed
- [ ] Apps launch successfully
- [ ] UI is responsive and smooth

---

**Ready to build and run!** 🚀

Last Updated: November 21, 2025
