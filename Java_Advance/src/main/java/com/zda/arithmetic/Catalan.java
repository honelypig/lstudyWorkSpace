package com.zda.arithmetic;

import java.math.BigInteger;

public class Catalan {
    public static void main(String args[]) {
        System.out.println(CatalanProcess(3));
    }

    public static int CatalanProcess(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] h = new int[n + 1];
        int result = 0;
        h[0] = h[1] = 1;
        //h(0)=1
        //h(1)=1
        //h(2)=h(0)*h(1)+h(1)*h(0)
        //h(3)=h(0)*h(2)+h(1)*h(1)+h(2)*h(0)
        //f（4）=f（0）*f（3）+f（1）*f（2）+f（2）*f（1）+f（3）*f（0）；
        // h(n)= h(0)*h(n-1)+h(1)*h(n-2) + ... + h(n-1)h(0) (n>=2)
        for (int i = 2; i <= n; i++) {
            h[i] = 0;
            for (int j = 0; j < i; j++) {
                h[i] += (h[j] * h[i - (j + 1)]);
            }
        }
        result = h[n];
        return result;
    }
}
