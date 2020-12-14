package com.tree.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BSTTree {
    public Node root = null;

    public void addNode(int value) {
        Node newNode = new Node(value);

        if(this.root == null){
            this.root = newNode;
            return;
        }

        Node curNode = this.root;

        while(true) {
            //check left node
            if (newNode.value < curNode.value) {
                if(curNode.left == null){
                    curNode.left = newNode;
                    return;
                }

                if(curNode.left != null){
                    curNode = curNode.left;
                    continue;
                }
            }

            //check right node
            if (newNode.value > curNode.value) {
                if(curNode.right == null){
                    curNode.right = newNode;
                    return;
                }

                if(curNode.right != null){
                    curNode = curNode.right;
                    continue;
                }
            }

            System.out.println("ERROR adding node: " + newNode.value);
        }
    }

    public void transverseBF(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        while (queue.size() > 0){
            Node curItem = queue.remove();
            System.out.println(curItem.value);

            if(curItem.left != null){
                queue.add(curItem.left);
            }

            if (curItem.right != null){
                queue.add(curItem.right);
            }
        }
    }
    public void transverseDFIterative(){
        List<Node> stack = new ArrayList<>();
        stack.add(this.root);
        while (stack.size() > 0){
            Node curItem = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);

            if(!curItem.visited) {
                System.out.println("VISITED: " + curItem.value);
                curItem.visited = true;
            }
            if(curItem.right != null && !curItem.right.visited){
                stack.add(curItem.right);
            }
            if(curItem.left != null && !curItem.left.visited){
                stack.add(curItem.left);
            }
        }
    }

    public boolean hasNode(int id){


        return false;
    }

    private void preOrderTraversal(Node n){
        if(n == null) {
            return;
        }
        System.out.println(n.value);
        preOrderTraversal(n.left);
        preOrderTraversal(n.right);
    }

    public void transverseInOrderDF(){
        preOrderTraversal(this.root);
    }
}
