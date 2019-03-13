package hackerRank.challenges.visitorPattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class VisitorPattern {
	
	static int values[];
	static Color colors[];
	static Map<Integer, Set<Integer>> nodesMap = new HashMap<>();

	public static Tree solve() {
		
		Scanner scan = new Scanner(System.in);
		
		int numberOfNodes = scan.nextInt();
		
		values = new int[numberOfNodes];
		
		for(int i = 0 ; i < numberOfNodes ; i++) {
			values[i] = scan.nextInt();
		}
		
		colors = new Color[numberOfNodes];
		
		for(int i = 0 ; i < numberOfNodes ; i++) {
			colors[i] = (scan.nextInt() == 0) ? Color.RED : Color.GREEN;
		}
		
		Tree rootNode;
		
		if( numberOfNodes == 1 ) {
			rootNode = new TreeLeaf(values[0],colors[0],0);
		} else {
			rootNode = new TreeNode(values[0],colors[0],0);
			
			for(int i = 1 ; i < numberOfNodes ; i++) {
				int u = scan.nextInt();
				int v = scan.nextInt();
				
				Set<Integer> uEdges = nodesMap.get(u);
				
				if(uEdges==null) {
					uEdges = new HashSet<>();
				}
				
				uEdges.add(v);
				nodesMap.put(u, uEdges);
				
				Set<Integer> vEdges = nodesMap.get(v);
				
				if(vEdges==null) {
					vEdges = new HashSet<>();
				}
				
				vEdges.add(u);
				nodesMap.put(v, vEdges);
			}
			
			scan.close();
			
			for(int nodeid : nodesMap.get(1) ) {
				
				nodesMap.get(nodeid).remove(1);
				createEdge(rootNode, nodeid);
			}
		}
		return rootNode;
	}
	
	private static void createEdge(Tree parent, int nodeid) {
		
		Set<Integer> nodeEdges = nodesMap.get(nodeid);
		boolean hasChild = nodeEdges!=null && !nodeEdges.isEmpty();
		
		if( hasChild ) {
			TreeNode node = new TreeNode(values[nodeid-1], colors[nodeid-1], parent.getDepth()+1);
			((TreeNode)parent).addChild(node);
			
			for(int neighborid : nodeEdges ) {
				nodesMap.get(neighborid).remove(nodeid);
				createEdge(node, neighborid);
			}
		} else {
			TreeLeaf leaf = new TreeLeaf(values[nodeid-1],colors[nodeid-1],parent.getDepth()+1);
			((TreeNode)parent).addChild(leaf);
		}
	}

	public static void main(String[] args) {
		Tree root = solve();
		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
		ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
		FancyVisitor vis3 = new FancyVisitor();

		root.accept(vis1);
		root.accept(vis2);
		root.accept(vis3);

		int res1 = vis1.getResult();
		int res2 = vis2.getResult();
		int res3 = vis3.getResult();

		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
	}
}