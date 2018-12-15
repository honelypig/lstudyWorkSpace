package com.zda.arithmetic;

import java.util.Scanner;

public class FindMaxSum {
    public int FindGreatestSumOfSubArray(int[] array) {

        if(array == null || (array.length == 1 && array[0] <= 0))
            return 0;

        int cur = array[0];
        int sum = array[0];
        for(int i = 1;i < array.length;i++){
            if(cur < 0)
                cur = 0;
            cur = cur + array[i];
            if(sum <= cur)
                sum = cur;
        }
        return sum;
    }

    //用动态规划
    public int FindGreatestSumOfSubArray2(int[] arr,int n){
        int sum = arr[0];
        int max = arr[0];
        for(int i = 1; i < n; i++){
            sum = getMax(sum+arr[i],arr[i]);
            if(sum >= max)
                max = sum;
        }

        return max;
    }

    public int getMax(int a,int b){
        return a > b ? a: b;
    }

    public static void main(String args[]){
        FindMaxSum ts = new FindMaxSum();
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int n = scan.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++)
                a[i] = scan.nextInt();
            System.out.println(ts.FindGreatestSumOfSubArray(a));
            System.out.println(ts.FindGreatestSumOfSubArray2(a,a.length));
        }
    }


}
