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

import com.github.lukaszbudnik.jelvis.Model.Address;
import com.github.lukaszbudnik.jelvis.Model.Person;
import org.junit.Assert;
import org.junit.Test;

import static com.github.lukaszbudnik.jelvis.Elvis.elvis;

public class ElvisNewSyntaxTest {

    @Test
    public void shouldReturnNotNullWhenAllIsGood() {
        Person person = new Person();
        Address address = elvis(() -> person.getAddress());

        Assert.assertNotNull(address);
    }

    @Test
    public void shouldReturnNotNullWhenAllIsGoodWrappedFunction() {
        Person person = new Person();
        int iq = elvis(() -> person.getIQ());

        Assert.assertEquals(100, iq);
    }

    @Test
    public void shouldReturnNullWhenChainedGetterReturnsNull() {
        Person person = new Person();
        String isoCode = elvis(() -> person.getAddress().getCountry().getISOCode());

        Assert.assertNull(isoCode);
    }

    @Test
    public void shouldReturnNullWhenRootObjectIsNull() {
        Person person = null;
        String isoCode = elvis(() -> person.getAddress().getCountry().getISOCode());

        Assert.assertNull(isoCode);
    }

    @Test
    public void shouldThrowOriginalRuntimeException() {
        Person person = new Person();

        try {
            elvis(() -> person.getAddress().getLine1());
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
            elvis(() -> person.getAddress().getLine2());
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
            elvis(() -> person.getAddress().getGeoLocation());
            Assert.fail("Exception was expected");
        } catch (RuntimeException e) {
            Assert.assertEquals("Geo location declares Exception but is throwing runtime exception", e.getMessage());
        } catch (Throwable t) {
            Assert.fail("Throwable was not expected");
        }
    }

}
