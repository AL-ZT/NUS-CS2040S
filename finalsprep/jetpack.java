package finalsprep;

import java.io.*;
import java.util.*;

public class jetpack {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int goal = Integer.parseInt(br.readLine());
    char[][] field = new char[10][goal];

    for (int i = 9; i >= 0; i--) {
      field[i] = br.readLine().toCharArray();
    }

    Deque<TimePressed> record = new LinkedList<>();
    jetpack.dfs(0, 0, field, goal, record);

    pw.println(record.size());
    while (!record.isEmpty()) {
      TimePressed time = record.poll();
      pw.println(time.start + " " + time.holdTime);
    }

    pw.close();
    br.close();
  }

  public static boolean dfs(int x, int y, char[][] field, int goal, Deque<TimePressed> record) {
    if (field[y][x] == 'X') {
      return false;
    }
    field[y][x] = 'X';

    if (x + 1 == goal) {
      return true;
    }

    if (dfs(x + 1, Math.max(0, y - 1), field, goal, record)) {
      return true;
    }

    TimePressed nextRecord = record.peekLast();
    if (nextRecord == null || !nextRecord.isNextInFrame(x)) {
      nextRecord = new TimePressed(x);
      record.offer(nextRecord);
    } else {
      nextRecord.incrementHoldTime();
    }
    if (dfs(x + 1, Math.min(9, y + 1), field, goal, record)) {
      return true;
    }
    if (!nextRecord.undoHold()) {
      record.pollLast();
    }
    return false;
  }
}

class TimePressed {
  public int start;
  public int holdTime;

  public TimePressed(int start) {
    this.start = start;
    this.holdTime = 1;
  }

  public void incrementHoldTime() {
    this.holdTime += 1;
  }

  public boolean undoHold() {
    this.holdTime -= 1;
    return this.holdTime > 0;
  }

  public boolean isNextInFrame(int x) {
    return this.start + this.holdTime == x;
  }
}