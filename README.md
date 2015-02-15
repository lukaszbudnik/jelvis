jelvis [![Build Status](https://travis-ci.org/lukaszbudnik/jelvis.svg?branch=master)](https://travis-ci.org/lukaszbudnik/jelvis) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.lukaszbudnik.jelvis/jelvis/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.lukaszbudnik.jelvis/jelvis)
==============================

Elvis operator in Java 8! Say no to NPE in chained calls!

# Getting started

1. Add jelvis to your project
2. Use the static ```elvis``` method like this:

```
import static com.github.lukaszbudnik.jelvis.Elvis.elvis;
//...
Person person = new Person();
String isoCode = elvis(person, p -> p.getAddress().getCountry().getISOCode());
```

The first argument to ```elvis``` method is the root object and the second one is the function to be evaluated.

If either ```person``` is null or  ```getAddress()``` or ```getCountry()``` returns null, the whole call returns null.

No ```NullPointerException``` is thrown. All other exceptions are preserved. However,  Java 8 lambdas have some problems with functions throwing checked exceptions. If your methods throw checked exception you need to use ```wrappedFunction``` like this:

```
import static com.github.lukaszbudnik.jelvis.Elvis.elvis;
import static com.github.lukaszbudnik.jelvis.Elvis.wrappedFunction;
//...
Person person = new Person();
String line2 = elvis(person, wrappedFunction(p -> p.getAddress().getLine2()));
```

See examples for more information.

# Examples

See `src/test/java` for unit tests and examples.

# Download

Use the following Maven dependency:

```xml
<dependency>
  <groupId>com.github.lukaszbudnik.jelvis</groupId>
  <artifactId>jelvis</artifactId>
  <version>1.1</version>
</dependency>
```

or open [search.maven.org](http://search.maven.org/#artifactdetails|com.github.lukaszbudnik.jelvis|jelvis|1.1|jar) and copy and paste dependency id for your favourite dependency management tool (Gradle (jelvis uses Gradle), Buildr, Ivy, sbt, Leiningen, etc).

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
