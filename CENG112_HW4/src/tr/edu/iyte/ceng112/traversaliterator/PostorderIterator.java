package tr.edu.iyte.ceng112.traversaliterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tr.edu.iyte.ceng112.stack.ArrayStack;
import tr.edu.iyte.ceng112.stack.StackInterface;
import tr.edu.iyte.ceng112.tree.BinaryNode;

public class PostorderIterator<T> implements Iterator<T> {
	private StackInterface<BinaryNode<T>> nodeStack;
	private BinaryNode<T> currentNode;
	
	public PostorderIterator(BinaryNode<T> root) {
		nodeStack = new ArrayStack<>();
		currentNode = root;
		pushFurtherNodes(currentNode);
	}
	
	@Override
	public boolean hasNext() {
		return !nodeStack.isEmpty();
	}

	@Override
	public T next() {
		if(nodeStack.isEmpty())
			throw new NoSuchElementException();
		
		BinaryNode<T> nextNode = null;
		nextNode = nodeStack.pop();
		if(!nodeStack.isEmpty()) {
			if(nextNode == nodeStack.peek().getLeftChild())
				pushFurtherNodes(nodeStack.peek().getRightChild());
		}
		return nextNode.getData();
	}
	private void pushFurtherNodes(BinaryNode<T> root) {
        while (root != null) {
            nodeStack.push(root);
            if (root.hasLeftChild())
            	root = root.getLeftChild();
            else
            	root = root.getRightChild();
        }
	}
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
