package com.epam.logic;

import com.epam.framework.Application;
import com.epam.framework.core.ApplicationContext;
import com.epam.logic.policeman.Policeman;
import com.epam.logic.policeman.PolicemanImpl;
import com.epam.logic.recommendator.Recommendator;
import com.epam.logic.recommendator.RecommendatorImpl;

import java.util.HashMap;

/**
 * @author Evgeny Borisov
 */
public class Main {
    public static void main(String[] args) {
        final HashMap<Class, Class> ifc2ImplClass = new HashMap<>() {{
            put(Policeman.class, PolicemanImpl.class);
            put(Recommendator.class, RecommendatorImpl.class);
        }};
        ApplicationContext context = Application.run("com.epam", ifc2ImplClass);
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);
        desinfector.start(new Room());
    }
}
