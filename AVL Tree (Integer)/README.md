<h1>AVL Tree</h1>

AVL Tree is a Balanced Binary Search Tree

"AVL tree is a self-balancing binary search tree. It was the first such data structure to be invented. In an AVL tree, the heights of the two child subtrees of any node differ by at most one; if at any time they differ by more than one, rebalancing is done to restore this property"

Balanced = | Height(Left Subtree) - Height(Right Subtree) |< 1

<hr>

<table>
  <tr>
    <th></th>
    <th>Average Case</th>
    <th>Worst Case</th>
  </tr>
  <tr>
    <td><strong>Space</strong></td>
    <td>O(n)</td>
    <td>O(n)</td>
  </tr>
  <tr>
    <td><strong>Search</strong></td>
    <td>O(log n)</td>
    <td>O(log n)</td>
  </tr>
    <tr>
    <td><strong>Inserty</strong></td>
    <td>O(log n)</td>
    <td>O(log n)</td>
  </tr>
    <tr>
    <td><strong>Delete</strong></td>
    <td>O(log n)</td>
    <td>O(log n)</td>
  </tr>
</table>

<hr>

<h5> Insertions and Deletions may require the tree to be reblanced by one or more tree rotations. </h5>
