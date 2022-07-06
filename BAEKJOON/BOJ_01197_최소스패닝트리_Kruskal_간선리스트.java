/*
 * BAEKJOON 01197. 최소 스패닝 트리
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_01197_최소스패닝트리_Kruskal_간선리스트 {
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01197.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수(인덱스는 1부터 V까지)
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		// 간선 리스트 생성
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(from, to, weight));
		}
		
		Collections.sort(edges);
		
		// Union-Find
		int[] parents = new int[V+1];
		makeSet(parents);
		
		int weightSum = 0;
		int selEdgeCnt = 0;
		
		for(int i = 0; i < E; i++) {
			Edge edge = edges.get(i);
			
			if(union(parents, edge.from, edge.to)) {
				weightSum += edge.weight;
				if(++selEdgeCnt == V - 1) break;
			}
		}
		
		System.out.println(weightSum);
		br.close();
	}
	
	public static void makeSet(int[] parents) {
		for(int i = 1; i < parents.length; i++) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int[] parents, int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents, parents[a]);
	}
	
	public static boolean union(int[] parents, int a, int b) {
		int aRoot = findSet(parents, a);
		int bRoot = findSet(parents, b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
}
