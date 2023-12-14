// this code is *NOT* AC
// TWO important modificationS are needed to get AC
// review the recording

import java.util.*;

class tildes {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(), q = sc.nextInt(); sc.nextLine();
    UnionFind UF = new UnionFind(n);
    while (q-- > 0) {
      String t_or_q = sc.next();
      if (t_or_q.equals("t")) { // do "t" (guest a and guest b chat and then never leave the group)
        int a = sc.nextInt(), b = sc.nextInt(); sc.nextLine();
        --a; --b; // go to 0-based indexing
        UF.unionSet(a, b); // study the library code below
      }
      else { // do "q" (what is the size of the group that contains guest a)
        int a = sc.nextInt(); sc.nextLine();
        --a; // go to 0-based indexing
        System.out.println(UF.sizeOfSet(a)); // study the library code below
      }
    }
  }
}



// from https://github.com/stevenhalim/cpbook-code/blob/master/ch2/ourown/unionfind_ds.java
// Union-Find Disjoint Sets Library written in OOP manner, using both path compression and union by rank heuristics
class UnionFind {                                              // OOP style
  private ArrayList<Integer> p, rank, setSize;
  private int numSets;

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
