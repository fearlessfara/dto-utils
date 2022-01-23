package com.fearlessfara.dto.utils;


import com.fearlessfara.dtoutils.DTOUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestClass {

    static class TestObject {
        public String field1;
        Object field2;
        protected Long field3;
        private Integer field4;
        Map<String, Object> map;
    }

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
}
