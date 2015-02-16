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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

public final class Elvis {

    private static final Logger log = LogManager.getLogger(Elvis.class);

    private Elvis() {
    }

    public static <T, R> R elvis(T t, Function<T, R> f) {
        try {
            log.trace("About to apply object " + t + " on function " + f);
            return f.apply(t);
        } catch (NullPointerException e) {
            log.debug("NullPointerException caught - gracefully returning null instead", e);
            return null;
        }
    }

    public static <T, R> Function<T, R> wrappedFunction(FunctionThrowingException<T, R> f) {
        log.trace("Wrapping function " + f);
        return nf -> {
            try {
                log.trace("Delegating function " + f + " to function " + nf);
                return f.apply(nf);
            } catch (RuntimeException e) {
                log.debug("RuntimeException caught - re-throwing as is", e);
                throw e;
            } catch (Exception e) {
                log.debug("Checked Exception caught - wrapping into ElvisException and re-throwing", e);
                throw new ElvisException("Checked exception was thrown", e);
            }
        };
    }

    @FunctionalInterface
    public interface FunctionThrowingException<T, R> {
        R apply(T t) throws Exception;
    }

}
