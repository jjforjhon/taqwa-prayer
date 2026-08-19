# Building Taqwa for Android

## Prerequisites

- **Node.js** 18+ and **npm**
- **Android Studio** (latest stable) or **Android SDK** with:
  - `platform-tools`
  - `platforms;android-34`
  - `build-tools;34.0.0`
- **Java JDK 17** (required by Gradle 8.x)

## Quick Build (Debug APK)

```bash
# 1. Install dependencies
npm install

# 2. Copy web assets to www/
cp index.html www/index.html

# 3. Sync Capacitor (copies web assets + config to Android project)
npx cap sync android

# 4. Build the debug APK
cd android
./gradlew assembleDebug

# APK output: android/app/build/outputs/apk/debug/app-debug.apk
```

## Release Build (F-Droid / Sideload)

```bash
# 1. Generate a signing key (one-time)
keytool -genkeypair -v -storetype PKCS12 \
  -keystore taqwa-release.keystore \
  -alias taqwa \
  -keyalg RSA -keysize 2048 -validity 10000

# 2. Build release APK
cd android
./gradlew assembleRelease

# APK output: android/app/build/outputs/apk/release/app-release-unsigned.apk
```

## F-Droid Submission

This project is structured for F-Droid / IzzyOnDroid submission:

- **No Google Play Services** — zero proprietary SDK dependencies
- **No tracking/analytics** — fully private
- **Open-source build** — `./gradlew assembleRelease` works without proprietary tools
- **Fastlane metadata** — included in `fastlane/metadata/android/en-US/`

### Metadata structure for F-Droid:

```
fastlane/metadata/android/en-US/
├── full_description.txt
├── short_description.txt
└── changelogs/
    └── 1.txt
```

### F-Droid repo metadata (提交到 fdroiddata):

```yaml
Categories:
  - Lifestyle
  - Books & Reference
License: MIT
SourceCode: https://github.com/jjforjhon/taqwa-prayer
Builds:
  - versionName: 1.0
    versionCode: 1
    gradle:
      - assembleRelease
    scandelete:
      - android/app/src/main/assets/public/adhan.min.js
    auto_update_mode: Version
```

## Architecture

```
taqwa-prayer/
├── index.html              # Main web app (source of truth)
├── www/                    # Capacitor web assets (copied from root)
│   ├── index.html
│   └── adhan.min.js        # Bundled prayer calculation library
├── android/                # Capacitor Android project
│   ├── app/
│   │   ├── build.gradle    # App-level Gradle config
│   │   └── src/main/
│   │       ├── AndroidManifest.xml
│   │       ├── java/com/taqwa/prayer/
│   │       │   └── BootReceiver.java
│   │       └── assets/public/  # Web assets served by WebView
│   ├── build.gradle        # Root Gradle config
│   └── settings.gradle
├── capacitor.config.json   # Capacitor configuration
├── package.json            # Node.js dependencies
└── fastlane/               # F-Droid metadata
```

## Permissions (AndroidManifest.xml)

| Permission | Purpose |
|---|---|
| `ACCESS_FINE_LOCATION` | GPS for offline prayer time calculation |
| `ACCESS_COARSE_LOCATION` | Fallback location for prayer times |
| `POST_NOTIFICATIONS` | Prayer time reminder notifications |
| `RECEIVE_BOOT_COMPLETED` | Reschedule notifications after reboot |
| `SCHEDULE_EXACT_ALARM` | Precise notification timing |
| `VIBRATE` | Notification vibration |
| `WAKE_LOCK` | Keep device awake for notifications |
| `INTERNET` | Initial load only — app runs fully offline |

## Key Configuration

- **Prayer Calculation**: Umm al-Qura University (Makkah) — Saudi Arabia standard
- **Asr Method**: Standard (Shafi'i/Maliki/Hanbali/Ahlul Hadith) — NOT Hanafi
- **Default Coordinates**: Makkah (21.4225°N, 39.8262°E) — overridden by GPS
- **Offline**: All prayer calculations computed locally using adhan-js
- **Storage**: Android WebView localStorage / IndexedDB — no external servers
