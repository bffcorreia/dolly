# Dolly
Dolly is a library that helps you create your Factories to setup objects as test data.

## Installing

``` groovy
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }

    dependencies {
        compile 'com.github.bffcorreia:dolly:1.0.0'
    }
```

## Defining Factories

### @Factory

Factory annotation is used to create factories based on the annotated class.

``` kotlin
class UserFactories {
  @Factory class DefaultUser : Dolly<User> {
    val name = "Chuck"
    val email = "chuck@gmail.com"
  }

  @Factory("Admin") class AdminUser : Dolly<User> {
    val name = "Tyler"
    val email = "tyler@gmail.com"
  }
}
```

All the created factories are created with a name plus the suffix `Factory`. If no name is provided
to the Factory annotation, the class name will be used (ex: `DefaultUserFactory`)

### @GeneratedId

This is a simple annotation that will create sequential ids for you (starting in 1).
Currently, only `Int`, `Long` and `String` types are allowed.

``` kotlin
class UserFactories {
  @Factory class DefaultUser : Dolly<User> {
    @GeneratedId var id: Int = 0
    val name = "Chuck"
    val email = "chuck@gmail.com"
  }

  @Factory("Admin") class AdminUser : Dolly<User> {
    @GeneratedId var id: Int = 0
    val name = "Tyler"
    val email = "tyler@gmail.com"
  }
}
```

### @WithFactory

Sometimes we want to create factories that depend on other factories. In this case, simply use
the WithFactory annotation and provide the factory name to have your object automatically created.

``` kotlin
class UserPermissionsFactories {
  @Factory class DefaultPermissions : Dolly<UserPermissions> {
    @GeneratedId var id: Int = 0
    @WithFactory("DefaultUser") lateinit var user : User
    val permissions = arrayOf("read")
  }

  @Factory class AdminPermissions : Dolly<UserPermissions> {
    @GeneratedId var id: Int = 0
    @WithFactory("Admin") lateinit var user : User
    val permissions = arrayOf("read", "write")
  }
}
```

## Using Factories

``` kotlin
  val user = DefaultUserFactory().build() // Returns a user instance
```

``` kotlin
  val admins = AdminFactory().buildList() // Returns a list with 3 user instances
```

``` kotlin
  val users = DefaultUserFactory().buildList(10) // Returns a list with 10 user instances
```

## License

    Copyright 2017 Bruno Correia

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.