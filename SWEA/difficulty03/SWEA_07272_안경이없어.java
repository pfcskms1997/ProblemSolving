/*
 * SWEA 07272. 안경이 없어!
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SWEA_07272_안경이없어 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_07272.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		HashMap<Character, Integer> alphabet = new HashMap<>();
		
		// 알파벳의 구멍에 따라 Map 자료구조에 분류
		for(int i = 65; i <= 91; i++) {
			switch((char)i) {
			case 'A':
			case 'D':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
				alphabet.put((char)i, 1);
				break;
			case 'B':
				alphabet.put((char)i, 2);
				break;
			default:
				alphabet.put((char)i, 0);
				break;
			}
		}
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			String input1 = st.nextToken();
			String input2 = st.nextToken();
			boolean flag = false;
			
			// 길이가 다르면 다른 문자열
			if(input1.length() != input2.length()){
				flag = false;
			}
			else {
				for(int i = 0; i < input1.length(); i++) {
					// 각 인덱스의 알파벳의 구멍의 수가 같은지 비교
					if(alphabet.get(input1.charAt(i)) != alphabet.get(input2.charAt(i))) {
						flag = false;
						break;
					}
					else flag = true;
				}
			}
			
			if(flag == true) System.out.printf("#%d SAME\n", test_case);
			else System.out.printf("#%d DIFF\n", test_case);
		}
		in.close();
	}
}
