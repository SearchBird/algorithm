package org.tjw.codewar.Trainning.kyu4.bwt;

import java.util.ArrayList;
import java.util.List;

public class RunLengthCode {

    public static void main(String[] args) {
        BurrowsWheeler.BWT bwt = BurrowsWheeler.encode("banananabababanananbana");
        System.out.println(bwt);
        int[] mtfRes = Movetofront.encode(bwt);
        for(int i : mtfRes) System.out.print(i + " ");
        System.out.println();
        int[] rlcRes = encode(mtfRes);
        for(int i : rlcRes) System.out.print(i + " ");
        System.out.println();
    }

    public static int[] encode(int[] arr) {
        int len = arr.length;
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < len;i ++) {
            int count = 1;
            int temp = arr[i];
            while(i + 1 < len && arr[i + 1] == temp) {
                i ++; count ++;
            }
            list.add(count);
            list.add(temp);
        }
        int size = list.size();
        int[] res = new int[size];
        for(int i = 0;i < size;i++) res[i] = list.get(i);
        return res;
    }

    public static int[] decode(int[] arr) {
        int len = arr.length;
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < len;i += 2) {
            for(int j = arr[i]; j -- > 0;) list.add(arr[i + 1]);
        }
        int size = list.size();
        int[] res = new int[size];
        for(int i = 0;i < size;i ++) res[i] = list.get(i);
        return res;
    }
}
