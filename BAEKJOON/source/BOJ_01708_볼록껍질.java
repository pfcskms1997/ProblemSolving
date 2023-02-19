/*
 * BAEKJOON 01708. 볼록껍질
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_01708_볼록껍질 {

	static class Point {
		long x;
		long y;
		
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static Point start;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_01708.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine()); // 점의 개수
		start = new Point(40000, 40000); // 시작(기준)점
		
		ArrayList<Point> list = new ArrayList<Point>();
		
		StringTokenizer st = null;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Point p = new Point(x, y);
			
			if(y < start.y) {
				start = p;
			} else if(y == start.y) {
				if(x < start.x) {
					start = p;
				}
			}
			
			list.add(p);
		}
		
		// 점들을 반시계 방향으로 정렬
		list.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				int ccw = calcDirection(start, o1, o2);
				
				if(ccw > 0) return -1; // 오름차순 정렬
				else if(ccw < 0) return 1; // 내림차순 정렬
				else {
					long dist1 = calcDistance(start, o1);
					long dist2 = calcDistance(start, o2);
					if(dist1 > dist2) return 1;
				}
				return -1;
			}
		});
		
		// Graham Scan Algorithm
		Stack<Point> stack = new Stack<Point>();
		stack.push(start);
		
		for(int i = 1; i < list.size(); i++) {
			while(stack.size() > 1 && calcDirection(stack.get(stack.size()-2), stack.get(stack.size()-1), list.get(i)) <= 0) {
				stack.pop();
			}
			stack.push(list.get(i));
		}
		
		bw.write(String.format("%d", stack.size()));
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static int calcDirection(Point A, Point B, Point C) {
		long res = (B.x - A.x) * (C.y - A.y) - (C.x - A.x) * (B.y - A.y);
		
		if(res > 0) return 1; // 반시계 방향
		else if(res < 0) return -1; // 시계 방향
		else return 0; // 동일 선상
	}
	
	public static long calcDistance(Point A, Point B) {
		long w = B.x - A.x;
		long h = B.y - A.y;
		return (w * w) + (h * h);
	}
}
