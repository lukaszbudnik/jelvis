/**
 * Copyright (C) 2015 Łukasz Budnik <lukasz.budnik@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.lukaszbudnik.jelvis


import com.github.lukaszbudnik.jelvis.Elvis._
import com.github.lukaszbudnik.jelvis.ElvisScalaToJavaConverters._
import com.github.lukaszbudnik.jelvis.Model._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ElvisSpec extends Specification {

  "Elvis" should {
    "return expected values when all is good" in {
      val person: Person = new Person

      val iq: Int = elvis(person, (p: Person) => p.getIQ)

      iq must beEqualTo(100)
    }

    "return null when chained call throws NPE" in {
      val person = new Person
      val isoCode = elvis(person, (p: Person) => p.getAddress.getCountry.getISOCode)

      isoCode must beNull
    }

    "throw ElvisException when chained call throws checked exception" in {
      val person = new Person

      elvis(person, (p: Person) => p.getAddress.getLine2) must throwAn[Exception]("getLine2 threw checked exception")
    }

  }

}
