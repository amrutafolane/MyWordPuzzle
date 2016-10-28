# MyWordPuzzle
Algorithms Analysis and  Data Structures Project 4

User input: 
No. of rows: r
No. of cols: c
for the word puzzle grid to be formed.

Algorithm:
1. Create a hash table and upload the given dictionary of words into it by separate chain hashing method.
2. Create a empty word puzzle grid of size (r,c) and populate it randomly with alphabets.
3. Accumulate all the words/ subwords in the grid vertically and horizantally and add them to the arrayList. Also, reverse these words and store them to.
4.//diagonal 
5. Delete all the duplicates from this arrayList.
6. Check this arrayList against the hashTable and delete the unmatched ones.
7. This new edited arrayList will give us the words that are present in the word grid, which exist in the given dictionary of words.
