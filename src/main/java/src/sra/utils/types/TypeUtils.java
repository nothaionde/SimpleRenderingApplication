package src.sra.utils.types;

import src.sra.logging.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class TypeUtils {
    public static void initSingleton(Class<?> clazz, Object value) {
        setAnnotatedField(clazz, Singleton.class, value);
    }

    private static void setAnnotatedField(Class<?> clazz, Class<? extends Annotation> annotation, Object value) {
        Stream.of(clazz.getDeclaredFields())
                .parallel()
                .filter(field -> Objects.nonNull(field.getAnnotation(annotation)))
                .findAny()
                .ifPresent(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(null, value);
                    } catch (IllegalAccessException e) {
                        Log.error("Cannot access field " + field.getName(), e);
                    }
                });
    }

    public static <T> T newInstance(Class<T> type, Object... args) {
        try {
            Constructor<T> constructor = type.getDeclaredConstructor(classesOf(args));
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (Exception e) {
            Log.error("Can't invoke constructor for class " + type + " with arguments " + Arrays.toString(args), e);
        }
        return null;
    }

    private static Class<?>[] classesOf(Object[] objects) {
        Class<?>[] classes = new Class<?>[objects.length];
        for (int i = 0; i < objects.length; i++) {
            classes[i] = objects[i].getClass();
        }
        return classes;
    }
}
