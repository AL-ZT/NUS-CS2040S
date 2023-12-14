package finalsprep;

import java.io.*;
import java.util.*;

public class conquest {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    StringTokenizer st = new StringTokenizer(br.readLine());
    int islands = Integer.parseInt(st.nextToken());
    int bridges = Integer.parseInt(st.nextToken());

    HashSet<Integer> connected = new HashSet<>(islands);
    Hashtable<Integer, ArrayList<Integer>> adjList = new Hashtable<>();

    while (bridges-- > 0) {
      st = new StringTokenizer(br.readLine());
      int first = Integer.parseInt(st.nextToken());
      int second = Integer.parseInt(st.nextToken());
      ArrayList<Integer> firstNeighbours = adjList.get(first);
      if (firstNeighbours == null) {
        firstNeighbours = new ArrayList<Integer>();
        adjList.put(first, firstNeighbours);
      }
      ArrayList<Integer> secondNeighbours = adjList.get(second);
      if (secondNeighbours == null) {
        secondNeighbours = new ArrayList<Integer>();
        adjList.put(second, secondNeighbours);
      }
      firstNeighbours.add(second);
      secondNeighbours.add(first);
    }

    int[] armies = new int[islands + 1];
    for (int i = 1; i <= islands; i++) {
      armies[i] = Integer.parseInt(br.readLine());
    }
    long mainArmy = armies[1];

    PriorityQueue<BridgeInformation> pq = new PriorityQueue<>();
    ArrayList<Integer> neighbours = adjList.get(1);
    connected.add(1);
    for (int i = 0; i < neighbours.size(); i++) {
      int island = neighbours.get(i);
      pq.offer(new BridgeInformation(island, armies[island]));
      connected.add(island);
    }

    while (!pq.isEmpty() && mainArmy > pq.peek().army) {
      BridgeInformation attackedIsland = pq.poll();
      mainArmy += attackedIsland.army;
      neighbours = adjList.get(attackedIsland.id);
      for (int i = 0; i < neighbours.size(); i++) {
        int island = neighbours.get(i);
        if (!connected.contains(island)) {
          pq.offer(new BridgeInformation(island, armies[island]));
          connected.add(island);
        }
      }
    }

    pw.println(mainArmy);
    pw.close();
    br.close();
  }
}

class BridgeInformation implements Comparable<BridgeInformation> {
  public int id;
  public int army;

  public BridgeInformation(int id, int armySize) {
    this.id = id;
    this.army = armySize;
  }

  @Override
  public int compareTo(BridgeInformation other) {
    return this.army - other.army;
  }
}