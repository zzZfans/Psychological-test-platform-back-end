package com.cqjtu.mindassess.util;

import java.util.Collection;
import java.util.Map;

public class EmptyChecker {
    private EmptyChecker() {
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else if (o instanceof String) {
            return ((String)o).trim().length() == 0;
        } else if (o instanceof Collection) {
            return ((Collection)o).isEmpty();
        } else {
            return o instanceof Map ? ((Map)o).isEmpty() : false;
        }
    }

    public static boolean notEmpty(Object o) {
        return !isEmpty(o);
    }


    public static boolean isEmpty(Object[] arr) {
        return arr == null || arr.length == 0;
    }
}
