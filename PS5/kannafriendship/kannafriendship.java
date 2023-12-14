import java.util.*;
import java.io.*;

class Interval {
    int start;
    int end;

    public Interval(int s, int e) {
        this.start = s;
        this.end = e;
    }

    @Override
    public String toString() {
        return "[" + this.start + "," + this.end + "]";
    }
}

public class kannafriendship {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] token = br.readLine().split(" ");
        int N = Integer.parseInt(token[0]);
        int Q = Integer.parseInt(token[1]);
        int totalValue = 0;
        TreeSet<Interval> tracks = new TreeSet<>((a, b) -> {
            int comp = a.start - b.start;
            if (comp == 0) {
              return a.end - b.end;
            }
            return comp;
        });
        
        for (int i = 0; i < Q; i++) {
            token = br.readLine().split(" ");
            int t = Integer.parseInt(token[0]);
            
            if (t == 1) {
                int s = Integer.parseInt(token[1]);
                int e = Integer.parseInt(token[2]);
                Interval input = new Interval(s, e);
                if (s >= 1 && e <= N) {
                    // Merge left
                    Interval m = tracks.floor(input);
                    //System.out.println("Input: " + input + " Floor: " + m);
                    if (m != null && m.end >= input.start) {
                        //System.out.println("Merging left with " + m);
                        input.start = Math.min(m.start, input.start);
                        input.end = Math.max(m.end, input.end);
                        totalValue -= (m.end - m.start + 1);
                        tracks.remove(m);
                    }
                    // Merge right
                    m = tracks.ceiling(input);
                    //System.out.println("Ceiling: " + m);
                    while (m != null && input.end >= m.start) {
                        //System.out.println("Merging right with " + m);
                        input.start = Math.min(m.start, input.start);
                        input.end = Math.max(input.end, m.end);
                        totalValue -= (m.end - m.start + 1);
                        tracks.remove(m);
                        m = tracks.ceiling(input);
                    }
                    tracks.add(input);
                    totalValue += (input.end - input.start + 1);
                }
                //System.out.println(tracks);
            } else if (t == 2) {
                pw.println(totalValue);
            }
        }
        pw.close();
    }
}

