package com.bat.other.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int arr[] = {49,38,65,97,76,13,27,49};
        sortarr(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
// 27,38,_,97,76,13,65,49
// 27,38,13,49,76,97,65,49
//

    }

    private static void sortarr(int[] arr,int low,int high) {
        if(low >= high)
            return;
        int i,j;
        i=low;
        j=high;
        int flag = arr[i];
        while (i<j){
            while (i<j&&arr[j]>=flag){
                j--;
            }
//            arr[i] = arr[j];
            while(i<j&&arr[i]<=flag){
                i++;
            }
            if(i<j){
                myswap(arr,i,j);
            }
//            arr[j]=arr[i];
        }
//        arr[i] = flag;
        myswap(arr,low,i);
        System.out.println("i:"+i+"j:"+j);
        sortarr(arr,low,i-1);
        sortarr(arr,i+1,high);

    }

    private static void myswap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }
}
