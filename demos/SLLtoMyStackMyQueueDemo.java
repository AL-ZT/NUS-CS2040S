/*
Plagiarism Policy:

A/Prof Steven Halim provides this implementation of basic Singly Linked List (SLL) for his classes
in National University of Singapore (NUS) School of Computing (SoC).

This code is supposed to be studied by his students to understand the technical details
of Singly Linked List (SLL) implementation.

His style is to test his students on "application" of this data structure,
not about re-inventing Singly Linked List (SLL) again and again,
thus his assignments and tests rarely ask students to reuse this code verbatim.
(anyway, you can always use Java LinkedList to do the same).

If you arrive at this source code from another module that is still testing you on how
to (re)-implement Singly (or Doubly) Linked List (and grade you for that), note that you can still
use this code below, but at your own risk, as you may be accidentally flagged as
commiting plagiarism if your other classmates do the same.
*/



import java.io.*;
import java.util.*;


// https://visualgo.net/en/list?slide=3-1
class Vertex { // we can use either C struct or C++/Java class
  int item; // the data is stored here, an integer in this example
  Vertex next; // this pointer tells us where is the next vertex
}


class SLL {
  protected Vertex head;
  protected Vertex tail;

  private int mysecret = 7;

  // this is the version as shown in https://visualgo.net/en/list, SLL with both head and tail pointers
  public SLL() {
    head = null;
    tail = null;
  }

  // https://visualgo.net/en/list?slide=3-8
  public void offerFirst(int v) {
    Vertex vtx = new Vertex(); // create new vertex vtx from item v
    vtx.item = v;
    vtx.next = head; // link this new vertex to the (old) head vertex
    if (head == null) tail = vtx; // if previously it was an empty SLL, then tail = head too
    head = vtx; // the new vertex becomes the new head
  }

  // https://visualgo.net/en/list?slide=3-12
  public void offerLast(int v) {
    if (head == null) // an important corner case
      offerFirst(v);
    else {
      Vertex vtx = new Vertex(); // create new vertex vtx from item v, O(1)
      vtx.item = v; // O(1)

      // The O(N) version (if we do not use tail pointer)
      // Vertex tail = head; // we have to start from head
      // while (tail.next != null) // while we have not reached the last element, O(N) - the slow part
      //   tail = tail.next; // the pointers are pointing to the higher index
      // now we can use the tail pointer, after searching for it in O(N)

      // The O(1) version (just by simply maintaining tail pointer at all times)
      tail.next = vtx; // just link this, as tail is the i = (N-1)-th item, O(1)
      tail = vtx; // now update the tail pointer, O(1)
    }
  }

  public int peekFirst() {
    if (head == null) return -1; // avoid crashing when the SLL is empty (assumption: the SLL contains only non-negative integers, so -1 is a 'safe' symbol to indicate 'does not exist')
    return head.item;
  }

  public int peekLast() {
    if (tail == null) return -1; // avoid crashing when the SLL is empty (assumption: the SLL contains only non-negative integers, so -1 is a 'safe' symbol to indicate 'does not exist')
    return tail.item;
  }

  // https://visualgo.net/en/list?slide=3-15
  public void pollFirst() {
    if (head == null) return; // avoid crashing when the SLL is empty
    Vertex temp = head; // so we can delete it later
    head = head.next; // book keeping, update the head pointer
    if (head == null) tail = null; // if the SLL is now becomes empty, then tail = null too
    temp = null; // which is the old head
  }

  public Boolean isEmpty() {
    return head == null;
  }
}



// semi-live demo to extend (wrap) SLL to a new class MyStack that can only access subset of SLL features
class MyStack extends SLL {
  public void push(int v) {
    this.offerFirst(v);
  }

  public int peek() {
    return this.peekFirst();
  }

  public int pop() {
    int top = this.peek();
    this.pollFirst();
    return top;
  }

  // no isEmpty in MyStack but it can use isEmpty (public) of class SLL
}



// another semi-live demo to extend (wrap) SLL to a new class MyQueue that can only access another subset of SLL features
class MyQueue extends SLL {
  public void offer(int v) {
    this.offerLast(v);
  }

  public int peek() {
    return this.peekFirst();
  }

  public int poll() {
    int top = this.peek();
    this.pollFirst();
    return top;
  }

  // no isEmpty in MyQueue but it can use isEmpty (public) of class SLL
}



class SLLtoMyStackMyQueueDemo {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    // experiment with this baseline code to familiarise yourself with the very basic idea of Linked List data structure
    pw.println("Our own Singly Linked List (SLL)");
    SLL l = new SLL();
    l.offerFirst(5);
    l.offerFirst(2);
    l.offerLast(6);
    l.offerFirst(7);

    pw.println(l.peekFirst()); // output 7 as the SLL is 7 (head)->2->5->6 now
    l.pollFirst();
    pw.println(l.peekFirst()); // output 2 as the SLL is 2 (head)->5->6 now
    l.pollFirst();
    pw.println(l.peekFirst()); // output 5 as the SLL is 5 (head)->6 now
    l.pollFirst();
    pw.println(l.peekFirst()); // output 6 as the SLL is 6 (head) now
    l.pollFirst(); // empty after this
    pw.println(l.peekFirst()); // -1 (empty SLL), already safe-guarded, won't crash


    pw.println();
    pw.println("Java LinkedList version");
    LinkedList<Integer> LL = new LinkedList<>();
    LL.offerFirst(5);
    LL.offerFirst(2);
    LL.offerLast(6);
    LL.offerFirst(7);

    pw.println(LL.peekFirst()); // output 7 as the SLL is 7 (head)->2->5->6 now
    LL.pollFirst();
    pw.println(LL.peekFirst()); // output 2 as the SLL is 2 (head)->5->6 now
    LL.pollFirst();
    pw.println(LL.peekFirst()); // output 5 as the SLL is 5 (head)->6 now
    LL.pollFirst();
    pw.println(LL.peekFirst()); // output 6 as the SLL is 6 (head) now
    LL.pollFirst(); // empty after this
    pw.println(LL.peekFirst() == null ? -1 : LL.peekFirst()); // -1 (empty SLL), need to do check first


    pw.println();
    pw.println("Equivalency testing on a very large test case");

    // large random test
    long begin = System.currentTimeMillis();

    Random rand = new Random();
    SLL ours = new SLL();
    LinkedList<Integer> theirs = new LinkedList<>();
    assert ours.isEmpty() && theirs.isEmpty(); // both empty at the start
    int N = 1000000; // usually just a few seconds
    for (int i = 0; i < N; ++i) { // insert N random integers to both data structures
      int value = rand.nextInt();
      if (rand.nextInt()%2 == 0) {
        ours.offerFirst(value);
        theirs.offerFirst(value);
      }
      else {
        ours.offerLast(value);
        theirs.offerLast(value);
      }
    }
    assert !ours.isEmpty() && !theirs.isEmpty(); // both not empty (has N entries) by now
    while (!ours.isEmpty()) {
      assert ours.peekFirst() == theirs.peekFirst(); // front-most value (index 0) should match
      assert ours.peekLast() == theirs.peekLast(); // last value (index N-1) should match
      ours.pollFirst();
      theirs.pollFirst();
    }
    assert ours.isEmpty() && theirs.isEmpty(); // both empty at the end

    pw.printf("Test time = %fs\n", (System.currentTimeMillis()-begin)/1000.0);
    pw.println("If there is no assertion (Run Time Error), then all is good");



    pw.println();
    MyStack ms = new MyStack();
    // Deque<Integer> ms = new ArrayDeque<>(); // again, why reinvent the wheel :O..., commenting the previous line (MyStack) and using this line (Java Stack) also works
    ms.push(5);
    ms.push(2);
    ms.push(7);
    pw.println(ms.pop()); // output 7 as the SLL was 7 (head)->2->5, now the SLL is 2 (head)->5
    pw.println(ms.pop()); // output 2 as the SLL was 2 (head)->5, now the SLL is 5 (head)
    // pw.println(ms.mysecret); // not allowed, as it is private for class SLL. MyStack cannot access this
    pw.println(ms.pop()); // output 5 as the SLL was 5 (head), now the SLL is empty
    pw.println(ms.isEmpty() ? -1 : ms.pop()); // output -1 (empty SLL), already safe-guarded, won't crash



    pw.println();
    MyQueue mq = new MyQueue();
    // Queue<Integer> mq = new LinkedList<>(); // notice, Java Queue is an interface, not a class
    mq.offer(5);
    mq.offer(2);
    mq.offer(7);
    pw.println(mq.poll()); // output 5 as the SLL was 5 (head)->2->7, now the SLL is 2 (head)->7
    pw.println(mq.poll()); // output 2 as the SLL was 2 (head)->7, now the SLL is 7 (head)
    pw.println(mq.poll()); // output 7 as the SLL was 7 (head), now the SLL is empty
    pw.println(mq.isEmpty() ? -1 : mq.poll()); // output -1 (empty SLL), already safe-guarded, won't crash



    pw.close();
  }
}
