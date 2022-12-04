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

package io.github.fearlessfara.dto.utils;


import io.github.fearlessfara.dtoutils.DTOUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestClass {

    @Test
    void workingTest() {
        TestObject testObject = new TestObject();
        assertThrows(IllegalArgumentException.class, () -> DTOUtils.notNull(testObject, testObject.field1));
    }

    @RepeatedTest(10)
    void testPerformance() {
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

    @Test
    void testCollections() {
        TestObject t = new TestObject();
        t.list = null;

        assertThrows(IllegalArgumentException.class, () -> DTOUtils.notEmpty(t, t.list));

        TestObject t1 = new TestObject();
        t1.list = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> DTOUtils.notEmpty(t1, t1.list));
    }

    @Test
    void testBlankStrings(){
        TestObject t = new TestObject();
        t.field1 = null;
        assertThrows(IllegalArgumentException.class, () -> DTOUtils.notBlank(t, t.field1));

        TestObject t1 = new TestObject();
        t1.field1 = " ";
        assertThrows(IllegalArgumentException.class, () -> DTOUtils.notBlank(t1, t1.field1));

    }

    static class TestObject {
        public String field1;
        protected Long field3;
        Object field2;
        List<String> list;
        Map<String, Object> map;
        private Integer field4;
    }
}
