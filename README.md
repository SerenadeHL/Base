# 添加依赖
```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
        implementation 'com.github.SerenadeHL:Base:1.1.7'
}
```

# 自定义Gson
```java
class DAppApplication : BaseApplication() {
     override fun onCreate() {
        super.onCreate()
        GsonExtensionUtils.setGson(yourGson)
     }
}
```