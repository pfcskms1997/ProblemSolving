/*
 * SWEA 01954. 달팽이 숫자
 */

package difficulty02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SWEA_01954_달팽이숫자 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("./input/difficulty02/input_01954.txt")));
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringBuilder sb = new StringBuilder();
			int size = Integer.parseInt(in.readLine());
			int[][] arr = new int[size][size];
			int x = 0, y = -1, rev = 1;
			int num = 1;
			
			while(size != 0) {
				for(int i = 0; i < size; i++) {
					y += rev;
					arr[x][y] = num++;
				}
				size--;
				
				for(int i = 0; i < size; i++) {
					x += rev;
					arr[x][y] = num++;
				}
				rev *= -1; // 진행 방향을 반대로 변경
			}
			
			// 배열 출력
			sb.append(String.format("#%d\n", test_case));
			for(int i = 0; i < arr.length; i++) {
				for(int j = 0; j < arr[i].length; j++) {
					sb.append(arr[i][j] + " ");
				}
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}
		in.close();
	}
}
