/*
 * BAEKJOON 17413. 단어 뒤집기 2
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_17413_단어뒤집기2 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_17413.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			String str = br.readLine();
			Stack<Character> stack = new Stack<Character>();
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < str.length(); i++) {
				// 현재 문자가 태그의 시작인 경우
				if(str.charAt(i) == '<') {
					// 이전에 stack에 담아 두었던 단어가 있으면 sb에 거꾸로 쌓음
					if(!str.isEmpty()) {
						while(!stack.empty()) {
							sb.append(stack.pop());
						}
					}
					
					// 태그의 끝까지 정방향으로 sb에 쌓음
					while(true) {
						sb.append(str.charAt(i));
						i++;
						if(str.charAt(i) == '>') {
							sb.append(str.charAt(i));
							break;
						}
					}
					continue;
				}
				
				// 공백인 경우 이전에 stack에 쌓아두었던 문자를 뽑아 sb에 거꾸로 쌓음
				if(str.charAt(i) == ' ') {
					while(!stack.empty()) {
						sb.append(stack.pop());
					}
					sb.append(" ");
					continue;
				}
				
				// 일반 문자인 경우 stack에 쌓음
				stack.push(str.charAt(i));
			}
			
			// 문자열의 마지막 단어라 공백을 만나지 못해 남아있던 문자들을 stack에서 pop하여 sb에 거꾸로 쌓음
			while(!stack.empty()) {
				sb.append(stack.pop());
			}
			
			// 결과 출력
			System.out.println(sb);
		}
		br.close();
	}
}
