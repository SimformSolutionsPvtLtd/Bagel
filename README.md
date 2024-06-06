# Bagel
![Bagel](https://github.com/yagiz/Bagel/blob/master/assets/header.png?raw=true)
<p align="center">
    <a href="https://github.com/CocoaPods/CocoaPods" alt="CocoaPods">
        <img src="https://img.shields.io/badge/CocoaPods-compatible-4BC51D.svg?style=flat" /></a>
    <a href="https://github.com/Carthage/Carthage" alt="Carthage">
        <img src="https://img.shields.io/badge/Carthage-compatible-4BC51D.svg?style=flat" /></a>
    <a href="https://github.com/JamitLabs/Accio" alt="Accio">
        <img src="https://img.shields.io/badge/Accio-supported-0A7CF5.svg?style=flat" /></a>
    <a href="https://github.com/yagiz/Bagel/releases" alt="Version">
        <img src="https://img.shields.io/github/release/yagiz/Bagel.svg" /></a>
</p>

Bagel is a little native iOS/Android network debugger. It's not a proxy debugger so you don't have to mess around with certificates, proxy settings etc. As long as your iOS/Android devices and your Mac are in the same network, you can view the network traffic of your apps seperated by the devices or simulators.

## Preview
![Bagel](https://github.com/yagiz/Bagel/blob/develop/assets/screenshot.png?raw=true)
## Install Mac App
- Clone the repo.
- Install pods.
- Build and archive the project.

## Install iOS Client
#### CocoaPods
```shhttps://img.shields.io/badge/version-1.3.1-blue.svg?style=flat
pod 'Bagel', '~>  1.4.0'
```
##### Carthage
```sh
github "yagiz/Bagel" "1.4.0"
```
##### Accio
```swift
.package(url: "https://github.com/yagiz/Bagel.git", .upToNextMajor(from: "1.4.0")),
```

### Usage
Most basic usage is to start Bagel iOS before any network operation. 
```swift
//import Bagel
Bagel.start()
```
Since Bagel exposes every request info to the public it would be better if you disable it for the store versions. You can use the below snippet to do it:
```swift
//import Bagel
#if DEBUG
Bagel.start()
#endif
```

###  Configuring Bagel
By default, Bagel gets your project name and device information. Desktop client uses these informations to separate projects and devices. You can configure these if you wish:
```swift
let bagelConfig = BagelConfiguration()

bagelConfig.project.projectName = "Custom Project Name"
bagelConfig.device.deviceName = "Custom Device Name"
bagelConfig.device.deviceDescription = "Custom Device Description"

Bagel.start(bagelConfig)
```
Bagel framework communicates with the desktop client by using Bonjour protocol. You can also configure these Netservice parameters. Default values are:

```swift
let bagelConfig = BagelConfiguration()

bagelConfig.netservicePort = 43434
bagelConfig.netserviceDomain = ""
bagelConfig.netserviceType = "_Bagel._tcp"
bagelConfig.netserviceName = ""

Bagel.start(bagelConfig)
```

## Install Android client
#### Dependency
* Add the below dependency in your preferred build system
  * `com.simformsolutions:bagel:1.0`

### Usage
In order to start Bagel we need to start the client and add a interceptor to intercept [OkHttp](https://square.github.io/okhttp/) API calls.

* You can start client in anyway you like from below
  * Start client when `Application` class starts

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Bagel.start(this)
    }
}
```
  * Start client using [AppStartup](https://developer.android.com/topic/libraries/app-startup)

```kotlin
class BagelInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Bagel.start(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> =
        mutableListOf()
}
```

* Intercept API calls in [OkHttp](https://square.github.io/okhttp/)
  * While building your `OkHttp` client create interceptor instance as below
    * `BagelInterceptor.getInstance()`

###  Configuring Bagel
By default, Bagel gets your project name and device information. Desktop client uses these informations to separate projects and devices. You can configure `projectName` and `netServiceType` if you wish:

```kotlin
val bagelConfiguration = BagelConfiguration
    .getDefault(context)
    .copy(projectName = "Bagel")

Bagel.start(
    context,
    bagelConfiguration
)
```

#### Note : If you change `netServiceType` parameter in your app, you should also change them on desktop client.

License
----
Apache
