import java.util.*;
public class Main {
    public static void main(String[] args) {
        Node head = new Node(-4, new Node(9, new Node(0, new Node(1, new Node(-2)))));
        System.out.println(head);
        mystery2(head);
        System.out.println(head);
    }

    public static void mystery2(Node list) {
        Node temp = list;
        while (temp != null) {
            if (temp.data < 0) {
                temp.data = temp.data * -1;
            } else if (list.data > 0) {
                temp.data = temp.data + 1;
            } else {
                temp.next = null;
            }
            temp = temp.next;
        }
        System.out.println(list);
    }
}

class Node {
    public int data;
    public Node next;

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Node(int data) {
        this(data, null);
    }

    @Override
    public String toString() {
        String sol = "<" + data;
        Node temp = next;
        while (temp != null) {
            sol += ", " + temp.data;
            temp = temp.next;
        }
        return sol + ">";
    }
}