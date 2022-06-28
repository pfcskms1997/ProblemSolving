/*
 * SWEA 01961. 숫자 배열 회전
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_01961_숫자배열회전 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01961.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			short size = sc.nextShort();
			short[][] matrix = new short[size][size];
			
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					matrix[i][j] = sc.nextShort();
				}
			}
			System.out.printf("#%d \n", test_case);
			
			for(int i = 0; i < size; i++) {
				for(int j = size - 1; j >= 0; j--) {
					System.out.print(matrix[j][i]);
				}
				System.out.print(" ");
				
				for(int j = size - 1; j >= 0; j--) {
					System.out.print(matrix[size - 1 - i][j]);
				}
				System.out.print(" ");
				
				for(int j = 0; j < size; j++) {
					System.out.print(matrix[j][size - 1 - i]);
				}
				System.out.println();
			}
		}
		sc.close();
	}
}
