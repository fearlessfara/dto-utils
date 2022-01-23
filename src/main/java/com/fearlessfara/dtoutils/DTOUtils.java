package com.fearlessfara.dtoutils;

import java.lang.reflect.Field;
import java.util.Objects;

public class DTOUtils {

    public static void notNull(Object parent, Object fieldObject) {
        if (fieldObject == null) {
            String fieldName = getFieldName(parent, fieldObject);
            throw new IllegalArgumentException("Field " + fieldName + " cannot be null.");
        }
    }


    private static String getFieldName(Object parent, Object fieldObject) {
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
}
