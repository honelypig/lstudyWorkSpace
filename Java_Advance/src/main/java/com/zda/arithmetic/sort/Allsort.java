package com.zda.arithmetic.sort;

import java.util.LinkedList;

public class Allsort {
    public static void main(String args[]) {
    int[] array = new int[]{11, 31, 12, 5, 34};//, 30, 26, 38, 36, 18
        shellSort(array);
    }

    /**
     * 1.冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     *
     * @param numbers 需要排序的整型数组
     */
    public static void bubbleSort(int[] numbers) {
        int temp = 0;
        int size = numbers.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1])  //交换两数位置
                {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 2.快速排序算法
     * 通过一趟排序将待排记录分割成独立的两部分,其中一部分记录的关键字均比另一部分的关键字小,
     * 则可以分别对这两部分记录继续进行排序,已达到整个序列有序的目的
     * 本质就是,找一个基位(枢轴,分水岭,作用是左边的都比它小,右边的都比它大.可随机,取名base
     * 首先从序列最右边开始找比base小的,如果小,换位置,从而base移到刚才右边(比较时比base小)的位置(记为临时的high位),这样base右边的都比base大
     * 然后,从序列的最左边开始找比base大的
     * ,如果大,换位置,从而base移动到刚才左边(比较时比base大)的位置(记为临时的low位),这样base左边的都比base小
     * 循环以上两步,直到 low == heigh, 这使才真正的找到了枢轴,分水岭. 返回这个位置,分水岭左边和右边的序列,分别再来递归
     */

    public static int[] sortQuick(int[] array) {
        return quickSort(array, 0, array.length - 1);
    }

    private static  int[] quickSort(int[] arr, int low, int heigh) {
        if (low < heigh) {
            int division = partition(arr, low, heigh);
            //低位和base位的比较
            quickSort(arr, low, division - 1);
            //高位和base位的比较
            quickSort(arr, division + 1, heigh);
        }
        return arr;
    }

    // 分水岭,基位,左边的都比这个位置小,右边的都大
    private  static int partition(int[] arr, int low, int heigh) {
        int base = arr[low]; //用子表的第一个记录做枢轴(分水岭)记录
        while (low < heigh) {  //从表的两端交替向中间扫描
            while (low < heigh && arr[heigh] >= base) {
                heigh--;
            }
            // base 赋值给 当前 heigh 位,base 挪到(互换)到了这里,heigh位右边的都比base大
            swap(arr, heigh, low);
            while (low < heigh && arr[low] <= base) {
                low++;
            }
            // 遇到左边比base值大的了,换位置
            swap(arr, heigh, low);
        }
        // now low = heigh;
        return low;
    }

    private static  void swap(int[] arr, int a, int b) {
        int temp;
        temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 插入排序，将第i个插入到第i个前的有序数列中。
     * 每步将一个待排序的记录，按其关键码值的大小插入前面已经排序的文件中适当位置上，直到全部插入完为止。
     * 第一层是0-n的循环，循环次数为i，
     * 第二层可以看成为将第i个与0-（i-1）个的比较
     * 将第0个与第i个从后往前比较，
     * @param arr
     */
    public static void insertSort(int[] arr)
    {
        int i, j;
        int n = arr.length;
        int target;
        //假定第一个元素被放到了正确的位置上
        //这样，仅需遍历1 -  n-1
        for (i = 1; i < n; i++)
        {
            j = i;
            target = arr[i];//第二层最后一个，
            while (j > 0 && target < arr[j - 1])//这边保证了前面的都比较小
            {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = target;
        }
    }

    /**
     * 希尔
     * @param a
     */
    public static void shellSort(int[] a){
        double gap = a.length;//增量长度
        int dk,sentinel,k;
        //dk  增量
        //sentinel
        //k
        while(true){
            gap = (int)Math.ceil(gap/2);//逐渐减小增量长度，ceil是取比较大的值 Math.ceil( 1.1)=》2  1.5=》2
            dk = (int)gap;//确定增量长度
            for(int i=0;i<dk;i++){
                //用增量将序列分割，分别进行直接插入排序。随着增量变小为1，最后整体进行直接插入排序
                for(int j=i+dk;j<a.length;j = j+dk){
                    k = j-dk;//左边
                    sentinel = a[j];//右边
                    while(k>=0 && sentinel<a[k]){//将相同dk间隔的分为一组，然后对这一组进行插入排序
                        a[k+dk] = a[k];
                        k = k-dk;
                    }
                    a[k+dk] = sentinel;
                }
            }
            //当dk为1的时候，整体进行直接插入排序
            if(dk==1){
                break;
            }
        }
    }

    /**
     * 归并
     * @param arr
     */
    public static void mergeSort (int []arr){
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,temp);
    }
    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }

    /**
     * 桶排序
     * 假设输入元素均匀而独立的分布在区间[0，1）上；
     * 桶排序的核心思想是，将[0，1）分为n个大小相同的子区间，
     * 上一个区间里的元素都比下一个区间里的元素小，然后对
     * 所有区间里的元素排序，最后顺序输出所有区间里的元素，
     * 达到对所有元素排序的目的。
        *
     */
    public void bucketSort (Double[] a) {
        int n = a.length;

        /**
         * 创建链表（桶）集合并初始化，集合中的链表用于存放相应的元素
         */
        int bucketNum = 10; // 桶数
        LinkedList<LinkedList<Double>> buckets = new LinkedList<LinkedList<Double>>();
        for(int i = 0; i < bucketNum; i++){
            LinkedList<Double> bucket = new LinkedList<Double>();
            buckets.add(bucket);
        }
        // 把元素放进相应的桶中
        for(int i = 0; i < n; i++){
            int index = (int) (a[i] * bucketNum);
            buckets.get(index).add(a[i]);
        }
        // 对每个桶中的元素排序，并放进a中
        int index = 0;
        for (LinkedList<Double> linkedList : buckets) {
            int size = linkedList.size();
            if (size == 0) {
                continue;
            }
            /**
             * 把LinkedList<Double>转化为Double[]的原因是，之前已经实现了
             * 对数组进行排序的算法
             */
            Double[] temp = new Double[size];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = linkedList.get(i);
            }
            // 利用插入排序对temp排序
            insertSort(temp);
            for (int i = 0; i < temp.length; i++) {
                a[index] = temp[i];
                index++;
            }
        }

    }
    public  static  <T extends Comparable<T>> void insertSort(T[] a) {

        // TODO Auto-generated method stub
        int length = a.length;
        for (int i = 0; i < length; i++) {
            T key = a[i];
            // 需要与key比较的元素的位置
            int j = i - 1;
            /**
             * j >= 0保证a[j]存在；
             * a[j].compareTo(key) > 0保证a[j] > key, a[j]的位置需要后移，
             * 这时（a[j]后移之后，j自减1之前），j就是key的候选位置；
             * 如果是因为j < 0而退出while循环的，说明子数组中没有小于key的元素，
             * 也就是说，0是key的正确位置。
             */
            while (j >= 0 && a[j].compareTo(key) > 0) {
                // 元素后移一个位置
                a[j + 1] = a[j];
                // 需要比较的元素的位置前移
                j--;
            }
            // j+1就是key的正确位置
            a[j + 1] = key;
        }
    }

}
