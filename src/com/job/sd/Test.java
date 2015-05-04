package com.job.sd;

import java.util.regex.*;
import java.util.*;

class Test {
	public static void main(String... args) {
		/*
                     1
                    / \
                   2   3
                 / | \  \ 
                4  5  6  11
                 / | \
                7  8  9
                      |
                     10
		*/				  
		Node root = Node.parseTreeData("1:<2,3> 2:<4,5,6> 5:<7,8,9> 9:<10> 3:<11>");
										
		assertTrue(root.getId() == 1);
		assertTrue(getDepth(root, 1) == 0);
		assertTrue(getDepth(root, 2) == 1);
		assertTrue(getDepth(root, 8) == 3);
		assertTrue(getDepth(root, 10) == 4);
		assertTrue(getDepth(root, 11) == 2);


		/*
                   20 
                / / \ \
               2  3  4  5
              / \    |  | \
             6  7    8  9 10
            /   |        / \ \
           11  12       13 14 15
                |
                16
               / | \
              17 18 19
		  */
		root = Node.parseTreeData("20:<2,3,4,5> 2:<6,7> 4:<8> 5:<9,10> 6:<11> 7:<12> 10:<13,14,15> 12:<16> 16:<17,18,19>");
		assertTrue(root.getId() == 20);
		assertTrue(getDepth(root, 20) == 0);
		assertTrue(getDepth(root, 3) == 1);
		assertTrue(getDepth(root, 7) == 2);
		assertTrue(getDepth(root, 8) == 2);
		assertTrue(getDepth(root, 12) == 3);
		assertTrue(getDepth(root, 15) == 3);
		assertTrue(getDepth(root, 18) == 5);
		assertTrue(getDepth(root, 19) == 5);
	}

	public static void assertTrue(boolean expression) {
		if(!expression) {
			Thread.dumpStack();
			System.exit(0);
		}
	}

	public static int getDepth(Node root, int nodeId) {
		// 1) Only fill in your code in this method
		// 2) Do not modify anything else
		// 3) Use of 'new' keyword is not allowed
		// 4) Do not use string concatenation
		// 5) Do not use reflection
		// 6) Assume node ids are always smaller than 1000
		// 7) If a node is not found, a negative value should be returned
		// 8) If your code cannot compile or fails the test case in 'main()', you will NOT receive a response from us
		
		int depth = 0;
		
		if(root.getId() == nodeId)
			return depth;
		
		for(int i = 0; i < root.getChildren().size(); i++){
			depth = 1 + getDepth(root.getChildren().get(i), nodeId);
			if(depth > 0)
				return depth;
		}
		return -1;
	}	
}

class Node {
	private int id;
	private Node parent;
	private List<Node> children;

	public static Node parseTreeData(String data) {
		Node root = null;
		Map<Integer, Node> idToNode = new HashMap<Integer, Node>();

		Pattern pattern = Pattern.compile("(\\d+):<([\\d,]+)>\\s*");
		Matcher matcher = pattern.matcher(data);
		while(matcher.find()) {
			int id = Integer.parseInt(matcher.group(1));
			String childrenIds = matcher.group(2);

			Node parent = idToNode.get(id);
			if(parent == null) {
				root = new Node(id);
				idToNode.put(id, root);
				parent = root;
			}

			for(String childId : childrenIds.split(",")) {
				Node child = new Node(Integer.parseInt(childId), parent);
				parent.children.add(child);
				idToNode.put(child.getId(), child);
			}
		}

		return root;
	}

	public Node(int id) {
		this(id, null);
	}

	public Node(int id, Node parent) {
		this.id = id;
		this.parent = parent;
		this.children = new ArrayList<Node>();
	}

	public int getId() {
	   	return id;
   	}

	public Node getParent() { 
		return parent;
   	}

	public List<Node> getChildren() {
		return Collections.unmodifiableList(children);
	}
}


