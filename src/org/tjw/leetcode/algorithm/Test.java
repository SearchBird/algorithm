package org.tjw.leetcode.algorithm;

import javax.lang.model.type.PrimitiveType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Throwable {
        Method method = Class.class.getDeclaredMethod("getPrimitiveClass", String.class);
        method.setAccessible(true);
        System.out.println(method.invoke(null, "int"));
    }
}
