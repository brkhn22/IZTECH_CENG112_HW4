package tr.edu.iyte.ceng112.traversaliterator;

import java.util.Iterator;

import tr.edu.iyte.ceng112.stack.ArrayStack;
import tr.edu.iyte.ceng112.stack.StackInterface;
import tr.edu.iyte.ceng112.tree.BinaryNode;

public class PreorderIterator<T> implements Iterator<T> {
	
	private StackInterface<BinaryNode<T>> nodeStack;
	private BinaryNode<T> currentNode;
	
	public PreorderIterator(BinaryNode<T> root) {
		nodeStack = new ArrayStack<>();
		currentNode = root;
	}
	
	@Override
	public boolean hasNext() {
		return !nodeStack.isEmpty() || currentNode != null;
	}

	@Override
	public T next() {
		BinaryNode<T> nextNode = null;
		if(nodeStack.isEmpty() && currentNode != null) {
			nodeStack.push(currentNode);
			currentNode = null;
		}
		if(!nodeStack.isEmpty()) {
			nextNode = nodeStack.pop();
			BinaryNode<T> left = nextNode.getLeftChild();
			BinaryNode<T> right = nextNode.getRightChild();
			if(right != null)
				nodeStack.push(right);
			if(left != null)
				nodeStack.push(left);
		}
		return nextNode.getData();
	}
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
