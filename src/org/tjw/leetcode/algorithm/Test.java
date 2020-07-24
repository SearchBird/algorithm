package org.tjw.leetcode.algorithm;

import javax.lang.model.type.PrimitiveType;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        System.out.print(Map.class.isAssignableFrom(HashMap.class));
        System.out.print(AbstractMap.class.isAssignableFrom(HashMap.class));

        Class cla1 = long.class;
        Class cla2 = Long.class;
        if(cla1 == long.class || cla1 == Long.class) {
            System.out.print(cla2 == long.class || cla2 == Long.class);
        }

    }
}
