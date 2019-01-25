package com.bat.other.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {49,38,65,97,76,13,27,49};
        Arrays.stream(arr).map(s->s+222).toArray();
        sortarr(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sortarr(int[] arr) {
        for(int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    private static void swap(int arr[],int i,int ind){
        int temp = arr[ind];
        arr[ind] = arr[i];
        arr[i] = temp;
    }
}
