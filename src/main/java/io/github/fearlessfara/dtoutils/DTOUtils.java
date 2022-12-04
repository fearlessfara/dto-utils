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
package io.github.fearlessfara.dtoutils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

public final class DTOUtils {

    private DTOUtils() {

    }

    /**
     * Asserts the non nullity of a given field on an object
     *
     * @param parent      the container object of the field we want to check
     * @param fieldObject the field we want to perform checks on
     */
    public static void notNull(Object parent, Object fieldObject) {
        if (fieldObject == null) {
            String fieldName = getFieldName(parent, fieldObject);
            throw new IllegalArgumentException("Field " + fieldName + " cannot be null.");
        }
    }

    /**
     * Asserts the non nullity and non blankness of the given string
     *
     * @param parent      the container object of the field we want to check
     * @param fieldObject the field we want to perform checks on
     */
    public static void notBlank(Object parent, CharSequence fieldObject) {
        if (isBlank(fieldObject)) {
            String fieldName = getFieldName(parent, fieldObject);
            throw new IllegalArgumentException("Field " + fieldName + " cannot be null.");
        }
    }

    /**
     * Asserts the non empty-ness of the given collection
     *
     * @param parent     the container object of the field we want to check
     * @param collection the collection we want to perform the check on
     */
    public static void notEmpty(Object parent, Collection<?> collection) {
        if (isEmpty(collection)) {
            String fieldName = getFieldName(parent, collection);
            throw new IllegalArgumentException("Field " + fieldName + " cannot be empty list");
        }
    }

    private static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }


    private static String getFieldName(Object parent, Object fieldObject) {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        Field[] allFields = parent.getClass().getDeclaredFields();
        for (Field field : allFields) {
            Object currentFieldObject;
            try {
                field.setAccessible(true);
                currentFieldObject = field.get(parent);
            } catch (Exception e) {
                return null;
            }
            boolean isWantedField = Objects.equals(fieldObject, currentFieldObject);
            if (isWantedField) {
                return field.getName();
            }
        }
        return null;
    }

    private static boolean isBlank(final CharSequence cs) {
        final int strLen = length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
}
