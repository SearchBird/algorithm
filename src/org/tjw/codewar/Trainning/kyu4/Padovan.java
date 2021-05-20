package org.tjw.codewar.Trainning.kyu4;

import java.math.BigInteger;

public class Padovan {

    public static void main(String[] args) {
        BigInteger res = Get(1000000);
        System.out.print(res);
    }
    public static BigInteger Get(long power) {
        if (power < 3) return BigInteger.ONE;

        // [[0 ,0 ,1], [1, 0, 1], [0, 1, 0]]
        BigInteger[][] sol = { { BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE },
                { BigInteger.ONE, BigInteger.ZERO, BigInteger.ONE }, { BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO } };

        BigInteger[][] oneStep = { { BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE },
                { BigInteger.ONE, BigInteger.ZERO, BigInteger.ONE }, { BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO } };

        // Square and Multiply. power + 5 because of the shifted start of the sequence.
        String binary = Long.toBinaryString(power + 5).substring(1);
        for (char ch : binary.toCharArray()) {
            sol = multiply(sol, sol);
            if (ch == '1') sol = multiply(sol, oneStep);
        }
        return sol[0][0];
    }

    /**
     * Multiply a matrix with BigInteger. Used for Square and Multiply
     * [[0 ,0 ,1],
     * [1, 0, 1],
     * [0, 1, 0]]
     * @param a current matrix
     * @param b matrix for square(sol) or to multiply(oneStep)
     * @return matrix a * matrix b
     */
    public static BigInteger[][] multiply(BigInteger[][] a, BigInteger[][] b) {
        BigInteger[][] c = { { BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO },
                { BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO }, { BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO } };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    c[i][j] = c[i][j].add(a[i][k].multiply(b[k][j]));
                }
            }
        }
        return c;
    }
}
