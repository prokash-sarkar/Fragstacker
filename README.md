# Fragstacker

[![AppCenter](https://build.appcenter.ms/v0.1/apps/afac4c2a-25f3-4502-aa00-b9420ce250b4/branches/master/badge)](https://build.appcenter.ms/v0.1/apps/afac4c2a-25f3-4502-aa00-b9420ce250b4/branches/master/badge) [![](https://jitpack.io/v/prokash-sarkar/Fragstacker.svg)](https://jitpack.io/#prokash-sarkar/Fragstacker)

### Specs
[![API](https://img.shields.io/badge/API-15%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=16)

A small utility class to manage Android fragments 

# Features

  - Tiny in size
  - Provides easy yet powerful customization for managing Fragment stacking
  - No 3rd party libraries

## Download

Use as Gradle dependency:

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
  implementation 'com.github.prokash-sarkar:Fragstacker:v1.0.0-alpha'
}
```

## Usage

Initialize the ```FragstackerController```

``` kotlin
val fragstackerController = FragstackerController(
                supportFragmentManager,
                R.id.content_main,
                this
        )               
```

Show a Fragment 

``` kotlin
val fragmentTransactionOptions = FragmentTransactionOptions.Builder()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .build()
                
fragstackerController.addFragment(fragmentA, fragmentTransactionOptions, false)               
```

Clear all previous fragments 

```Kotlin
fragstackerController.clearFragmentStack()
```

Listener callbacks

```Kotlin
override fun onFragmentAdded(tag: String?) {
        Log.i(TAG, "onFragmentAdded() $tag")
    }

    override fun onFragmentRemoved(tag: String?) {
        Log.i(TAG, "onFragmentRemoved() $tag")
    }

    override fun onReachedLastFragment(tag: String?) {
        Log.i(TAG, "onReachedLastFragment() $tag")

        // No more fragments in the stack
        // Either show an exit dialog or close the app
        // TODO: Perform operation
    }
```

Handle back press at ease

```Kotlin
override fun onBackPressed() {
       // Removes the current fragment and loads the immediate previous one from stack
       fragstackerController.gotoPreviousFragment(fragmentTransactionOptions)
  }
```

License
----

**Free Software, Ta-Da!**
