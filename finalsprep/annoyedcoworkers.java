package demos;

// Lab Group 3, Lab Tutor Ramapriyan
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Map.Entry;

public class annoyedcoworkers {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tokens = br.readLine().split(" ");
    int help = Integer.parseInt(tokens[0]), coworkers = Integer.parseInt(tokens[1]);
    TreeMap<Long, PriorityQueue<Coworker>> map = new TreeMap<>();
    long maxAnnoyance = 0;

    while (coworkers-- > 0) {
      tokens = br.readLine().split(" ");
      int a = Integer.parseInt(tokens[0]), d = Integer.parseInt(tokens[1]);
      Coworker coworker = new Coworker(a, d);
      maxAnnoyance = Math.max(maxAnnoyance, a);
      annoyedcoworkers.AddCoworkersToMap(map, coworker);
    }

    while (help-- > 0) {
      Entry<Long, PriorityQueue<Coworker>> entry = map.firstEntry();
      Coworker coworker = entry.getValue().poll();
      if (entry.getValue().isEmpty()) {
        map.pollFirstEntry();
      }
      coworker.Help();
      maxAnnoyance = Math.max(maxAnnoyance, coworker.annoyance);
      AddCoworkersToMap(map, coworker);
    }

    System.out.println(maxAnnoyance);
  }

  public static void AddCoworkersToMap(TreeMap<Long, PriorityQueue<Coworker>> map, Coworker coworker) {
    PriorityQueue<Coworker> coworkers = map.get(coworker.PostAnnoyance());
    if (coworkers == null) {
      coworkers = new PriorityQueue<>((Coworker a, Coworker b) -> {
        return b.annoyance < a.annoyance ? -1 : b.annoyance > a.annoyance ? 1 : 0;
      });
      map.put(coworker.PostAnnoyance(), coworkers);
    }
    coworkers.add(coworker);
  }
}

class Coworker {
  public long annoyance;
  public int depression;

  public Coworker(int a, int d) {
    this.annoyance = a;
    this.depression = d;
  }

  public void Help() {
    this.annoyance += this.depression;
  }

  public long PostAnnoyance() {
    return this.annoyance + this.depression;
  }
}
