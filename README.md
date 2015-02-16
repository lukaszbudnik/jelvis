jelvis [![Build Status](https://travis-ci.org/lukaszbudnik/jelvis.svg?branch=master)](https://travis-ci.org/lukaszbudnik/jelvis) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.lukaszbudnik.jelvis/jelvis/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.lukaszbudnik.jelvis/jelvis)
==============================

Elvis operator in Java 8 & Scala! Say no to NPE in chained calls!

# Getting started

## Java 8

1. Add jelvis to your project
2. Use the static `elvis` method like this:

```
import static com.github.lukaszbudnik.jelvis.Elvis.elvis;
//...
Person person = new Person();
String isoCode = elvis(person, p -> p.getAddress().getCountry().getISOCode());
```

The first argument to `elvis` method is the root object and the second one is the function to be evaluated.

If either `person` is null or  `getAddress()` or `getCountry()` returns null, the whole call returns null.

No `NullPointerException` is thrown. All other exceptions are preserved.
However,  Java 8 lambdas have some problems with functions throwing checked exceptions.
If your methods throw checked exception you need to use `wrappedFunction` like this:

```
import static com.github.lukaszbudnik.jelvis.Elvis.elvis;
import static com.github.lukaszbudnik.jelvis.Elvis.wrappedFunction;
//...
Person person = new Person();
String line2 = elvis(person, wrappedFunction(p -> p.getAddress().getLine2()));
```

## Scala

There is an implicit converter which converts Scala `Function1` into Java 8 `Function`.
All you have to do is import it in your code:

```
import com.github.lukaszbudnik.jelvis.Elvis._
import com.github.lukaszbudnik.jelvis.ElvisScalaToJavaConverters._
```

And then just use the `elvis` method in your code:

```
val person = new Person
val isoCode = elvis(person, (p: Person) => p.getAddress.getCountry.getISOCode)
```

Exceptions? In Scala all exceptions are unchecked. You don't have to worry about them at all.
For example `p.getAddress.getLine2` doesn't have to be wrapped with `wrappedFunction`.
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
