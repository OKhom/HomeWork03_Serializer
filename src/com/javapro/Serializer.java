package com.javapro;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;

class Serializer {
    public static String serialize(Object obj) throws IllegalAccessException {
        Class<?> cls = obj.getClass();
        StringBuilder sb = new StringBuilder();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                if (Modifier.isPrivate(field.getModifiers())) field.setAccessible(true);
                sb.append(field.getName()).append("=");
                if (field.getType() == String.class) sb.append((String)field.get(obj));
                else if (field.getType() == int.class) sb.append(field.getInt(obj));
                else if (field.getType() == boolean.class) sb.append(field.getBoolean(obj));
                sb.append(";");
            }
        }
        return sb.toString();
    }

    public static <T> T deserialize(String str, Object obj) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<T> cls = (Class<T>) obj.getClass();
        T myInst = (T)cls.newInstance();
        String[] fields = str.split(";");
        for (String field : fields) {
            String[] fieldParam = field.split("=");
            if (fieldParam.length != 2) throw new InvalidParameterException(str);
            String name = fieldParam[0];
            String value = fieldParam[1];
            Field declaredField = cls.getDeclaredField(name);
            if (Modifier.isPrivate(declaredField.getModifiers())) declaredField.setAccessible(true);
            if (declaredField.isAnnotationPresent(Save.class)) {
                if (declaredField.getType() == String.class) declaredField.set(myInst, value);
                else if (declaredField.getType() == int.class) declaredField.setInt(myInst, Integer.parseInt(value));
                else if (declaredField.getType() == boolean.class) declaredField.setBoolean(myInst, Boolean.parseBoolean(value));
            }
        }
        return myInst;
    }
}
