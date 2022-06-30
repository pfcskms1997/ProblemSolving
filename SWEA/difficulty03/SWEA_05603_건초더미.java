/*
 * SWEA 05603. 건초더미
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SWEA_05603_건초더미 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("./input/difficulty03/input_05603.txt")));
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			int[] piles = new int[N];
			int sum = 0;
			int avg = 0;
			int cnt = 0;
			
			for(int i = 0; i < piles.length; i++) {
				piles[i] = Integer.parseInt(in.readLine());
				sum += piles[i];
			}
			
			avg = sum / piles.length;
			
			// 평균 이상의 건초더미에서 평균 이하의 건초더미로 옮겨야 하는 양을 계산
			for(int i = 0; i < piles.length; i++) {
				if(avg < piles[i]) {
					cnt += piles[i] - avg;
				}
			}
			
			System.out.printf("#%d %d\n", test_case, cnt);
		}
		in.close();
	}
}
