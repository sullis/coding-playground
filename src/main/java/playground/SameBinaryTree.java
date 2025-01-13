package playground;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SameBinaryTree {

  public static boolean isSameBinaryTree(TreeNode p, TreeNode q) {
    if ((p == null) && (q == null)) {
      return true;
    }

    if ((p == null) || (q == null)) {
      return false;
    }

    if (p.val() != q.val()) {
      return false;
    }

    return (isSameBinaryTree(p.left(), q.left()))
        && (isSameBinaryTree(p.right(), q.right()));
  }

  public static void main(String[] args) {
    TreeNode left = new TreeNode(2, null, null);
    TreeNode right = new TreeNode(8, null, null);
    TreeNode root = new TreeNode(5, left, right);

    assertTrue(isSameBinaryTree(root, root));
    assertTrue(isSameBinaryTree(left, left));
    assertTrue(isSameBinaryTree(right, right));

    assertFalse(isSameBinaryTree(left, right));
    assertFalse(isSameBinaryTree(root, right));
    assertFalse(isSameBinaryTree(root, left));
  }
}
