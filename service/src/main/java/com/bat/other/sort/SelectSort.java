package com.bat.other.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {49,38,65,97,76,13,27,49};
        sortarr(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sortarr(int[] arr) {
        for(int i=0;i<arr.length;i++){
            int ind = getMin(arr,i);
            if(ind != i)
                swap(arr,i,ind);
        }
    }

    private static int getMin(int[] arr, int i) {
        int in = i;
        for(;i<arr.length;i++){
            if(arr[i]<arr[in])
                in = i;
        }
        return in;
    }

    private static void swap(int arr[],int i,int ind){
        int temp = arr[ind];
        arr[ind] = arr[i];
        arr[i] = temp;
    }

}
