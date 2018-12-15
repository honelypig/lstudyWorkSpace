package com.zda.arithmetic;

/**
 * 查找最长回文子串
 */
public class PalindromicSubstring {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(longestPalindrome1("babcbabcbaccba"));
    }

    /**
     * 使用普通方法
     */

    public static String longestPalindrome1(String s) {
        int maxPalinLength = 0;
        String longestPalindrome = null;
        int length = s.length();
        // check all possible sub strings
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int len = j - i;
                String curr = s.substring(i, j + 1);
                if (isPalindrome(curr)) {
                    if (len > maxPalinLength) {
                        longestPalindrome = curr;
                        maxPalinLength = len;
                    }
                }
            }
        }
        return longestPalindrome;
    }

    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    /**
     * 使用动态规划
     * 如果dp[ i ][ j ]的值为true，表示字符串s中下标从 i 到 j 的字符组成的子串是回文串。那么能够推出：
     *     dp[ i ][ j ] = dp[ i + 1][ j - 1] && s[ i ] == s[ j ]。
     *     这是一般的情况，因为须要依靠i+1, j -1，所以有可能 i + 1 = j -1, i +1 = (j - 1) -1，因此须要求出基准情况才干套用以上的公式：
     *     a. i + 1 = j -1，即回文长度为1时，dp[ i ][ i ] = true;
     *     b. i +1 = (j - 1) -1，即回文长度为2时，dp[ i ][ i + 1] = （s[ i ] == s[ i + 1]）。
     *     有了以上分析就能够写出代码了。
     *
     * 须要注意的是动态规划须要额外的O(n2)的空间。
     */
    public static String longestPalindrome2(String s) {
        if (s == null)
            return null;

        if(s.length() <=1)
            return s;

        int maxLen = 0;
        String longestStr = null;

        int length = s.length();

        int[][] table = new int[length][length];

        //every single letter is palindrome
        for (int i = 0; i < length; i++) {
            table[i][i] = 1;
        }
        printTable(table);

        //e.g. bcba
        //two consecutive same letters are palindrome
        for (int i = 0; i <= length - 2; i++) {
            //System.out.println("i="+i+"  "+s.charAt(i));
            //System.out.println("i="+i+"  "+s.charAt(i+1));
            if (s.charAt(i) == s.charAt(i + 1)){
                table[i][i + 1] = 1;
                longestStr = s.substring(i, i + 2);
            }
        }
        System.out.println(longestStr);
        printTable(table);
        //condition for calculate whole table
        for (int l = 3; l <= length; l++) {
            for (int i = 0; i <= length-l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    table[i][j] = table[i + 1][j - 1];
                    if (table[i][j] == 1 && l > maxLen)
                        longestStr = s.substring(i, j + 1);

                } else {
                    table[i][j] = 0;
                }
                printTable(table);
            }
        }
        return longestStr;
    }
    public static void printTable(int[][] x){
        for(int [] y : x){
            for(int z: y){
                //System.out.print(z + " ");
            }
            //System.out.println();
        }
        //System.out.println("------");
    }

    /**
     * 使用中心扩展法
     * 由于回文字符串是以中心轴对称的，所以假设我们从下标 i 出发。用2个指针向 i 的两边扩展推断是否相等，那么仅仅须要对0到
     * n-1的下标都做此操作，就能够求出最长的回文子串。但须要注意的是，回文字符串有奇偶对称之分，即"abcba"与"abba"2种类型。
     * 因此须要在代码编写时都做推断。
     *      设函数int Palindromic ( string &s, int i ,int j) 是求由下标 i 和 j 向两边扩展的回文串的长度，那么对0至n-1的下标。调用2次此函数：
     *      int lenOdd =  Palindromic( str, i, i ) 和 int lenEven = Palindromic (str , i , j )，就可以求得以i 下标为奇回文和偶回文的子串长度。
     *      接下来以lenOdd和lenEven中的最大值与当前最大值max比較就可以。
     *      这种方法有一个优点是时间复杂度为O(n2)，且不须要使用额外的空间。
     */
    public  static String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return null;
        }
        if (s.length() == 1) {
            return s;
        }
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // get longest palindrome with center of i
            String tmp = helper(s, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }

            // get longest palindrome with center of i, i+1
            tmp = helper(s, i, i + 1);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
        }
        return longest;
    }

    // Given a center, either one letter or two letter,
    // Find longest palindrome
    public static String helper(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1
                && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        String subS = s.substring(begin + 1, end);
        return subS;
    }
}
