package assignment.fiveweeks;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BinaryTreeTest {


    static Node node;
    static BinaryTree tree = new BinaryTree();

    @Test @DisplayName("1.Node를 생성한다.") @Order(1)
    void makeNode(){
        //given
        int value = 10;

        //when
        node = new Node(value);

        //then
        assertNotNull(node);
        assertEquals(node.getValue(), value,()->"root값은 10 이여야 합니다.");
    }

    @Test @DisplayName("2. 생성한 Node로 이진트리를 구현한다.") @Order(2)
    void addNode() throws Exception {
        //given
        int right_value  = 11;
        int left_value = 9;
        //when
        tree.addNode(node, right_value);
        tree.addNode(node,left_value);
        //then
        assertNotNull(node);
        assertTrue(find(node, left_value));
        assertEquals(node.getRight().getValue(), right_value, ()-> "rootNode의 value값 보다 크므로 좌측 구현");
        assertEquals(node.getLeft().getValue(), left_value, ()-> "rootNode의 value값 보다 작으므로 우측 구현");

    }

    @Test @DisplayName("3. 중복된 값은 들어갈 수 없다.") @Order(2)
    void addNodeException() throws Exception{
        // given
        int  value = 10;
        //when
        Exception exception = assertThrows(Exception.class, () ->
                tree.addNode(node,10));
        //then
        assertEquals("같은 수를 입력할 수 없습니다.",exception.getMessage());
    }


    // 값의 유무를 찾는다.
    boolean find(Node node, int value) {
        return tree.find(node, value);
    }
}