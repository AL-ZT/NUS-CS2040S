// Modulo Solitaire
// there are TWO subtle bugs in this code (one of them involves overflow... use Long)

import java.io.*;
import java.util.*;

class modulosolitaire {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    
    String[] token = br.readLine().split(" ");
    int m = Integer.parseInt(token[0]), n = Integer.parseInt(token[1]);
    int s0 = Integer.parseInt(token[2]);
    
    ArrayList<Integer> a = new ArrayList<>();
    ArrayList<Integer> b = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      token = br.readLine().split(" ");
      a.add(Integer.parseInt(token[0]));
      b.add(Integer.parseInt(token[1]));
    }

    // BFS
    Queue<Integer> q = new LinkedList<Integer>();
    q.offer(s0); // this is the source vertex
    
    int INF = 1000000000; // 1 Billion, safe enough as there are at most m <= 10^6-2 (1 Million - 2) in the worst case path
    ArrayList<Integer> Dist = new ArrayList<>();
    for (int i = 0; i < m; ++i)
      Dist.add(INF);
    Dist.set(s0, 0);
    
    while (!q.isEmpty()) { // BFS routine
      int u = q.poll(); // front of the queue, FIFO
      // pw.println("u = " + u + " dist[u] = " + Dist.get(u));
      
      for (int i = 0; i < n; ++i) { // up to n moves
        int v = (u*a.get(i) + b.get(i)) % m; // be-careful with this line...
        // pw.println("v = " + v + " dist[v] = " + Dist.get(v));
        if (!Dist.get(v).equals(INF)) continue;
        // pw.println("relaxation happens");
        Dist.set(v, Dist.get(u)+1);
        q.offer(v);
      }
    }
    
    if (!Dist.get(0).equals(INF))
      pw.println(Dist.get(0));
    else
      pw.println(-2);
    
    pw.close();
  }
}