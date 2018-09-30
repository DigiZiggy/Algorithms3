import java.util.*;


public class LongStack {

    private ArrayList<Long> stack;


   public static void main (String[] argum) {
      // TODO!!! Your tests here!
   }

   LongStack() {
       stack = new ArrayList<Long>();
       this.stack = stack;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
       //creating new longstack
       LongStack clone = new LongStack();
       //assigning clone of original stack to this new longstack stack object
       clone.stack = (ArrayList<Long>) stack.clone();

       return clone;
   }

   public boolean stEmpty() {
       return ((stack.size()-1) == -1);
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
       LongStack stack = new LongStack();

       for (String token : pol.trim().split("\\s+")) {
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
       if (stack.stack.size() == 1) {
           return stack.pop();
       } else {
           throw new RuntimeException();
       }
   }
}




