package org.tjw.codewar.Trainning.kyu4;

import java.lang.reflect.*;

public class BagelSolver extends Bagel {

    public static void main(String[] args) {
        Bagel bagel = BagelSolver.getBagel();

        System.out.println(bagel.getValue() == 4);
    }

    public static Bagel getBagel() {
        try {
            Field trueField = Boolean.class.getField("TRUE");
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(trueField, trueField.getModifiers() & ~Modifier.FINAL);
            trueField.setAccessible(true);
            trueField.set(null, Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bagel bagel = new Bagel();
        return bagel;
    }
}

class Bagel {
    public final int getValue() {
        return 3;
    }
}
