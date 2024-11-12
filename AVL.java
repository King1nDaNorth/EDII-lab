//
// Árvore AVL - Exemplo de implementação em Java
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

public class AVL extends BST {

	public AVL() {
		super();
	}

	public AVL(Node root) {
		super(root);
	}

	@Override
	protected Node insert(Node node, Node parent, int data) {
		return balance(super.insert(node, parent, data));
	}
	
	@Override
	protected Node remove(Node node, int data) {
		return balance(super.remove(node, data));
	}

	private Node balance(Node node) {
		if (node == null)
			return null;
		
		int nodeBF = node.getBalanceFactor();
		if (nodeBF < -1) {
			if (node.getLeft().getBalanceFactor() <= 0) {
				node = rotateRight(node);
			} else {
				node = rotateLeftRight(node);
			}
		} else if (nodeBF > 1) {
			if (node.getRight().getBalanceFactor() >= 0) {
				node = rotateLeft(node);
			} else {
				node = rotateRightLeft(node);
			}
		}
		
		return node;
	}

	private void updateParentChild(Node parent, final Node child, Node newChild) {
		if (parent != null) {
			if (parent.getLeft() == child) {
				parent.setLeft(newChild);
			} else {
				parent.setRight(newChild);
			}
		} else {
			root = newChild;
			newChild.setParent(null);
		}
	}
	
	// Rotação LL.
	private Node rotateLeft(Node node) {
		if (node == null) {
			return null;
		}
		
		// O nó atual deve ter um filho direito, que será a nova raiz desta subárvore.
		Node newRoot = node.getRight();
		if (newRoot == null) {
			return null;
		}
		
		// Troca as conexões do nó pai (newRoot vira filho de parent, no lugar de node).
		Node parent = node.getParent();
		updateParentChild(parent, node, newRoot);
		
		// newRoot é a nova raiz desta subárvore, então seu filho esquerdo se torna o
		// filho direito de node (que deixa de ser raiz desta subárvore).
		Node left = newRoot.getLeft();
		node.setRight(left);

		// node agora vira filho esquerdo de newRoot.
		newRoot.setLeft(node);
		
		return newRoot;
	}
	
	// Rotação RR.
	private Node rotateRight(Node node) {
		if (node == null) {
			return null;
		}
		
		// O nó atual deve ter um filho esquerdo, que será a nova raiz desta subárvore.
		Node newRoot = node.getLeft();
		if (newRoot == null) {
			return null;
		}
		
		// Troca as conexões do nó pai (newRoot vira filho de parent, no lugar de node).
		Node parent = node.getParent();
		updateParentChild(parent, node, newRoot);
		
		// newRoot é a nova raiz desta subárvore, então seu filho direito se torna o
		// filho esquerdo de node (que deixa de ser raiz desta subárvore).
		Node right = newRoot.getRight();
		node.setLeft(right);
		
		// node agora vira filho direito de newRoot.
		newRoot.setRight(node);
		
		return newRoot;
	}
	
	// Rotação LR.
	private Node rotateLeftRight(Node node) {
		node.setLeft(rotateLeft(node.getLeft()));
		return rotateRight(node);
	}
	
	// Rotação RL.
	private Node rotateRightLeft(Node node) {
		node.setRight(rotateRight(node.getRight()));
		return rotateLeft(node);
	}
	
	@Override
	public String toString() {
		return "AVL - isEmpty(): " + isEmpty()
				+ ", getDegree(): " + getDegree()
				+ ", getHeight(): " + getHeight()
				+ ", root => { " + root + " }";				
	}

}
