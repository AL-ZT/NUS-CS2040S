package finalsprep;

import java.io.*;
import java.util.*;

public class colorland {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    int N = Integer.parseInt(br.readLine());

    // Store minimum moves to destination based on last seen color.
    HashMap<String, Integer> lastSeen = new HashMap<>();

    // Store in Stack to iterate from the back.
    Stack<String> colorSeq = new Stack<>();
    for (int i = 0; i < N; i++) {
      colorSeq.push(br.readLine());
    }

    // Add destination to last seen.
    lastSeen.put(colorSeq.pop(), 1);
    while (!colorSeq.isEmpty()) {
      String color = colorSeq.pop();
      int minMoves = N + 1;
      for (int movesRequired : lastSeen.values()) {
        // Update moves needed based on color.
        minMoves = Math.min(minMoves, movesRequired);
      }
      // Update last seen color.
      if (lastSeen.containsKey(color)) {
        lastSeen.replace(color, minMoves + 1);
      } else {
        lastSeen.put(color, minMoves + 1);
      }
    }
    int minMoves = N + 1;
    for (int movesRequired : lastSeen.values()) {
      // Update moves needed based on color.
      minMoves = Math.min(minMoves, movesRequired);
    }

    pw.println(minMoves);
    pw.close();
    br.close();
  }
}