/*
Problem:  Write a program that keeps and manipulates a linked list of
	    String data. The data will be provided by the user one item at a time.
      The user should be able to do the following operations:
                     -add "String"
                                adds an item to your list (maintaining alphabetical order)
                     -remove "String"
                                if the item exists removes the first instance of it
                     -show
                                should display all items in the linked list
                     -clear
                               should clear the list
	Input:  commands listed above
	Output:  the results to the screen of each menu
	    choice, and error messages where appropriate.
*/

import java.util.List;

public class LinkedList{

  //instance varialbes go here (think about what you need to keep track of!)
  ListNode head;
  int length = 0;
  //constructors go here
  public LinkedList(){
    head = null; 
  }

  //precondition: the list has been initialized
  //postcondition: the ListNode containing the appropriate value has been added and returned
  public ListNode addAValue(String line)
  {
    ListNode tracker = head;
    if (head== null){
        ListNode node = new ListNode(line, null);
        head = node;
        length++;
        return node;
      }
    for (int i = 0; i<length; i++){
      ListNode tracker2 = null;
      if (tracker.getValue().compareToIgnoreCase(line)<=0){
        tracker2 = tracker.getNext();
      }
      else{
        ListNode node = new ListNode(line, tracker);
        head = node;
        length++;
        return node;
      }
      
      if (tracker2 == null && tracker.getValue().compareToIgnoreCase(line)<= 0){
        ListNode node = new ListNode(line, tracker2);
        tracker.setNext(node);
        length++;
        return node;
      }
      if( tracker2 != null && i == 0 && tracker.getValue().compareToIgnoreCase(line) > 0){
        ListNode node = new ListNode(line, tracker);
        head = node;
        length++;
        return node;
      }
      else if (tracker2 != null && tracker.getValue().compareToIgnoreCase(line)<= 0 && (tracker2.getValue().compareToIgnoreCase(line) > 0 || tracker2==null)){
        ListNode node = new ListNode(line, tracker2);
        tracker.setNext(node);
        length++;
        return node;
      }
      tracker = tracker.getNext();
    }
    return null;
  }

  //precondition: the list has been initialized
  //postcondition: the ListNode containing the appropriate value has been deleted and returned.
  //if the value is not in the list returns null
  public ListNode deleteAValue(String line)
  {
    ListNode tracker = head;
    for (int i = 0; i<length; i++){
      ListNode next = null;
      if (tracker.getNext()!=null){
        if (tracker.getValue().equals(line)){
          head = tracker.getNext();
          length--;
          return tracker;
        }
        next = tracker.getNext();
      }
      else{
        return null;
      }
      if(next.getValue().equals(line)){
        
        tracker.setNext(next.getNext());
        length--;
        return next;
      }
      tracker = next;
    }
    return null;
  }

  //precondition: the list has been initialized
  //postconditions: returns a string containing all values appended together with spaces between.
  public String showValues()
  {
    ListNode node = head;
    String str = "";
    for (int i = 0; i<length;i++){
      if (node != null){
      str += node.getValue() + " ";
      node = node.getNext();
      }
    }
    return str;
  }

  //precondition: the list has been initialized
  //postconditions: clears the list.
  public void clear()
  {
    head = null;
    length = 0;
  }
  //precondition: the list has been initalized and has elements
  //postconditions: reverses the list order
  public void reverse()
  {
    ListNode start = head;
    ListNode mid = null;
    ListNode end = null;
    
    if (start!=null){
      mid = start.getNext();
      if (mid!=null){
        end = mid.getNext();
      }
    }
    if (end == null){
      mid.setNext(start);
      head = mid;
      start.setNext(null);
    }
    for (int i = 0; i<length; i++){
      mid.setNext(start);
      start = mid;
      mid = end;
      if(mid != null){
        end = mid.getNext();
      }
      else{
        head = start;
        i=length;
      }
      
    }
     
  }

//precondition: the list has been initialized and has elements
  //postconditions: reverses groups of n items in the list
  public void nReverse(int n) {
    ListNode start = head;
    ListNode tail = null;
    boolean first = true;

    while (start != null){
      ListNode head = start;
      ListNode prev = null;
      int num = 0;

      while(start != null && num < n){
        ListNode temp = start.getNext();
        start.setNext(prev);
        prev = start;
        start = temp;
        num++;
      }
      if (first){
        head = prev;
        first = false;
      }
      if (num != n){
        ListNode tracker = prev;
        ListNode tracker2 = null;
        while(tracker != null){
          ListNode temp = tracker.getNext();
          tracker.setNext(tracker2);
          tracker2 = tracker;
          tracker = temp;
        }
        tail.setNext(tracker2);
        return;
      }
      if (tail != null){
        tail.setNext(prev);
      }
      tail = head;
    }
  }
}
