package com.micka.utils;

import java.util.Objects;

public class Utilities {

    private Utilities(){}

    public static void checkForNull(Object object){
        if(Objects.isNull(object)){
            throw new IllegalArgumentException("Passed value should not be null");
        }
    }

}
