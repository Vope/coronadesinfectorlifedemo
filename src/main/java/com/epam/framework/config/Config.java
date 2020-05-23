package com.epam.framework.config;

import org.reflections.Reflections;

import java.util.Map;

/**
 * @author Evgeny Borisov
 */
public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    Reflections getScanner();

    Map<Class, Class> getIfc2ImplClass();
}
