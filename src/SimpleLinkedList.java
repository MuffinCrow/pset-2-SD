import java.util.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public SimpleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public SimpleLinkedList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            add(i, list.get(i));
        }

        size = list.size();
    }

    public void add(int index, String s) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == 0) {
            addFirst(s);
        } else if (index == size) {
            addLast(s);
        } else {
            Node current = getNode(index);
            Node newNode = new Node(current.prev, s, current);

            size++;
            current.prev.next = newNode;
            current.prev = newNode;
        }
    }

    public void addFirst(String s) {
        Node current = head;
        Node newNode = new Node(null, s, current);

        head = newNode;
        if (current == null) {
            tail = newNode;
        } else {
            current.prev = newNode;
        }

        size++;
    }

    public void addLast(String s) {
        Node current = tail;
        Node newNode = new Node(current, s, null);

        tail = newNode;
        if (current == null) {
            head = newNode;
        } else {
            current.next = newNode;
        }

        size++;
    }

    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    public boolean contains(String s) {
        Node current = head;

        while (current != null) {
            if (current.data == s) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public String get(int index) {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == 0) {
            return head.data;
        } else if (index == size) {
            return tail.data;
        } else {
            Node current = getNode(index);
            return current.data;
        }
    }

    public String getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return head.data;
    }

    public String getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return tail.data;
    }

    public int indexOf(String s) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (current.data == s) {
                return count;
            }

            count++;
            current = current.next;
        }

        return -1;
    }

    public String remove(int index) {
        Node current;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == 0) {
            current = head;
            current.next.prev = null;
            head = current.next;
            return current.data;
        } else if (index == size) {
            current = tail;
            current.prev.next = null;
            tail = current.prev;
            return current.data;
        }
        current = getNode(index);
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return current.data;
    }

    public boolean remove(String s) {
        Node current = head;

        while (current != null) {
            if (current.data == s) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public String removeFirst() {
        Node current = head;
        String temp = current.data;
        current = current.next;
        current.prev = null;
        head = current;
        size--;
        return temp;
    }

    public String removeLast() {
        Node current = tail;
        String temp = current.data;
        current = current.prev;
        current.next = null;
        tail = current;
        size--;
        return temp;
    }

    public String set(int index, String s) {
        Node current = getNode(index);
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        String temp = current.data;
        current.data = s;
        if (index == 0) {
            current.next.prev = current;
        } else if (index == size) {
            current.prev.next = current;
        } else {
            current.next.prev = current;
            current.prev.next = current;
        }
        return temp;
    }

    public int size() {
        return size;
    }

    public String toString() {
        Node current = head;
        String output = "[";
        for(int i = 0; i < size; i++) {
            output = output + getNode(i).data;
            current = current.next;
            if (i != size-1) {
                output = output + ", ";
            }
        }
        output = output + "]";
        return output;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        int count = 0;
        Node current = head;

        while (current != null) {
            if (count++ == index) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    private static class Node {

        Node prev;
        String data;
        Node next;

        public Node(Node prev, String data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
}
