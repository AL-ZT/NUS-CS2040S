package finalsprep;

import java.io.*;
import java.util.*;

public class jurassicjigsaw {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    int samples = Integer.parseInt(st.nextToken());
    int strLen = Integer.parseInt(st.nextToken());

    String[] sequences = new String[samples];
    for (int i = 0; i < samples; i++) {
      sequences[i] = br.readLine();
    }

    JurassicJigsawUFDS ufds = new JurassicJigsawUFDS(samples);
    ArrayList<EdgeInfo> edges = new ArrayList<>((samples * (samples - 1)) / 2);
    for (int i = 0; i < samples; i++) {
      for (int j = i + 1; j < samples; j++) {
        edges.add(new EdgeInfo(i, j, jurassicjigsaw.unlikeliness(sequences[i], sequences[j], strLen)));
      }
    }
    Collections.sort(edges);

    int edgesSelected = 0;
    long unlikeliness = 0;
    for (int i = 0; i < edges.size(); i++) {
      EdgeInfo edge = edges.get(i);
      if (ufds.sameSet(edge.src, edge.dst)) {
        continue;
      }

      ufds.union(edge.src, edge.dst);
      unlikeliness += edge.weight;
      pw.println(edge.src + " " + edge.dst);
      edgesSelected += 1;
      if (edgesSelected == samples - 1) {
        break;
      }
    }

    System.out.println(unlikeliness);
    pw.close();
    br.close();
  }

  public static int unlikeliness(String a, String b, int strLen) {
    int unlikeliness = 0;
    for (int i = 0; i < strLen; i++) {
      unlikeliness += a.charAt(i) == b.charAt(i) ? 0 : 1;
    }
    return unlikeliness;
  }
}

class EdgeInfo implements Comparable<EdgeInfo> {
  int src;
  int dst;
  int weight;

  public EdgeInfo(int src, int dst, int weight) {
    this.src = src;
    this.dst = dst;
    this.weight = weight;
  }

  @Override
  public int compareTo(EdgeInfo other) {
    if (this.weight == other.weight) {
      return this.src - other.src;
    }
    return this.weight - other.weight;
  }
}

class JurassicJigsawUFDS {
  int[] arr;
  int[] ranks;
  int[] sizes;

  public JurassicJigsawUFDS(int len) {
    this.arr = new int[len];
    this.ranks = new int[len];
    this.sizes = new int[len];
    for (int i = 0; i < len; i++) {
      this.arr[i] = i;
      this.sizes[i] = 1;
    }
  }

  public void union(int i, int j) {
    i = findSet(i);
    j = findSet(j);
    if (i == j) {
      return;
    }
    int newParent = i;
    int oldParent = j;
    if (this.ranks[i] < this.ranks[j]) {
      newParent = j;
      oldParent = i;
    } else if (this.ranks[newParent] == this.ranks[oldParent]) {
      this.ranks[newParent] += 1;
    }

    this.arr[oldParent] = newParent;
    this.sizes[newParent] += this.sizes[oldParent];
  }

  public int findSet(int i) {
    ArrayList<Integer> paths = new ArrayList<>();
    while (i != this.arr[i]) {
      paths.add(i);
      i = arr[i];
    }
    for (Integer index : paths) {
      this.arr[index] = i;
    }
    return i;
  }

  public boolean sameSet(int i, int j) {
    return this.findSet(i) == this.findSet(j);
  }

  public int getSetSize(int i) {
    return this.sizes[this.findSet(i)];
  }
}
