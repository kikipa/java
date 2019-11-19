package algorithms.basic;

public class BinarySearch {
	
	public static int binarySearch(int key, int[] a) {
		if(a==null||a.length==0) {
			return -1;
		}
		int low = 0;
		int high = a.length-1;
		while(low<=high) {
			int mid = low + (high-low)/2;
			if(key<a[mid]) {
				high = mid-1;
			}else if(key>a[mid]){
				low = mid+1;
			}else {
				return mid;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] whiteList = {1,3,4,6,8,11,14,15,19,22,23,24,30};
		System.out.println(BinarySearch.binarySearch(11, whiteList));
	
	}
	
	
}
