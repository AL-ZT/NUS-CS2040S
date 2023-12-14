// I put TWO subtle bugs in this code

import java.util.*;
import java.io.*;

// Union-Find Disjoint Sets Library written in OOP manner, using both path compression and union by rank heuristics
class UnionFind {                                              // OOP style
  private ArrayList<Integer> p, rank, setSize;
  private int numSets;

  public UnionFind(int N) {
    p = new ArrayList<Integer>(N);
    rank = new ArrayList<Integer>(N);
    setSize = new ArrayList<Integer>(N);
    numSets = N;
    for (int i = 0; i < N; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1);
    }
  }

  public int findSet(int i) { 
    if (p.get(i) == i) return i;
    else {
      int ret = findSet(p.get(i)); p.set(i, ret);
      return ret;
    }
  }

  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { numSets--; 
    int x = findSet(i), y = findSet(j);
    // rank is used to keep the tree short
    if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
    else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
                                     if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1); } } }
  public int numDisjointSets() { return numSets; }
  public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}

class cats {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      String[] token = br.readLine().split(" ");
      int M = Integer.parseInt(token[0]), C = Integer.parseInt(token[1]);
      int V = C, E = C*(C-1)/2;

      ArrayList<IntegerTriple> EL = new ArrayList<>();
      for (int i = 0; i < E; ++i) {
        token = br.readLine().split(" ");
        int u = Integer.parseInt(token[0]), v = Integer.parseInt(token[1]), w = Integer.parseInt(token[2]);
        EL.add(new IntegerTriple(w, u, v));        // reorder as (w, u, v)
      }
      Collections.sort(EL);                        // sort by w, O(E log E)

      int mst_cost = 0, num_taken = 0;             // no edge has been taken
      UnionFind UF = new UnionFind(V);             // all V are disjoint sets
      for (int i = 0; i < E; ++i) {                // up to O(E)
        IntegerTriple front = EL.get(i);
        if (UF.isSameSet(front.second(), front.third())) continue; // check
        mst_cost += front.first();                 // add w of this edge
        UF.unionSet(front.second(), front.third());// link them
        ++num_taken;                               // 1 more edge is taken
        if (num_taken == V-1) break;               // optimization
      }

      // note: the number of disjoint sets must eventually be 1 for a valid MST
      System.out.println((mst_cost-C < M) ? "yes" : "no");
    }
  }
}

class IntegerTriple implements Comparable<IntegerTriple> {
  Integer _first, _second, _third;

  public IntegerTriple(Integer f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else if (!this.second().equals(o.second()))
      return this.second() - o.second();
    else
      return this.third() - o.third();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }

  public String toString() { return first() + " " + second() + " " + third(); }
}