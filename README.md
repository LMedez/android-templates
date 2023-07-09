Architecture starter template (single module)
==================

This template is compatible with the latest **stable** version of Android Studio.

## Screenshots
![Screenshot](https://raw.githubusercontent.com/LMedez/android-templates/main/screenshot_koin.png)

## Features

* Room Database
* Koin
* ViewModel, read+write
* UI in traditional XML
* Navigation
* Repository and data source
* Kotlin Coroutines and Flow

## Usage

1. Clone this branch

```
git clone https://github.com/android/architecture-templates.git --branch base-xml
```

2. Run the customizer script:

```
./customizer.sh your.package.name DataItemType [MyApplication]
```

Where `your.package.name` is your app ID (should be lowercase) and `DataItemType` is used for the
name of the screen, exposed state and data base entity (should be PascalCase). You can add an optional application name.

# License

Now in Android is distributed under the terms of the Apache License (Version 2.0). See the
[license](LICENSE) for more information.
