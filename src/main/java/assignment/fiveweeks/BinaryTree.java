package assignment.fiveweeks;

import java.lang.reflect.Executable;

public class BinaryTree {
    Node root;

    public Node
    addNode(Node node, int value) throws Exception {
        if(find(node,value)){
           throw new Exception("같은 수를 입력할 수 없습니다.");
        }
        if(node == null){ // 노드가 비었을 경우
            return new Node(value);
        }else if ( value < node.getValue()) {
            node.setLeft(addNode(node.left,value));
            return node;
        }else{
            node.setRight(addNode(node.right,value));
            return node;
        }
    }

    public boolean find(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.value) {
            return true;
        }

        // 찾는 값이 현재 노드기준 값보다 작다면 좌측 순회, 크면 우측 순회
        return value < node.value ? find(node.left, value) : find(node.right, value);

    }
}
