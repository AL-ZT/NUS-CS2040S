import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Participant {
    public long time;
    public int count;
    public long id;

    public Participant(long t, int c, long id) {
        this.time = t;
        this.count = c;
        this.id = id;
    }
}

public class kaploeb {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        int l = Integer.parseInt(token[0]);
        int k = Integer.parseInt(token[1]);
        long s = Long.parseLong(token[2]);
        HashMap<Long, Participant> m = new HashMap<>();
        ArrayList<Participant> completed = new ArrayList<>();
        
        for (int i = 0; i < l; i++) {
            token = br.readLine().split(" ");
            long startNo = Long.parseLong(token[0]);
            String[] HM = token[1].split("\\.");
            long time = (Long.parseLong(HM[0]) * 60) + Long.parseLong(HM[1]);
            Participant p = null;
            
            if (!m.containsKey(startNo)) {
                p = new Participant(time, 1, startNo);
                m.put(startNo, p);
            } else {
                p = m.get(startNo);
                p.count++;
                p.time += time;
            }
            
            if (p.count == k) {
                completed.add(p);
            }
        }
        
        
        Collections.sort(completed, new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                Long t1 = p1.time;
                Long t2 = p2.time;
                int comp = t1.compareTo(t2);
                if (comp != 0) {
                    return comp;
                }

                Long i1 = p1.id;
                Long i2 = p2.id;
                return i1.compareTo(i2);
            }
        });

        completed.forEach(i -> System.out.println(i.id));
    }
}

