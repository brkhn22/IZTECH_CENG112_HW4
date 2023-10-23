package tr.edu.iyte.ceng112.traversaliterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tr.edu.iyte.ceng112.queue.ArrayQueue;
import tr.edu.iyte.ceng112.queue.EmptyQueueException;
import tr.edu.iyte.ceng112.queue.QueueInterface;
import tr.edu.iyte.ceng112.tree.BinaryNode;

public class LevelOrderIterator<T> implements Iterator<T> {
	
	private QueueInterface<BinaryNode<T>> nodeQueue;
	private QueueInterface<BinaryNode<T>> nodeBeforeLevelQueue;
	private BinaryNode<T> rootNode;
	private int currentLevel;
	private int maxHeight;
	
	public LevelOrderIterator(BinaryNode<T> root) {
		nodeQueue = new ArrayQueue<>();
		nodeBeforeLevelQueue = new ArrayQueue<>();
		rootNode = root;
		if (rootNode != null)
			nodeQueue.enqueue(root);
		maxHeight = rootNode.getHeight();
		currentLevel = 1;
	}
	
	@Override
	public boolean hasNext() {
		return !nodeQueue.isEmpty() || !nodeBeforeLevelQueue.isEmpty();
//		return !nodeQueue.isEmpty() || currentLevel <= rootNode.getHeight();
	}

	@Override
	public T next() {
		BinaryNode<T> nextNode = null;
		if(nodeQueue.isEmpty())
			addNodeForLevel();
		if(!nodeQueue.isEmpty()) {
			try {
				nextNode = nodeQueue.dequeue();
				if(currentLevel < maxHeight)
					nodeBeforeLevelQueue.enqueue(nextNode);
			} catch (EmptyQueueException e) {
				e.printStackTrace();
			}
		}else
			throw new NoSuchElementException();
		
		
	/*	Recursive Implementation that is not efficient.
	 
	  if(nodeQueue.isEmpty() && currentLevel <= rootNode.getHeight()) {
			int level = 1;
			addNodeForLevel(level, rootNode);
			currentLevel++;
		}
		if(!nodeQueue.isEmpty()) {
			try {
				nextNode = nodeQueue.dequeue();
			} catch (EmptyQueueException e) {
				e.printStackTrace();
			}
		} 
		
		Recursive Implementation that is not efficient.	*/
		return nextNode.getData();
	}
	
	private void addNodeForLevel() {
		while(!nodeBeforeLevelQueue.isEmpty()) {
			BinaryNode<T> frontNode;
			try {
				frontNode = nodeBeforeLevelQueue.dequeue();
				if(frontNode.hasLeftChild())
					nodeQueue.enqueue(frontNode.getLeftChild());
				if(frontNode.hasRightChild())
					nodeQueue.enqueue(frontNode.getRightChild());
			} catch (EmptyQueueException e) {
				e.printStackTrace();
			}
			
		}
		currentLevel++;
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
/*	
	private void addNodeForLevel(int level, BinaryNode<T> rootNode){
		if(rootNode != null) {
			if(level < currentLevel) {
				addNodeForLevel(level+1,rootNode.getLeftChild());
				addNodeForLevel(level+1,rootNode.getRightChild());
			}else if (level == currentLevel) {
				nodeQueue.enqueue(rootNode);
			}
		}	
	}
	*/
}