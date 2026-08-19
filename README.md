# 🕌 Taqwa — Salah & Worship Tracker

A beautiful, **fully offline** app for Muslims to track their **salah (prayer)** and **worship** based on **Salafi Islam** (Ahlul Hadith manhaj).

Available as a **web app** and **standalone Android APK** (F-Droid ready).

## Features

- **🕌 Prayer Tracker** — Track all 5 daily prayers with Jama'ah support
- **📿 Sunnah Mu'akkadah** — 12 established Rakat per the Hadith of Umm Habibah (Sahih Muslim 727)
- **🕌 General Daily Actions (Nafl)** — Tahiyatul Masjid, Ishraq, and voluntary prayers
- **📿 Adhkar** — Morning, Evening, Sleep, and Masnoon Du'as with authentic hadith sources
- **📖 Quran Tracker** — Log reading sessions, track Juz progress and Khatmah count
- **📊 Stats Dashboard** — Streak, weekly chart, perfect days, and more
- **⏰ Prayer Notifications** — Local reminders for each of the 5 daily prayers (Android)
- **📍 Offline Prayer Times** — Calculated locally using Umm al-Qura method (no internet required)

## Based On

- The **Quran** and **Sunnah** upon the **Manhaj of the Salaf**
- Prayer time method: **Umm al-Qura University, Makkah** (Saudi Arabia)
- Asr calculation: **Standard method** (Shafi'i/Maliki/Hanbali/Ahlul Hadith) — NOT Hanafi
- All adhkar sourced from **Sahih al-Bukhari, Sahih Muslim, Sunan Abu Dawud, Sunan al-Tirmidhi, Sunan Ibn Majah**, and the **Quran**
- Scholarly references: **Permanent Committee for Scholarly Research and Ifta**, **Lajnah Da'imah**, **Hisn al-Muslim**

## Authenticity Policy

- Only **Sahih (authentic)** narrations are included
- **Weak (Da'if)**, **very weak (Da'if Jiddan)**, and **fabricated (Mawdu')** narrations are **excluded**
- **Innovations (Bid'ah)** in worship are **NOT tracked or encouraged**

## Live Demo

🔗 https://jjforjhon.github.io/taqwa-prayer/

## Android App

See **[BUILDING.md](BUILDING.md)** for complete build instructions.

### Quick Build

```bash
npm install
cp index.html www/index.html
npx cap sync android
cd android && ./gradlew assembleDebug
# APK: android/app/build/outputs/apk/debug/app-debug.apk
```

### F-Droid / FOSS Compliance

- ✅ Zero proprietary Google Play Services dependencies
- ✅ No tracking, analytics, or closed-source SDKs
- ✅ Standard Gradle build — `./gradlew assembleRelease`
- ✅ Fastlane metadata included for F-Droid submission
- ✅ All data stored locally — no external servers

## Tech

- **Web**: Pure HTML, CSS, JavaScript + adhan-js (prayer calculations)
- **Android**: Capacitor 6 native wrapper
- **Design**: Apple-inspired dark mode, glass morphism, smooth animations
- **Storage**: LocalStorage / IndexedDB — data stays on your device
- **Offline**: 100% offline capable — no internet required after first install

## License

MIT — Free to use and share for the sake of Allah ﷻ.
