import java.util.Arrays;
import java.util.PriorityQueue;

public class partial_sort_demo {
  public static void main(String[] args) {
    int[] A = {5, 3, 2, 3, 4, 1, 5, 7, 9, 100, 343, 12378, 343, 5, 23, 34, 3, 5};
    // Arrays.sort(A); // try comparing line 7 (sort all) vs line 8 (wrong) vs line 9 (proper partial sort)
    // Arrays.sort(A, 0, 10); // only sort the first 10 indices
    partialSort(A, 10); // Get the first 10 smallest elements of A, afterwards the result is not guaranteed
    for (int Ai : A)
      System.out.print(Ai + " ");
    System.out.println();
    // correct output of partial sort: 1 2 3 3 3 4 5 5 5 5] [343 12378 343 100 23 34 9 7 
    // this code                     : 1 2 3 3 3 4 5 5 5 5] [343 12378 343 5 23 34 3 5 
    //                           first 10 are the smallest] [ignore the remainder of the elements, not guaranteed
  }

  public static void partialSort(int[] A, int k) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
    
    for (int i = 0; i < N; ++i) { // Add the first k elements to the max-heap
      maxHeap.offer(-A[i]);
    }
    
    for (int i = k; i < A.length; ++i) { // Replace elements in the max-heap if a smaller element is found
      if (-A[i] > maxHeap.peek()) {
        maxHeap.poll(); // Remove the largest element
        maxHeap.offer(-A[i]); // Add the smaller element
      }
    }
    
    for (int i = 0; i < k; ++i) // Copy the elements from the max-heap back to the first k slots of the original array (may overwrite stuffs)
      A[k-i-1] = -maxHeap.poll();
  }
}
