package hackerRank.challenges.visitorPattern;

public class SumInLeavesVisitor extends TreeVis {
	
	private int sum = 0;
	
	public int getResult() {
		return sum;
	}

	public void visitNode(TreeNode node) {
	}

	public void visitLeaf(TreeLeaf leaf) {
		sum += leaf.getValue();
	}
}