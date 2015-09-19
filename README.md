jelvis [![Build Status](https://travis-ci.org/lukaszbudnik/jelvis.svg?branch=master)](https://travis-ci.org/lukaszbudnik/jelvis) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.lukaszbudnik.jelvis/jelvis/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.lukaszbudnik.jelvis/jelvis)
==============================

jelvis is an elvis operator for Java 8 & Scala. jelvis eats `NullPointerException` in chained calls and returns null instead.

For example in this chained call:

```
person.getAddress().getCountry()
```

you can get at least two NPEs: first when `person` is null and second when `getAddress()` returns null.

jelvis takes care of NPEs for you. For the above call jelvis will return the value of `getCountry()` or null if either `person` or `getAddress()` is null. No NPE will be thrown.

# Getting started

## Java 8

All you need is just one static import:

```
import static com.github.lukaszbudnik.jelvis.Elvis.elvis;
//...
Person person = new Person();
// old syntax requires 2 arguments
// the first argument to `elvis` method is the root object
// the second one is the lambda function to be evaluated
String isoCode = elvis(person, p -> p.getAddress().getCountry().getISOCode());

// new syntax takes just a lambda to be evaluated
// a little bit shorter than the old syntax.
String isoCode = elvis(() -> person.getAddress().getCountry().getISOCode());
```

No `NullPointerException` is thrown. All other exceptions are preserved.
Java 8 lambdas have some problems with functions throwing checked exceptions (code simply does not compile).
To overcome this limitation jelvis is wrapping all checked exceptions into a runtime exception: `ElvisException`.

Prior to jelvis 1.3 you had to explicitly wrap functions throwing exceptions using `Elvis.wrappedFunction()`.
This is now done automatically:

```
import static com.github.lukaszbudnik.jelvis.Elvis.elvis;
//...
Person person = new Person();
// starting with jelvis 1.3 there is no need to use Elvis.wrappedFunction()
// old syntax
String line2 = elvis(person, p -> p.getAddress().getLine2());
// new syntax
String line2 = elvis(() -> person.getAddress().getLine2());
```

## Scala

There is an implicit converter which converts Scala `Function1` into jelvis functions.
So in case of Scala you have to add two imports:

```
import com.github.lukaszbudnik.jelvis.Elvis._
import com.github.lukaszbudnik.jelvis.ElvisScalaToJavaConverters._
```

And then just use the `elvis` method in your code:

```
val person = new Person
// old syntax
val isoCode = elvis(person, (p: Person) => p.getAddress.getCountry.getISOCode)
// new syntax
val isoCode = elvis(() => person.getAddress.getCountry.getISOCode)
```

Exceptions? In Scala all exceptions are unchecked. You don't have to worry about them at all.
For example `p.getAddress.getLine2` doesn't have to be wrapped using `Elvis.wrappedFunction()`.
Thus, when running from Scala, you will never see `ElvisException`.
The call simply looks like this:

 ```
 elvis(person, (p: Person) => p.getAddress.getLine2)
 ```

See examples for more information.

# Examples

See `src/test/java` for Java unit tests and `src/test/scala` for Scala ones.

# Download

Use the following Maven dependency:

```xml
<dependency>
  <groupId>com.github.lukaszbudnik.jelvis</groupId>
  <artifactId>jelvis</artifactId>
  <version>{version}</version>
</dependency>
```

or open [search.maven.org](http://search.maven.org/#search|ga|1|com.github.lukaszbudnik.jelvis)
and copy and paste dependency id for your favourite dependency management tool (Gradle (jelvis uses Gradle), Buildr, Ivy, sbt, Leiningen, etc).

# License

Copyright 2015 Łukasz Budnik

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   <http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
