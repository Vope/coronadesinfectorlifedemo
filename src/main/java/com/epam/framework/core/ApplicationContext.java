package com.epam.framework.core;

import com.epam.framework.config.Config;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Evgeny Borisov
 */
public class ApplicationContext {
    @Setter
    private ObjectFactory factory;
    private final Map<Class<?>, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private final Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }
        return createObjectAndCache(type);
    }

    private <T> T createObjectAndCache(Class<T> type) {
        Class<? extends T> implClass = type;

        if (implClass.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }

        return t;
    }

    @SneakyThrows
    public void createEagerBeans() {
        for (Map.Entry<Class, Class> mapping : config.getIfc2ImplClass().entrySet()) {
            final Class<?> ifc = mapping.getKey();
            final Singleton ifcAnnotation = ifc.getAnnotation(Singleton.class);
            if (ifcAnnotation != null && !ifcAnnotation.lazy()) {
                createObjectAndCache(ifc);
                continue;
            }

            final Class<?> impl = mapping.getKey();
            final Singleton implAnnotation = impl.getAnnotation(Singleton.class);
            if (implAnnotation != null && !implAnnotation.lazy()) {
                createObjectAndCache(ifc);
            }
        }
    }
}
