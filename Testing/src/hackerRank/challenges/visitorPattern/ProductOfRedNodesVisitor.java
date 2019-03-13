package hackerRank.challenges.visitorPattern;

public class ProductOfRedNodesVisitor extends TreeVis {

	private static final int MOD = 1000000007;
	private long product = 1;

	public int getResult() {

		return (int) product;
	}

	public void visitNode(TreeNode node) {
		if (Color.RED.equals(node.getColor())) {
			product = ( product * (node.getValue() % MOD)) % MOD;
		}
	}

	public void visitLeaf(TreeLeaf leaf) {
		if (Color.RED.equals(leaf.getColor())) {
			product = ( product * (leaf.getValue() % MOD)) % MOD;
		}
	}
}
