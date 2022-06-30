/*
 * SWEA 07193. 승현이의 수학공부
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_07193_승현이의수학공부 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty03/input_07193.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // N진법
			String X = st.nextToken(); // 수
			
			// 각 자릿수를 더함
			int dsum = 0;
			for(int i = 0; i < X.length(); i++) {
				dsum += X.charAt(i) - '0';
			}
			
			int ans = dsum % (N-1);
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
