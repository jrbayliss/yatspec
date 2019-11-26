package com.googlecode.yatspec.state;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unused"})
public class LogKey {
    private final String value;

    public LogKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getValueWithSpacesReplacedByUnderscore() {
        return value.replaceAll(" ", "_").replaceAll("\\(", "_").replaceAll("\\)","_");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogKey logKey = (LogKey) o;

        Set s = new HashSet();

        return value.equals(logKey.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
