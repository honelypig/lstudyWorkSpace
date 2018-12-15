package com.zda.arithmetic;

/**
 * 首先，这个问题，如果用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费了大量的时间，
 * 所以，kmp方法算法就利用之前判断过信息，通过一个next数组，保存模式串中前后最长公共子序列的长度，每次回溯时，通过next数组找到，前面匹配过的位置，
 * 省去了大量的计算时间。

 */
public class KMP {
    public static int kmp(String str, String dest,int[] next){//str文本串  dest 模式串
        for(int i = 0, j = 0; i < str.length(); i++){
            while(j > 0 && str.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if(str.charAt(i) == dest.charAt(j)){
                j++;
            }
            if(j == dest.length()){
                return i-j+1;
            }
        }
        return 0;
    }
    public static int[] kmpnext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;
        for(int i = 1,j = 0; i < dest.length(); i++){
            while(j > 0 && dest.charAt(j) != dest.charAt(i)){
                j = next[j - 1];
            }
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    public static void main(String[] args){
        String a = "ababa";
        String b = "ssdfgasdbababa";
        //先获取模式串的next数组

        int[] next = kmpnext(a);
        int res = kmp(b, a,next);
        System.out.println(res);
        for(int i = 0; i < next.length; i++){
            System.out.println(next[i]);
        }
        System.out.println(next.length);
    }
    /**
     * 获取next数组
     * “部分匹配值”就是”前缀”和”后缀”的最长的共有元素的长度。以”ABCDABD”为例，
     * －”A”的前缀和后缀都为空集，共有元素的长度为0；
     * －”AB”的前缀为[A]，后缀为[B]，共有元素的长度为0；
     * －”ABC”的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0；
     * －”ABCD”的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0；
     * －”ABCDA”的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为”A”，长度为1；
     * －”ABCDAB”的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB, B]，共有元素为”AB”，长度为2；
     * －”ABCDABD”的前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]，后缀为[BCDABD, CDABD, DABD, ABD, BD, D]，共有元素的长度为0。
     * 所以  搜索词  ABCDABD
     *   部分匹配值  0000120   =》int[7] kmpNext={0,0,0,0,1,2,0}
     *
     *  移动位数 = 已匹配的字符数 - 对应的部分匹配值
     * 因为 6 - 2 等于4，所以将搜索词向后移动 4 位。
     */

}
