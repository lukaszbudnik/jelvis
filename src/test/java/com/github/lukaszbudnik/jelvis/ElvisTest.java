/**
 * Copyright (C) 2015 Łukasz Budnik <lukasz.budnik@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.lukaszbudnik.jelvis;

import org.junit.Assert;
import org.junit.Test;

import static com.github.lukaszbudnik.jelvis.Elvis.elvis;

public class ElvisTest {

    @Test
    public void shouldReturnNull() {
        Person person = new Person();
        String isoCode = elvis(person, p -> p.getAddress().getCountry().getISOCode());

        Assert.assertNull(isoCode);
    }

    @Test
    public void shouldThrowRuntimeException() {
        Person person = new Person();

        try {
            elvis(person, p -> p.getAddress().getLine1());
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertEquals("getLine1 threw runtime exception", e.getMessage());
        }
    }

    class Country {
        String getISOCode() {
            return "PL";
        }
    }

    class Address {
        Country getCountry() {
            return null;
        }

        // throws unchecked exception
        String getLine1() {
            throw new RuntimeException("getLine1 threw runtime exception");
        }
    }

    class Person {
        Address getAddress() {
            return new Address();
        }
    }

}

