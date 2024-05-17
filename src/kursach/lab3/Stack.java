package kursach.lab3;

class Node{
    String data;
    Node next;
    public Node(String data){
        this.data = data;
        this.next = null;
    }
}
public class Stack {
    public Node top;
    public int size = 0;

    public Stack() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(String data) {
        Node newNode = new Node(data);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        size++;
    }

    public String pop() {
        String data = null;
        if (!isEmpty()) {
            data = top.data;
            top = top.next;
            size--;
        }

        return data;
    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    public void display(){
        if(top == null){
            System.out.println("Стэк пуст");
            return;
        }

        Node current = top;
        while (current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void deleteStack(){
        top = null;
    }

    public static boolean isOperator(Character token) {
        return token.equals('+') || token.equals('-') || token.equals('*') || token.equals('/');
    }
}
