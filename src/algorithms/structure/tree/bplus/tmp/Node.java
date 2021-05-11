package algorithms.structure.tree.bplus.tmp;

import java.util.ArrayList;

public class Node {
	protected boolean isLeafNode;
	protected ArrayList<Integer> keys;

	public boolean isOverflowed() {
		return keys.size() > BPlusTree.n;
	}

	public boolean isUnderflowed() {
		return keys.size() < BPlusTree.D;
	}

}
