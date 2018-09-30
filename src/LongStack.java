import java.util.*;

public class LongStack {

    private ArrayList<Long> stack;
    private ArrayList<Long> clone_of_stack;
    private int top_of_stack;


   public static void main (String[] argum) {
      // TODO!!! Your tests here!
   }

   LongStack() {
       stack = new ArrayList<Long>();
       this.stack = stack;
       top_of_stack = -1;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
       if (this.stack == null) {
           stack = new ArrayList<Long>();
           return null;
       }
       LongStack.super.clone();
 //      LongStack result = (LongStack) super.clone();

       return LongStack.super.clone();
   }

   public boolean stEmpty() {
       return (top_of_stack == -1);
   }

   public void push (long a) {
       stack.add(a);
   }

   public long pop() {
       if (stack.size() == 0)
       {
           throw new EmptyStackException();
       }
        else
       {
           return stack.remove(stack.size()-1);
       }
   } // pop

   public void op (String s) {
       long op2 = pop();
       long op1 = pop();
       if (s.equals ("+")) push (op1 + op2);
       if (s.equals ("-")) push (op1 - op2);
       if (s.equals ("*")) push (op1 * op2);
       if (s.equals ("/")) push (op1 / op2);
   }

   public long tos() {
       if (stack.isEmpty()) {
           throw new EmptyStackException();
       }
        else
       {
           return stack.get(stack.size()-1);
       }
   }

   @Override
   public boolean equals (Object o) {
       return o instanceof LongStack && stack.equals(((LongStack)o).stack);
   }


   @Override
   public String toString() {
       return stack.toString();
   }


   public static long interpret (String pol) {
       Stack<Long> stack = new Stack<>();

       for (String token : pol.split("\\s+")) {
           switch (token) {
               case "+":
                   stack.push(stack.pop() + stack.pop());
                   break;
               case "-":
                   stack.push(-stack.pop() + stack.pop());
                   break;
               case "*":
                   stack.push(stack.pop() * stack.pop());
                   break;
               case "/":
                   long divisor = stack.pop();
                   stack.push(stack.pop() / divisor);
                   break;
               default:
                   stack.push(Long.parseLong(token));
                   break;
           }
       }
       if (stack.size() == 1) {
           return stack.pop();
       } else {
           throw new RuntimeException();
       }
   }
}




