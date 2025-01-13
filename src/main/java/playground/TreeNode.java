package playground;

public record TreeNode(int val, TreeNode left, TreeNode right) {
  public boolean isLeaf() {
    return this.left == null && this.right == null;
  }
}
