import java.util.*;
import java.io.*;

class Road {
    int start;
    int end;
    Long length;
    int status;
    
    public Road(int s, int e, Long l, int st) {
        this.start = s;
        this.end = e;
        this.length = l;
        this.status = st;
    }

    @Override
    public String toString() {
        return "<E: " + this.end + ", L: " + this.length + ", S: " + this.status + ">";
    }
}

class Path {
    Long dist;
    int u;
    int shamans;
    int titans;
    int status;

    public Path(Long d, int u, int s, int t, int st) {
        this.dist = d;
        this.u = u;
        this.shamans = s;
        this.titans = t;
        this.status = st;
    }

    public Path(Long d, int st) {
        this.dist = d;
        this.status = st;
        this.shamans = 0;
        this.titans = 0;
        this.u = 0;
    }

    @Override
    public String toString() {
        return "(Village: "+ (this.u+1) + ", d:" + this.dist + ", s: " + this.shamans + ", t: " + this.titans + ", st: " + this.status + ")";
    }
}

public class fendofftitan {
    public static final long INF = 1000000000000000L;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] token = br.readLine().split(" ");
        int N = Integer.parseInt(token[0]); // N villages, labelled
        int M = Integer.parseInt(token[1]); // M bidirectional roads
        int X = Integer.parseInt(token[2]) - 1; // Starting village
        int Y = Integer.parseInt(token[3]) - 1; // Ending village
        ArrayList<ArrayList<Road>> AL = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            AL.add(new ArrayList<Road>());
        }
        for (int i = 0; i < M; i++) { // Edges between villages
            token = br.readLine().split(" ");
            int Ai = Integer.parseInt(token[0]) - 1; // From village Ai
            int Bi = Integer.parseInt(token[1]) - 1; // To village Bi
            Long Wi = Long.parseLong(token[2]); // Weight/Length of road Wi
            int Ci = Integer.parseInt(token[3]); // Ci == 0/1/2 (No enemy/1 shaman/1 titan)
            AL.get(Ai).add(new Road(Ai, Bi, Wi, Ci));
            AL.get(Bi).add(new Road(Bi, Ai, Wi, Ci));
        }
        
        ArrayList<Path> dist = new ArrayList<>();

        dist = new ArrayList<>(Collections.nCopies(N, new Path(INF, 3)));
        dist.set(X, new Path(0L, 0));
        PriorityQueue<Path> pq = new PriorityQueue<>((a, b) -> {
            int t = a.titans - b.titans;
            if (t == 0) {
                int s = a.shamans - b.shamans;
                if (s == 0) {
                    return a.dist.compareTo(b.dist);
                }
                return s;
            }
            return t;
        });
        pq.offer(new Path(0L, X, 0, 0, 0));

        while (!pq.isEmpty()) {
            //pw.println("PQ: " + pq);
            Path top = pq.poll();
            long d = top.dist;
            int u = top.u;     // shortest unvisited u
            int t = top.titans;
            int s = top.shamans;
            int st = top.status;
            //pw.println(st + " " + dist.get(u).status);
            if (st > dist.get(u).status) continue;
            if (st == dist.get(u).status) {
                if (d > dist.get(u).dist) {
                    continue;
                }
            }
            for (Road v_w : AL.get(u)) {        // all edges from u
                long w = v_w.length;
                int v = v_w.end;
                if (st <= dist.get(v).status && v_w.status <= dist.get(v).status) {
                    if (st == dist.get(v).status || v_w.status == dist.get(v).status) {
                        int add = 0;
                        if (dist.get(v).status == 1) {
                            if (v_w.status == 1) add = 1;
                            if (dist.get(u).shamans + add > dist.get(v).shamans) {
                                continue;
                            } else if (dist.get(u).shamans + add == dist.get(v).shamans) {
                                if (dist.get(u).dist+w >= dist.get(v).dist) continue; // not improving, skip
                            }
                        } else if (dist.get(v).status == 2) {
                            if (v_w.status == 2) add = 1;
                            if (dist.get(u).titans + add > dist.get(v).titans) {
                                continue;
                            } else if (dist.get(u).titans + add == dist.get(v).titans) {
                                if (dist.get(u).dist+w >= dist.get(v).dist) continue; // not improving, skip
                            }
                        } else if (dist.get(v).status == 0) {
                            if (dist.get(u).dist+w >= dist.get(v).dist) continue; // not improving, skip
                        }
                    }
                    if (v_w.status == 1) {
                        pq.offer(new Path(dist.get(u).dist+w, v, s + 1, t, Math.max(st, v_w.status)));
                        dist.set(v, new Path(dist.get(u).dist+w, v, s + 1, t, Math.max(st, v_w.status)));
                    } else if (v_w.status == 2) {
                        pq.offer(new Path(dist.get(u).dist+w, v, s, t + 1, Math.max(st, v_w.status)));
                        dist.set(v, new Path(dist.get(u).dist+w, v, s, t + 1, Math.max(st, v_w.status)));
                    } else if (v_w.status == 0) {
                        pq.offer(new Path(dist.get(u).dist+w, v, s, t, Math.max(st, v_w.status)));
                        dist.set(v, new Path(dist.get(u).dist+w, v, s, t, Math.max(st, v_w.status)));
                    }
                }
            }
            //pw.println("LIST: " + dist);
        }
        
        if (dist.get(Y).dist != INF) {
            System.out.println(dist.get(Y).dist + " " + dist.get(Y).shamans + " " + dist.get(Y).titans);
        } else {
            System.out.println("IMPOSSIBLE");
        }
        pw.close();
    }
}