package hackerRank.challenges.visitorPattern;

class FancyVisitor extends TreeVis {
	
	private int sumOfEvenNodes = 0;
	private int sumOfGreenLeafs = 0;

	public int getResult() {
		return Math.abs(sumOfEvenNodes - sumOfGreenLeafs);
	}

	public void visitNode(TreeNode node) {
		if( node.getDepth() % 2 == 0 ) {
			sumOfEvenNodes += node.getValue();
		}
	}

	public void visitLeaf(TreeLeaf leaf) {
		if(Color.GREEN.equals(leaf.getColor())) {
			sumOfGreenLeafs += leaf.getValue();
		}
	}
}