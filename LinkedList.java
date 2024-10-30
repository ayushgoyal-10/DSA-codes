public class LinkedList{
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data= data;
            this.next= null;
        }
    }
    public static Node head;

    public static void addFirst(int data){
        Node newNode = new Node(data);
        if(head==null){
            head= newNode;
            return;
        }   
        newNode.next= head;
        head= newNode;
    }
    public static void addLast(int data){
        Node newNode = new Node (data);
        if(head==null){
            head= newNode;
            return;
        }
        Node temp= head;
        while(temp.next!=null){
            temp= temp.next;
        }
        temp.next= newNode;
    }
    public static void display(){
        if(head==null){
            System.out.println("The list is empty");
            return;
        }
        Node temp= head;
        while (temp!=null) {
            System.out.print(temp.data+" ");
            temp= temp.next;
        }
    }
    public static void reverse(){
        if(head==null){
            System.out.println("The list is empty");
            return;
        }
        Node prev= null;
        Node curr= head;
        Node next;
        while(curr!=null){
            next= curr.next;
            curr.next=prev;
            prev= curr;
            curr= next;
        }
        head= prev;
    }
    public static boolean isPalindrome(){
        if(head==null){
            System.out.println("The list is empty");
            return true;
        }
        Node mid= getMid(head);

        Node curr= mid;
        Node prev= null;
        Node next;
        while(curr!= null){
            next= curr.next;
            curr.next= prev;
            prev= curr;
            curr= next;
        }
        
        Node right= prev;
        Node left= head;
        while(right!=null){
            if(left.data!=right.data){
                return false;
            }
            left= left.next;
            right= right.next;
        }
        return true;
    }
    public static Node getMid(Node head){
        Node slow= head;
        Node fast= head;
        while(fast!=null && fast.next!=null){
            slow= slow.next;
            fast= fast.next.next;
        }
        return slow;
    }
    public static void main(String[] args) {
        LinkedList list= new LinkedList();
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst (30);
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        // list.display();
        // list.reverse();
        list.display();
        System.out.println(list.isPalindrome());       
    }
}