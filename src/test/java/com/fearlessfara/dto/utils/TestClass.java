/*
 * Copyright 2022 fearlessfara (Christian Gennaro Faraone)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fearlessfara.dto.utils;


import com.fearlessfara.dtoutils.DTOUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestClass {

    @Test
    public void workingTest() {
        TestObject testObject = new TestObject();
        assertThrows(IllegalArgumentException.class, () -> DTOUtils.notNull(testObject, testObject.field1));
    }

    @RepeatedTest(10)
    public void testPerformance() {
        TestObject testObject = new TestObject();
        long start = 0, end, duration;
        try {
            start = System.currentTimeMillis();
            DTOUtils.notNull(testObject, testObject.field1);

        } catch (Exception ignored) {

        } finally {
            end = System.currentTimeMillis();
        }

        duration = (end - start);
        System.out.println("time elapsed for reflection: " + duration + " ms");
    }

    static class TestObject {
        public String field1;
        protected Long field3;
        Object field2;
        Map<String, Object> map;
        private Integer field4;
    }
}
