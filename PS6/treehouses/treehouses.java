import java.util.*;
import java.io.*;

class Treehouse {
    int id;
    double x;
    double y;
    
    public Treehouse(int i, double x, double y) {
        this.id = i;
        this.x = x;
        this.y = y;
    }
}

class Cable implements Comparable<Cable> {
    int start;
    int end;
    Double length;
    
    public Cable(int s, int e, Double l) {
        this.start = s;
        this.end = e;
        this.length = l;
    }
    
    @Override
    public int compareTo(Cable c) {
        if (!this.length.equals(c.length)) {
            return this.length.compareTo(c.length);
        } else {
            return this.start - c.start;
        }
    }

    @Override
    public String toString() {
        return "<" + this.start + "," + this.end + "," + this.length + ">";
    }
}

class UnionFind {
    public ArrayList<Integer> p, rank, setSize;
    public int numSets;

    public UnionFind(int N) {
      p = new ArrayList<>(N);
      rank = new ArrayList<>(N);
      setSize = new ArrayList<>(N);
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
        return ret; } }

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

public class treehouses {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] token = br.readLine().split(" ");
        int N = Integer.parseInt(token[0]); // n treehouses
        int E = Integer.parseInt(token[1]); // first e connected
        int P = Integer.parseInt(token[2]); // no. of cables in place already
        int num_taken = 0;
        ArrayList<Cable> EL = new ArrayList<>();
        ArrayList<Treehouse> th = new ArrayList<>();
        UnionFind uf = new UnionFind(N);
        
        for (int i = 0; i < N; i++) { // edge list
            token = br.readLine().split(" ");
            Double x = Double.parseDouble(token[0]);
            Double y = Double.parseDouble(token[1]);
            if (i != 0) {
                for (int j = 0; j < th.size(); j++) {
                    double l = Math.sqrt(
                                    Math.pow(Math.abs(x - th.get(j).x), 2) + 
                                            Math.pow(Math.abs(y - th.get(j).y), 2));
                    EL.add(new Cable(j, i, l));
                }
            }
            th.add(new Treehouse(i, x, y));
        }
        for (int i = 1; i < E; i++) { // union existing connections
            uf.unionSet(0, i);
            num_taken++;
        }
        for (int i = 0; i < P; i++) { // existing cables
            token = br.readLine().split(" ");
            int a = Integer.parseInt(token[0]) - 1;
            int b = Integer.parseInt(token[1]) - 1;
            if (!uf.isSameSet(a, b)) {
                uf.unionSet(a, b);
                num_taken++;
            }
        }

        Collections.sort(EL);
        //pw.println(EL);

        double mst_cost = 0; // no edge has been taken
        int edges = N * (N-1) / 2;
        for (int i = 0; i < edges; ++i) { // up to O(E)
        Cable front = EL.get(i);
            if (uf.isSameSet(front.start, front.end)) continue;
            mst_cost += front.length; // add w of this edge
            uf.unionSet(front.start, front.end);// link them
            ++num_taken; // 1 more edge is taken
            if (num_taken == N-1) break; // optimization
        }

        pw.println(mst_cost);
        pw.close();
    }
}