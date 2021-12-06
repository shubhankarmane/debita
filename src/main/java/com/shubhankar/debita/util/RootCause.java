package com.shubhankar.debita.util;

public class RootCause {
    public static Throwable get(Throwable throwable) {
        Throwable rootCause = throwable;

        while(rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }

        return rootCause;
    }
}
