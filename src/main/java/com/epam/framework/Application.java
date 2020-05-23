package com.epam.framework;

import com.epam.framework.config.JavaConfig;
import com.epam.framework.core.ApplicationContext;
import com.epam.framework.core.ObjectFactory;

import java.util.Map;

/**
 * @author Evgeny Borisov
 */
public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifc2ImplClass) {
        JavaConfig config = new JavaConfig(packageToScan, ifc2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);
        context.createEagerBeans();
        return context;
    }
}
