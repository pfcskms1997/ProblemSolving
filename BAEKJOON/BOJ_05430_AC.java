import java.io.*;
import java.util.*;

public class BOJ_05430_AC {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/input_05430.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T ; test_case++) {
            char[] p = br.readLine().toCharArray(); // 수행할 함수 (1 ≤ p.length ≤ 100,000)
            int n = Integer.parseInt(br.readLine()); // 배열에 들어있는 수의 개수 (0 ≤ n ≤ 100,000)
            String inputArr = br.readLine().replaceAll("[\\[\\,\\]]", "\t");
            List<Integer> x = new LinkedList<>();

            StringTokenizer st = new StringTokenizer(inputArr, "\t");
            while(st.hasMoreTokens()) {
                x.add(Integer.parseInt(st.nextToken()));
            }

            boolean flag = true; // true: front, false: back
            boolean errorFlag = false;
            for (int i = 0; i < p.length; i++) {
                if(p[i] == 'R') {
                    flag = !flag;
                } else if(p[i] == 'D') {
                    if(x.size() < 1) {
                        errorFlag = true;
                        break;
                    }

                    if(flag) {
                        x.remove(0);
                    } else {
                        x.remove(x.size() - 1);
                    }
                }
            }

            if(!flag) {
                Comparator com = new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        return -1;
                    }
                };

                Collections.sort(x, com);
            }

            System.out.println(errorFlag ? "error" : x.toString().replaceAll(" ", ""));
        }
        br.close();
    }
}
