//
// Árvore de Busca Binária - Exemplo de implementação em Java
// Copyright (C) 2024 André Kishimoto
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.
//

package ed2;

public class BST extends BinaryTree {

	public BST() {
		super();
	}

	public BST(BTNode root) {
		super(root);
	}

	public BTNode search(String data) {
		return search(data, false);
	}

	public BTNode searchIgnoreCase(String data) {
		return search(data, true);
	}

	private BTNode search(String data, boolean ignoreCase) {
		return search(root, data, ignoreCase);
	}
	
	private BTNode search(BTNode node, String data, boolean ignoreCase) {
		if (node == null) {
			return null;
		}
		
		int diff = diffCompare(data, node.getData(), ignoreCase);

		if (diff < 0) {
			return search(node.getLeft(), data, ignoreCase);
		} else if (diff > 0) {
			return search(node.getRight(), data, ignoreCase);
		} else {
			return node;
		}
	}
	
	public void insert(String data) {
		insert(data, false);
	}
	
	public void insertIgnoreCase(String data) {
		insert(data, true);
	}
	
	private void insert(String data, boolean ignoreCase) {
		root = insert(root, null, data, ignoreCase); 
	}
	
	private BTNode insert(BTNode node, BTNode parent, String data, boolean ignoreCase) {
		if (node == null) {
			return new BTNode(data, parent);
		}

		int diff = diffCompare(data, node.getData(), ignoreCase);
		
		if (diff < 0) {
			node.setLeft(insert(node.getLeft(), node, data, ignoreCase));
		} else if (diff > 0) {
			node.setRight(insert(node.getRight(), node, data, ignoreCase));
		} else {
			// Nessa implementação, não é permitida a inserção de duplicatas na BST.
			// Portanto, não fazemos nada.
			throw new RuntimeException("Essa BST não pode ter duplicatas!");
		}
		
		return node;
	}
	
	public void remove(String data) {
		remove(data, false);
	}
	
	public void removeIgnoreCase(String data) {
		remove(data, true);
	}
	
	private void remove(String data, boolean ignoreCase) {
		root = remove(root, data, ignoreCase);
	}
	
	private BTNode remove(BTNode node, String data, boolean ignoreCase) {
		if (node == null) {
			//return null;
			throw new RuntimeException("Nó com chave " + data + " não existe na BST!");
		}
		
		int diff = diffCompare(data, node.getData(), ignoreCase);
				
		if (diff < 0) {
			node.setLeft(remove(node.getLeft(), data, ignoreCase));
		} else if (diff > 0) {
			node.setRight(remove(node.getRight(), data, ignoreCase));
		} else {
			node = removeNode(node, ignoreCase);
		}
		
		return node;		
	}
	
	private BTNode removeNode(BTNode node, boolean ignoreCase) {
		if (node.isLeaf()) {
			return null;
		}
		
		if (!node.hasLeftChild()) {
			return node.getRight();
		} else if (!node.hasRightChild()) {
			return node.getLeft();
		} else {
			BTNode predecessor = predecessor(node.getData(), ignoreCase);
			node.setData(predecessor.getData());
			node.setLeft(remove(node.getLeft(), predecessor.getData(), ignoreCase));
		}
		
		return node;		
	}
	
	public BTNode findMin() {
		return findMin(root);
	}
	
	private BTNode findMin(BTNode node) {
		if (node == null) {
			return null;
		}

		while (node.hasLeftChild()) {
			node = node.getLeft();
		}
		return node;
	}
	
	public BTNode findMax() {
		return findMax(root);
	}
	
	private BTNode findMax(BTNode node) {
		if (node == null) {
			return null;
		}

		while (node.hasRightChild()) {
			node = node.getRight();
		}
		return node;
	}
	
	public BTNode findPredecessor(String data) {
		return predecessor(data, false);
	}
	
	public BTNode findPredecessorIgnoreCase(String data) {
		return predecessor(data, true);
	}

	private BTNode predecessor(String data, boolean ignoreCase) {
		BTNode node = search(data, ignoreCase);
		return predecessor(node);
	}
	
	private BTNode predecessor(BTNode node) {
		if (node == null) {
			return null;
		}
		
		if (node.hasLeftChild()) {
			return findMax(node.getLeft());
		} else {
			BTNode current = node;
			BTNode parent = node.getParent();

			while (parent != null && current == parent.getLeft()) {
				current = parent;
				parent = current.getParent();
			}
			
			return parent;
		}
	}
	
	public BTNode findSuccessor(String data) {
		return successor(data, false);
	}
	
	public BTNode findSuccessorIgnoreCase(String data) {
		return successor(data, true);
	}

	private BTNode successor(String data, boolean ignoreCase) {
		BTNode node = search(data, ignoreCase);
		return successor(node);		
	}
	
	private BTNode successor(BTNode node) {
		if (node == null) {
			return null;
		}

		if (node.hasRightChild()) {
			return findMin(node.getRight());
		} else {
			BTNode current = node;
			BTNode parent = node.getParent();

			while (parent != null && current == parent.getRight()) {
				current = parent;
				parent = current.getParent();
			}
			
			return parent;
		}
	}
	
	public void clear() {
		root = clear(root);
	}
	
	private BTNode clear(BTNode node) {
		if (node == null) {
			return null;
		}

		// Pós-ordem = percurso LRN.
		node.setLeft(clear(node.getLeft()));
		node.setRight(clear(node.getRight()));
		node.setParent(null);

		return null;
	}

	private int diffCompare(String s1, String s2, boolean ignoreCase) {
		return ignoreCase ? s1.compareToIgnoreCase(s2) : s1.compareTo(s2);
	}
	
	@Override
	public String toString() {
		return "BST - isEmpty(): " + isEmpty()
				+ ", getDegree(): " + getDegree()
				+ ", getHeight(): " + getHeight()
				+ ", root => { " + root + " }";				
	}

}
