import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/main_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        Integer[] res = {n1 + n2, n1 - n2};
        
        Arrays.sort(res, Collections.reverseOrder());
        
        sb.append(String.format("%d\n%d", res[0], res[1]));
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
	}
}