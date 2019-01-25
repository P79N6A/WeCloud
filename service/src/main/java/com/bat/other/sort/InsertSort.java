package com.bat.other.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int arr[] = {49,38,65,97,76,13,27,49};
        sortarr(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sortarr(int[] arr) {
        int j,temp;
        for(int i=1;i<arr.length;i++){
            j = i-1;
            temp = arr[i];
            while(j>=0 && temp < arr[j]){
                arr[j+1] = arr[j--];//包含两步操作
            }
            arr[j+1] = temp;
        }
    }
}
