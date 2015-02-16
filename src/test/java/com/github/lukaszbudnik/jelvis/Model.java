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

final class Model {

    static class Country {
        String getISOCode() {
            return "PL";
        }
    }

    static class Address {
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

    static class Person {
        Address getAddress() {
            return new Address();
        }

        int getIQ() throws Exception {
            return 100;
        }
    }

}
