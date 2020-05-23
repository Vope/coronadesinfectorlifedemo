package com.epam.logic.announcer;

import com.epam.framework.core.InjectByType;
import com.epam.logic.recommendator.Recommendator;

/**
 * @author Evgeny Borisov
 */
@Deprecated
public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommendator recommendator;

    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
