package assignment.stack;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StackTest {

    static final int size = 12;
    static Stack stack;

    @BeforeAll // 클래스 단위 적용
    static void setUp() {
        // given
        stack = new Stack(size);
    }

    @DisplayName("1. isEmpty 스택 비었는지 확인")
    @Test  @Order(1)
    void isEmpty() {
        //then
        assertTrue(stack.isEmpty(), ()-> "아무 값도 넣지 않았기 때문에 비어있어야 한다.");
        assertEquals(size, stack.StackSize, () -> "스택 크기는 12 여야만 한다.");

    }

    @DisplayName("2. push pop 값 넣고 빼기 ")
    @Test @Order(2)
    void push_pop() {
        int firstElement = 13; // givne
        int lastElement = 15;
        stack.push(firstElement); // when
        stack.push(14);
        stack.push(15);
        assertEquals(2,stack.top, ()-> "값을 저장할때마다 top값이 증가하여야 한다." ); // then
        assertEquals(lastElement,stack.pop(), ()-> "스택의 TOP값은 15이어야 한다." ); // then
    }

    @DisplayName("3. top 값 빼지않고 출력만 하기 ")
    @Test @Order(3)
    void peek() {
        stack.peek();
    }
    @DisplayName("4. Stack 출력 ")
    @Test @Order(4)
    void print_stack() {
        stack.printStack();
    }
}