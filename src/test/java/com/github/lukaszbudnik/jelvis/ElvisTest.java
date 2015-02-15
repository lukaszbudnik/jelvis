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
import static com.github.lukaszbudnik.jelvis.Elvis.wrappedFunction;

public class ElvisTest {

    @Test
    public void shouldReturnNotNullWhenAllIsGood() {
        Person person = new Person();
        Address address = elvis(person, p -> p.getAddress());

        Assert.assertNotNull(address);
    }

    @Test
    public void shouldReturnNotNullWhenAllIsGoodWrappedFunction() {
        Person person = new Person();
        int ig = elvis(person, wrappedFunction(p -> p.getIQ()));

        Assert.assertEquals(100, ig);
    }

    @Test
    public void shouldReturnNullWhenChainedGetterReturnsNull() {
        Person person = new Person();
        String isoCode = elvis(person, p -> p.getAddress().getCountry().getISOCode());

        Assert.assertNull(isoCode);
    }

    @Test
    public void shouldReturnNullWhenRootObjectIsNull() {
        Person person = null;
        String isoCode = elvis(person, p -> p.getAddress().getCountry().getISOCode());

        Assert.assertNull(isoCode);
    }

    @Test
    public void shouldThrowOriginalRuntimeException() {
        Person person = new Person();

        try {
            elvis(person, p -> p.getAddress().getLine1());
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertEquals("getLine1 threw runtime exception", e.getMessage());
        } catch (Throwable t) {
            Assert.fail();
        }
    }

    @Test
    public void shouldThrowElvisExceptionForCheckedExceptions() {
        Person person = new Person();

        try {
            elvis(person, wrappedFunction(p -> p.getAddress().getLine2()));
            Assert.fail("Exception was expected");
        } catch (ElvisException e) {
            Assert.assertEquals("getLine2 threw checked exception", e.getCause().getMessage());
        } catch (Throwable t) {
            Assert.fail("Throwable was not expected");
        }
    }

    @Test
    public void shouldThrowRuntimeExceptionCorrectlyEvenIfMethodDeclaresCheckedException() {
        Person person = new Person();

        try {
            elvis(person, wrappedFunction(p -> p.getAddress().getGeoLocation()));
            Assert.fail("Exception was expected");
        } catch (RuntimeException e) {
            Assert.assertEquals("Geo location declares Exception but is throwing runtime exception", e.getMessage());
        } catch (Throwable t) {
            Assert.fail("Throwable was not expected");
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

        String getGeoLocation() throws Exception {
            throw new RuntimeException("Geo location declares Exception but is throwing runtime exception");
        }

        // throws unchecked exception
        String getLine1() {
            throw new RuntimeException("getLine1 threw runtime exception");
        }

        // throws checked exception
        String getLine2() throws Exception {
            throw new Exception("getLine2 threw checked exception");
        }
    }

    class Person {
        Address getAddress() {
            return new Address();
        }

        int getIQ() throws Exception {
            return 100;
        }
    }

}

