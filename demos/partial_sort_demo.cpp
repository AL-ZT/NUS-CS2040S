// also see the example section of https://en.cppreference.com/w/cpp/algorithm/partial_sort

#include <bits/stdc++.h>
using namespace std;

int main() {
  vector<int> A = {5, 3, 2, 3, 4, 1, 5, 7, 9, 100, 343, 12378, 343, 5, 23, 34, 3, 5};
  // sort(A.begin(), A.end()); // try comparing line 6 vs line 7
  partial_sort(A.begin(), A.begin()+10, A.end()); // only sort the first 10 elements, with no guarantee of what is the ordering of the 11th elements to the end...
  for (auto& Ai : A)
    cout << Ai << ' ';
  cout << '\n';
  // correct output of partial sort: 1 2 3 3 3 4 5 5 5 5] [343 12378 343 100 23 34 9 7 
  //                           first 10 are the smallest] [ignore the remainder of the elements, not guaranteed
  return 0;
}
