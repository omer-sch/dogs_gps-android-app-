# GPS Dogs 🐾

> **Real-Time GPS Tracking App for Your Dog**

[![Android](https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=flat-square&logo=firebase&logoColor=black)](https://firebase.google.com)
[![Google Maps](https://img.shields.io/badge/Google_Maps-4285F4?style=flat-square&logo=google-maps&logoColor=white)](https://developers.google.com/maps)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)](LICENSE)

**GPS Dogs** is an Android app that lets you track your dog's location history on an interactive map. Log in, view today's route or the full history, manage your profile, and always know where your pup has been.

---

## What It Does

- **User authentication** — register and log in with email & password via Firebase Auth
- **Profile management** — update your name, bio, and profile photo
- **GPS route visualization** — view your dog's movement as polylines on Google Maps
- **Date filtering** — see today's route or the full historical track
- **Cloud sync** — all GPS data stored in Firebase Firestore, accessible from any device

---

## Screens

| Screen | Description |
|--------|------------|
| Start | Welcome screen with Login / Register options |
| Login | Email + password sign-in |
| Register | New account creation with password confirmation |
| Profile | Displays user info and profile photo |
| Edit Profile | Update name, bio, and upload a new photo |
| Map | Google Maps view of GPS track with polylines and markers |

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin |
| UI | XML layouts + View Binding |
| Navigation | Jetpack Navigation Components |
| Authentication | Firebase Auth (email/password) |
| Database | Firebase Firestore |
| Realtime DB | Firebase Realtime Database |
| File Storage | Firebase Cloud Storage |
| Maps | Google Maps SDK for Android |
| Location | Google Play Services Location |
| Min SDK | Android 21 (Lollipop) |
| Target SDK | Android 34 |

---

## Architecture

```
Firebase Auth
    │
    ▼
FireStoreModel  ←  Singleton business logic layer
    ├── register / login / logout
    ├── updateUserProfile (name, bio, photo)
    ├── getAllGpsData()       ← full history
    └── getTodayGpsData()    ← filtered by today's date
         │
         ▼
    Firestore Collections
    ├── users/          ← profiles
    └── gps_data/       ← GPS records { lat, lng, timestamp }
```

Navigation is fragment-based with a single `NavGraph`:

```
StartFragment
    ├── → LoginFragment    → ProfileFragment
    └── → RegisterFragment → ProfileFragment
                                │
                                ├── → EditProfileFragment
                                └── → MapsActivity
```

---

## Getting Started

### Prerequisites
- Android Studio Hedgehog or newer
- A Firebase project with Auth, Firestore, and Storage enabled
- A Google Maps API key

### Setup

1. Clone the repo:
```bash
git clone https://github.com/omer-sch/dogs_gps-android-app-.git
cd dogs_gps-android-app-
```

2. Add your `google-services.json` from the Firebase Console into `app/`.

3. Add your Google Maps API key to `AndroidManifest.xml`:
```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API_KEY_HERE" />
```

4. Open in Android Studio and run on a device or emulator.

---

## Firestore Data Model

```
users/{userId}
    ├── email: String
    ├── fullName: String
    ├── bio: String
    └── photoUrl: String

gps_data/{docId}
    ├── lat: Double
    ├── lng: Double
    ├── timestamp: String   ← "yyyy-MM-dd HH:mm:ss"
    └── userId: String
```

---

## Roadmap

- [ ] Background location service for live tracking
- [ ] Real-time map updates as the dog moves
- [ ] Polyline click — show timestamp and speed info
- [ ] Multiple dogs per account
- [ ] Geofence alerts when dog leaves a safe zone
- [ ] Export route as GPX/KML

---

## Built By

**Omer Schreiber** — Android & Full-Stack Developer

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Omer_Schreiber-0077B5?style=flat-square&logo=linkedin)](https://linkedin.com/in/omer-schreiber-48b3912b6)
[![GitHub](https://img.shields.io/badge/GitHub-omer--sch-181717?style=flat-square&logo=github)](https://github.com/omer-sch)

---

## License

MIT — see [LICENSE](LICENSE) for details.
