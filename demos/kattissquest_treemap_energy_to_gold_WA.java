// Kattis's Quest
// simulation of greedy algorithm (as outlined in the problem description)
// almost complete solution: using TreeMap of <Energy Level to Gold>, will WA if there are two quests with the same energy level

import java.io.*;
import java.util.*;

class kattissquest_treemap_energy_to_gold_WA {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // BufferedReader br = new BufferedReader(new FileReader("in.txt"));
    PrintWriter pw = new PrintWriter(System.out);
    int N = Integer.parseInt(br.readLine());
    TreeMap<Integer, Integer> pool = new TreeMap<>(); // praying for no two quests have same energy level E
    while (N-- > 0) {
      String[] token = br.readLine().split(" ");
      if (token[0].equals("add")) {
        int E = Integer.parseInt(token[1]), G = Integer.parseInt(token[2]);
        pool.put(E, G);
      }
      else { // if (token[0] == "get")
        int X = Integer.parseInt(token[1]);
        long ans = 0;
        while (X > 0) {
          Map.Entry<Integer, Integer> pos = pool.lowerEntry(X+1); // find largest energy quest from current pool of quest
          if (pos == null) break;
          X -= pos.getKey();
          ans += pos.getValue();
          pool.remove(pos.getKey());
        }
        pw.println(ans);
      }
    }
    pw.close();
  }
}
