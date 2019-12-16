package algorithms.structure;

import java.util.HashMap;
import java.util.Map;
public class Array {
	
	//First not repeating char
	/**
	 * 在字符串中找出第一个只出现一次的字符
	 * 
	 * https://blog.csdn.net/u013132035/article/details/81474654
	 * */
	Character firstNotRepeatingChar_Array(String str) {
		
		if(str==null||str.isEmpty()) {
			return null;
		}
		
		str = str.toLowerCase();
		
		int[] counts = new int[26];
		
		for(int i=0;i<str.length();i++) {
			counts[str.charAt(i)-'a']++;
		}
		
		for(int i=0;i<str.length();i++) {
			if(counts[str.charAt(i)-'a']==1) {
				return str.charAt(i);
			}
		}
		
		return null;
	}
	
	Character firstNotRepeatingChar_HashMap(String str) {
		if(str==null||str.isEmpty()) {
			return null;
		}
		
		str = str.toLowerCase();
		
		Map<Character,Integer> counts = new HashMap<Character,Integer>();
		
		for(int i=0;i<str.length();i++) {
			if(counts.containsKey(str.charAt(i))) {
				counts.put(str.charAt(i), counts.get(str.charAt(i))+1);
			}else {
				counts.put(str.charAt(i), 1);
			}
		}
		
		for(int i=0;i<str.length();i++) {
			if(counts.get(str.charAt(i))==1) {
				return str.charAt(i);
			}
		}
		
		return null;
	}
	
	/**
	 * 在一个二维数组中，每行每列都递增排序，在这个数组中查找一个数字，如果存在返回true，否则返回flase
	 * 
	 * https://blog.csdn.net/baiyuexuanxuan/article/details/80366558
	 * */
	public boolean findInPartiallySortedMatrix(int[][] matrix, int findValue) {
		boolean found = false;
		
		int rows = matrix.length-1;
		int columns = matrix[0].length-1;
		
		if(rows<=0||columns<=0) {
			System.out.println("matrix empty error!");
			return false;
		}
		
		int row = 0;
		int column = columns-1;
		
		while(row<=rows&&column>=0) {
			
			if(matrix[row][column]==findValue) {
				found = true;
				System.out.println(findValue+" found at row="+row+",column="+column);
				break;
			}
			if(matrix[row][column]>findValue) {
				column--;
			}
			if(matrix[row][column]<findValue) {
				row++;
			}
			
		}
		
		if(!found) {
			System.out.println(findValue+" not found!");
		}

		return found;
	}

	
	
	
	public static void main(String[] args) {
		System.out.println(new Array().firstNotRepeatingChar_Array("abaccdeff"));
		System.out.println('\0');
		System.out.println(new Array().firstNotRepeatingChar_HashMap("abaccdeff"));
		
		int[][] matrix ={{1,2,8,4},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
		new Array().findInPartiallySortedMatrix(matrix, 7);
		new Array().findInPartiallySortedMatrix(matrix, 111);
		
		int a = 3;
		int b = (a++) + (++a) + (a++) + (++a) + 2;
		//3 a=4
		//a=5 5
		//5 a=6
		//a=7 7
		//2
		//b=3+5+5+7+2=22
		System.out.println(b);
			
		
	}

}
