/*
 * SWEA 01204. 최빈수 구하기
 */

package difficulty02;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SWEA_01204_최빈수구하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_01204.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			Map<Integer, Integer> scoresMap = new HashMap<>();
			sc.nextInt();
			int score = 0;
			int maxFreq = 0;
			
			// Map 자료구조에 각 점수가 나온 횟수를 카운트(점수 : 횟수)
			for(int i = 0; i < 1000; i++) {
				score = sc.nextInt();
				if(!scoresMap.containsKey(score)) scoresMap.put(score,  1);
				else scoresMap.put(score, scoresMap.get(score) + 1);
			}
			
			for(int i = 0; i < scoresMap.size(); i++) {
				if(maxFreq < scoresMap.get(i)) maxFreq = scoresMap.get(i);
			}
			
			for(int i = scoresMap.size() - 1; i >= 0; i--) {
				if(scoresMap.get(i) == maxFreq) {
					System.out.printf("#%d %d\n", test_case, i);
					break;
				}
			}
		}
		sc.close();
	}
}
