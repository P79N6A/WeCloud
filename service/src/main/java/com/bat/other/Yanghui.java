package com.bat.other;

public class Yanghui {
    public static void main(String[] args) {
        int arr[][] = new int[10][10];
        for(int i=1;i<=10;i++){//定义行号，从1开始到10
            int n = (i-1)/2;
            for(int j=0;j<=n;j++){
//                System.out.println(i);
//                System.out.println(j);
                int get = getV(arr,i,j);
//                System.out.println(get);
                arr[i-1][j] = get;
                arr[i-1][i-1-j] = get;
            }

        }

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++ ){
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }

    }

    public static int getV(int arr[][],int i,int j){
        if(j==0)
            return 1;
        int n = 0;
        for(int v = 0;v<=i-2;v++){
            n+=arr[v][j-1];
        }
        return n;
    }
}
