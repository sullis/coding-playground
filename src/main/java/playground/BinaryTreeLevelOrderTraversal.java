package playground;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/*

 Binary Tree : Level Order Traversal
 https://leetcode.com/problems/binary-tree-level-order-traversal/description/

   Given the root of a binary tree, return
   the level order traversal of its nodes' values.
   (i.e., from left to right, level by level).

 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
      if (root == null) {
        return new ArrayList<>();
      }

      List<List<Integer>> result = new ArrayList<>();

      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);

      while (!q.isEmpty()) {
        int len = q.size();
        System.out.println("q.size: " + q.size());
        List<Integer> currentLevel = new ArrayList<>();
        while (len > 0) {
          len--;
          TreeNode node = q.poll();
          currentLevel.add(node.val());
          if (node.left() != null) {
            q.offer(node.left());
          }
          if (node.right() != null) {
            q.offer(node.right());
          }
        }
        result.add(currentLevel);
      }

      return result;
    }
}
