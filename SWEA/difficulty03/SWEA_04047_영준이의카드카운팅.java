/*
 * SWEA 04047. 영준이의 카드 카운팅
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_04047_영준이의카드카운팅 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty03/input_04047.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			String S = br.readLine();
			int[] result = {13, 13, 13, 13};
			
			int spades = 0;
			int diamonds = 0;
			int hearts = 0;
			int clovers = 0;
			boolean isError = false;
			
			for(int i = 0; i < S.length(); i += 3) {
				char shape = S.substring(i, i+1).charAt(0);
				int number = Integer.parseInt(S.substring(i+1, i+3));
				
				switch(shape) {
				case 'S':
					spades = cardCheck(spades, number);
					break;
				case 'D':
					diamonds = cardCheck(diamonds, number);
					break;
				case 'H':
					hearts = cardCheck(hearts, number);
					break;
				case 'C':
					clovers = cardCheck(clovers, number);
					break;
				}
				
				if(spades == -1 || diamonds == -1 || hearts == -1 || clovers == -1) {
					isError = true;
					break;
				}
			}
			
			// 중복 카드가 있는 경우
			if(isError) {
				System.out.printf("#%d %s\n", test_case, "ERROR");
				continue;
			}
			
			// 필요한 카드 수 계산
			for(int i = 1; i <= 13; i++) {
				if((spades & 1 << i) != 0) result[0]--; 
				if((diamonds & 1 << i) != 0) result[1]--; 
				if((hearts & 1 << i) != 0) result[2]--; 
				if((clovers & 1 << i) != 0) result[3]--; 
			}
			
			// 결과 출력
			System.out.printf("#%d %s\n", test_case, Arrays.toString(result).replaceAll("[\\[\\,\\]]", ""));
		}
		br.close();
	}
	
	public static int cardCheck(int flag, int num) {
		// 이미 있는 카드라면 -1을 리턴
		if((flag & 1 << num) != 0) return -1;
		// 없는 카드였다면 해당 비트 인덱스의 값을 1로 바꾸어 리턴
		return flag | 1 << num;
	}
}
