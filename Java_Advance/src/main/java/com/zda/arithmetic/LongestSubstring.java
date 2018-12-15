package com.zda.arithmetic;

/**
 * 获取最长公共子串
 * 1.对于较短的那个字符串，假设其长度为n，依次找到它的长度为n, n-1, n-2....1的若干的子串，若另外那个较长的字符串包含了较短字符串的某个子串，则找到了二者的最长公共子串。
 */
public class LongestSubstring {
    public static void main(String args[]) {
        String x = DPFind("abcd", "bcd");
        System.out.println(x);
    }
    //使用动态规划查找最长公共子串

    /**
     *   a  b  c  d
     * b 0  1  0  0
     * c 0  0  2  0
     * d 0  0  0  3
     *
     * @param strOne
     * @param strTwo
     * @return
     */
    public static String DPFind(String strOne, String strTwo) {
        if (strOne == null || strTwo == null || strOne.equals("") || strTwo.equals("")) return "";
        int len1 = strOne.length();
        int len2 = strTwo.length();
        // 保存矩阵的上一行
        int[] topLine = new int[len1];
        // 保存矩阵的当前行
        int[] currentLine = new int[len1];
        // 矩阵元素中的最大值
        int maxLen = 0;
        // 矩阵元素最大值出现在第几列
        int pos = 0;

        char ch = ' ';

        for (int i=0;i<len2;i++){
            ch=strTwo.charAt(i);
            for (int j=0;j<len1;j++){
                 if(ch==strOne.charAt(j)){
                     // 如果当前处理的是矩阵的第一列，单独处理，因为其坐上角的元素不存在
                     if(j==0){
                         currentLine[j] = 1;
                     } else{
                         currentLine[j] = topLine[j-1] + 1;
                     }
                     if(currentLine[j] > maxLen){
                         maxLen = currentLine[j];
                         pos = j;
                     }
                 }
            }
            // 将矩阵的当前行元素赋值给topLine数组; 并清空currentLine数组
            for(int k=0; k<len1; k++){
                topLine[k] = currentLine[k];
                currentLine[k] = 0;
            }
        }
        return strOne.substring(pos-maxLen+1, pos+1);
    }

    //暴力查找
    public static String violenceFind(String strOne, String strTwo) {
        if (strOne == null || strTwo == null || strOne.equals("") || strTwo.equals("")) return "";
        // 二者中较长的字符串
        String max = "";
        // 二者中较短的字符串
        String min = "";
        if (strOne.length() < strTwo.length()) {
            max = strTwo;
            min = strOne;
        } else {
            max = strTwo;
            min = strOne;
        }
        String current = "";
        // 遍历较短的字符串，并依次减少短字符串的字符数量，判断长字符是否包含该子串
        for (int i = 0; i < min.length(); i++) {
            for (int begin = 0, end = min.length() - i; end <= min.length(); begin++, end++) {
                current = min.substring(begin, end);
                if (max.contains(current)) {
                    return current;
                }
            }
        }
        return null;

    }

}
