/*
 * BAEKJOON 12927. 배수 스위치
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_12927_배수스위치 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_12927.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			String input = br.readLine();
			char[] bulbState = new char[input.length() + 1];
			int cur = 1;
			int cnt = 0;
			
			for(int i = 1; i <= input.length(); i++) {
				bulbState[i] = input.charAt(i - 1);
			}
			
			// 좌측 전구의 상태부터 오른쪽 방향으로 탐색
			while(cur <= bulbState.length - 1) {
				boolean changeFlag = false;
				int switchNum = cur;

				//System.out.println(Arrays.toString(bulbState));
				
				// 현재 위치의 전구가 꺼져 있는 경우
				if(bulbState[cur] == 'N') {
					cur++;
					continue;
				}
				
				// 현재 위치의 전구가 켜져 있는 경우
				while(!changeFlag) {
					if(bulbState[switchNum] == 'Y') bulbState[switchNum] = 'N';
					else bulbState[switchNum] = 'Y';
					switchNum += cur;
					
					if(switchNum > bulbState.length - 1) {
						cur++;
						cnt++;
						changeFlag = true;
					}
				}
			}
			System.out.printf("#%d %d\n", test_case, cnt);
		}
		br.close();
	}
}
