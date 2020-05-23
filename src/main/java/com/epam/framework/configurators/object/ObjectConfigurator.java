package com.epam.framework.configurators.object;

import com.epam.framework.core.ApplicationContext;

/**
 * @author Evgeny Borisov
 */
public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
