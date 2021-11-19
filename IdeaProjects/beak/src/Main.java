import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
public class Main {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        StringBuilder sb=new StringBuilder();
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        Arrays.sort(arr, new Comparator<Integer>() {
            public int compare(int a1[], int a2[]) {
                if (a1[1] == a2[1]) {
                    return a1[0] - a2[0];
                }
                return a1[1] - a2[1];
            }
        });
        System.out.print(arr);
    }
}
