package org.tjw.luogu.province;

public class Package {
    public static void main(String[] args) {
        int[] v = new int[]{1,2,3,4,5};
        int[] w = new int[]{1,2,3,4,5};
        int[] v1 = new int[]{1,2,3,4,5,6,7,8};
        int[] w1 = new int[]{1,2,3,4,5,6,7,8};
        Package package1 = new Package();
        System.out.println(package1.p(v,w,5,5));
        System.out.println(package1.p(v1,w1,8,8));
        System.out.println(package1.pa(v1,w1,8,8));

    }

    public int p(int[] v, int[] w, int n, int c) {
        int[] f = new int[c + 1];
        for(int i = 0;i < f.length;i ++) f[i] = 1;

        for(int i = 0; i < n; i++){
            for(int j = c; j >= w[i]; j--)
                f[j] = Math.max(f[j], f[j - w[i]] * v[i]);
        }
        return f[c];
    }

    public int pa(int[] v, int[] w, int n, int c) {
        int[] f = new int[c + 1];
        for(int i = 0;i < f.length;i ++) f[i] = 1;

        for(int i = 1;i < n;i ++)
            for(int  j = w[i];j <= c;j ++){
                f[j] = Math.max(f[j], f[j - w[i]] * v[i]);
            }
        return f[c];
    }


}
