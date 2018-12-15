package com.zda.arithmetic.sort;

public class MySortTest {
    public static void main(String args[]) {
        int[] array = new int[]{11, 31, 12, 5, 34, 30, 26, 38, 36, 18};//
        //popSort(array);
        sortQuick(array);
        print(array);
    }

    public static void print(int[] a) {
        System.out.printf("sort:");
        for (int i = 0; i < a.length; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");
    }

    /**
     * 冒泡
     */
    public static void popSort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {//第j与j+1的比较。而不是第i与第j的比较
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
    }

    public static void sortQuick(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 快速
     */
    public static void quickSort(int[] arr, int low, int heigh) {
        if (low < heigh) {
            int partion = partition(arr, low, heigh);
            quickSort(arr, low, partion - 1);
            quickSort(arr, partion + 1, heigh);
        }
    }

    public static int partition(int[] arr, int low, int heigh) {
        int base = arr[low];
        while (low < heigh) {

            while (low < heigh && arr[heigh] >= base)//先遍历右边
                heigh--;

            swap(arr, low, heigh);

            while (low < heigh && arr[low] <= base) {//再遍历左边
                low++;
            }
            swap(arr, low, heigh);

        }
        return low;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp;
        temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 插入
     */
    public static void insertSort(int[] arr) {
        int i, j, target;
        int size = arr.length;
        for (i = 0; i < size; i++) {
            j = i;
            target = arr[i];
            while (j > 0 && target < arr[j - 1]) {
                //当第j个小于第j-1个时，j--
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = target;
        }


    }
}
