/*
 * SWEA 05607. 조합
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_05607_조합_X {

	static long[] fact;
	
	static final int p = 1234567891;

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty03/input_05607.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		fact = new long[1000001];
		fact[0] = 1;
		
		for(int i = 1; i < fact.length; i++) {
			fact[i] = i * fact[i-1] % p;
		}
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken()); // 전체 원소의 개수
			int r = Integer.parseInt(st.nextToken()); // 뽑을 원소의 개수
			long ans = (fact[n] * pow(fact[r], p-2) % p * pow(fact[n-r], p-2) % p) % p;
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
	
	private static long pow(long a, long b) {
		if(b == 0) return 1L;
		else if(b == 1) return a;
		
		long res = 1L; 
        a = a % p;             
        while(b > 0) {    
            if(b % 2 == 1) 
                res = (res * a) % p; 
            b = b >> 1;
            a = (a * a) % p; 
        }  
        return res; 
	}
}
