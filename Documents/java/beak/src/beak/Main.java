package beak;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;
import java.util.Deque;
import java.util.Queue;
public class Main {
	
	public static boolean Reverse(boolean reverse) {
		if(reverse==true) {
			return false;
		}
		return true;
	}
	public static void cut(boolean reverse, Deque<Integer> d) {
		if(reverse) {
			d.removeLast();
		}else {
			d.removeFirst();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO 자동 생성된 메소드 스텁
		Scanner sc=new Scanner(System.in);
		StringBuilder sb= new StringBuilder();
		int n=sc.nextInt();
		Deque<Integer> d= new LinkedList<Integer>();
		//\
		boolean reverse;
		String order;
		boolean errorchk;
		for(int i=0;i<n;i++) {
			reverse =false;
			errorchk=false;
			sb=new StringBuilder();
			order=sc.next();
			int arr_leng=sc.nextInt();
			String arr=sc.next();
			String[] arr1;
			arr1=arr.substring(1,arr.length()-1).split(",");
			
			for(int j=0;j<arr1.length;j++) {
				d.add(Integer.parseInt(arr1[j]));
			}
			
			for(int j=0;j<order.length();j++) {
				
				if(order.charAt(j)=='R') {
					reverse=Reverse(reverse);
				}
				else {
					if(d.size()==0) {
						errorchk=true;
						break;
					}
					
					cut(reverse,d);
					System.out.println(d);
					
				}
				System.out.println(reverse);
			}
			
			
			if(errorchk==false) {
				sb.append("[");
				if(reverse==true) {
					while(d.size()>1) {
						sb.append(d.pollLast());
					}
				}	else {
					while(d.size()>1) {
						sb.append(d.pollFirst());
					}
				
				}
				sb.append("]");
				System.out.print(sb);
			}else {
				sb.append("error");
			}
		}
	}
}
