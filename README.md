jelvis [![Build Status](https://travis-ci.org/lukaszbudnik/jelvis.svg?branch=master)](https://travis-ci.org/lukaszbudnik/jelvis) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.lukaszbudnik.jelvis/jelvis/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.lukaszbudnik.jelvis/jelvis)
==============================

Elvis operator in Java 8! Say not to NPE in long chained calls :)

# Getting started

Import Elvis class or use the static import:

```import static com.github.lukaszbudnik.jelvis.Elvis.elvis;```

And chain your calls:

```
Person person = new Person();
String isoCode = elvis(person, p -> p.getAddress().getCountry().getISOCode());
```

If either ```getAddress()``` or ```getCountry()``` will return ```null```, the whole call will return ```null``` (instead of throwing ugly NPE!).

# Examples

See `src/test/java` for unit tests and examples.

# Download

Use the following Maven dependency:

```xml
<dependency>
  <groupId>com.github.lukaszbudnik.jelvis</groupId>
  <artifactId>jelvis</artifactId>
  <version>1.0</version>
</dependency>
```

or open [search.maven.org](http://search.maven.org/#artifactdetails|com.github.lukaszbudnik.jelvis|jelvis|1.0|jar) and copy and paste dependency id for your favourite dependency management tool (Gradle (jelvis uses Gradle), Buildr, Ivy, sbt, Leiningen, etc).

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
