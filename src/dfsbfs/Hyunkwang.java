package dfsbfs;

import java.io.*;
import java.util.*;

public class Hyunkwang {
	static int N, M, V; // N: 노드의 개수, M : 간선 개수, V : 시작 노드 번호
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	
	static void Bfs(int start, BufferedWriter bw) throws Exception {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int node = q.poll();
			bw.write(Integer.toString(node)+ " ");
			
			for (int next: graph[node]) {
				if(!visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
		
	}
	
	static void Dfs(int node, BufferedWriter bw) throws Exception {
		bw.write(Integer.toString(node) + " ");
		visited[node] = true;
		for (int next: graph[node]) {
			if(!visited [next]) {
				Dfs(next, bw);		
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(line.nextToken()); 
			int edge = Integer.parseInt(line.nextToken());
			
			graph[node].add(edge);
			graph[edge].add(node);
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort((graph[i]));
		}
		
		visited = new boolean[N+1];
		Dfs(V, bw);
		bw.newLine();
		
		visited = new boolean[N+1];
		Bfs(V, bw);
		
		bw.flush();
		bw.close();
		br.close();
		
	}
}
