/*
 * SWEA 02063. 중간값 찾기
 */

package difficulty01;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA_02063_중간값찾기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty01/input_02063.txt"));
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		int mid = len / 2;
		int[] arr = new int[len];
		
		for(int i = 0; i < len; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		System.out.println(arr[mid]);
		
		sc.close();
	}
}
