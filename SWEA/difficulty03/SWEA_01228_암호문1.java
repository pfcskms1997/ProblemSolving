/*
 * SWEA 01228. 암호문1 
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_01228_암호문1 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_01228.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int originLen = Integer.parseInt(in.readLine()); // 원본 암호문의 길이
			int inst = 0; // 명령어의 개수
			LinkedList<Integer> list = new LinkedList<Integer>();
			
			// 원본 암호문을 LinkedList 자료구조에 입력
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int i = 0; i < originLen; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			inst = Integer.parseInt(in.readLine());
			StringTokenizer st1 = new StringTokenizer(in.readLine(), " ");
			for(int i = 0; i < inst; i++) {
				if(!st1.nextToken().equals("I")) {
					System.out.println("INPUT ERROR!!");
					break;
				}
				
				// idx x부터 y개의 새 암호문을 기존 암호문에 덧붙임
				int x = Integer.parseInt(st1.nextToken());
				int y = Integer.parseInt(st1.nextToken());
				
				for(int j = 0; j < y; j++) {
					list.add(x + j, Integer.parseInt(st1.nextToken()));
				}
			}
			
			// 결과 출력 Part
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case);
			for(int i = 0; i < 10; i++) {
				sb.append(" ").append(list.get(i));
			}
			System.out.println(sb);
		}
		in.close();
	}
}
