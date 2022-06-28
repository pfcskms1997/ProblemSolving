/*
 * SWEA 01926. 간단한 369게임
 */

package difficulty02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_01926_간단한369게임 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty02/input_01926.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine()); // 입력받은 숫자
		
		for(int i = 1; i <= N; i++) {
			String temp = Integer.toString(i).replaceAll("[3,6,9]", "-");
			int cnt = 0;
			
			for(int j = 0; j < temp.length(); j++) {
				if(temp.charAt(j) == '-') cnt++;
			}
			
			if(cnt == 0) sb.append(temp + " ");
			else if(cnt == temp.length()) {
				temp = "";
				for(int j = 0; j < cnt; j++) {
					temp += "-";
				}
				sb.append(temp + " ");
			}
			else sb.append("- ");
			
			//sb.append(temp + " ");
		}
		//System.out.println(temp.replaceAll("[369]", "-"));
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
