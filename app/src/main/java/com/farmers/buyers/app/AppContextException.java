package com.farmers.buyers.app;

/**
 * created by Mohammad Sajjad
 * on 22-01-2021 at 11:53
 * mohammadsajjad679@gmail.com
 */

public class AppContextException extends Exception {

    AppContextException() {
        super("App context is null, try calling init function of the implementing class");
    }

}
