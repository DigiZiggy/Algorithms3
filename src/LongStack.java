import java.util.*;


public class LongStack {

    private LinkedList<Long> stack;


   public static void main (String[] argum) {
       LongStack m = new LongStack();
       m.push (4);
       m.push (5);
       m.op ("#");
       m.tos();
   }

   LongStack() {
       stack = new LinkedList<Long>();
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
       //creating new longstack
       LongStack clone = new LongStack();
       //assigning clone of original stack to this new longstack stack object
       clone.stack = (LinkedList<Long>) stack.clone();
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
           throw new IndexOutOfBoundsException("Your stack has run empty " +
                   "or has too many operators for the amount of numbers " +
                   "or operator entered before any numbers in stack!");
       }
        else
       {
           return stack.remove(stack.size()-1);
       }
   }

   public void op (String s) {
       if (stack.size() >= 2) {
           long op2 = pop();
           long op1 = pop();
           switch (s) {
               case "+":
                   System.out.println("Removing " + op1 + " and " + op2 + " from stack, adding them up, inserting new value into stack");
                   push(op1 + op2);
                   break;
               case "-":
                   System.out.println("Removing " + op1 + " and " + op2 + " from stack, deleting one from another, inserting new value into stack");
                   push(op1 - op2);
                   break;
               case "*":
                   System.out.println("Removing " + op1 + " and " + op2 + " from stack, multiplying them, inserting new value into stack");
                   push(op1 * op2);
                   break;
               case "/":
                   System.out.println("Removing " + op1 + " and " + op2 + " from stack, adding them up, inserting new value into stack");
                   push(op1 / op2);
                   break;
               default:
                   System.out.println("Invalid symbol " + s + " entered!");
                   throw new NumberFormatException("Invalid symbol " + s + " entered!");
           }
       } else {
       throw new IllegalArgumentException("Not enough numbers in stack to do the operation!");
       }

   }

   public long tos() {
       if (stack.isEmpty()) {
           System.out.println("Your stack is empty!");
           throw new EmptyStackException();
       }
        else
       {
           System.out.println("Your top of stack element is " + stack.get(stack.size()-1));
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
       if (pol.length() == 0) {
           throw new RuntimeException("Input string is empty! Not possible to interpret.");
       } else {
           LongStack stack = new LongStack();
           System.out.println("Calculating input: " + pol);
           for (String token : pol.trim().split("\\s+")) {
               switch (token) {
                   case "+":
                       stack.op("+");
                       break;
                   case "-":
                       stack.op("-");
                       break;
                   case "*":
                       stack.op("*");
                       break;
                   case "/":
                       stack.op("/");
                       break;
                   default:
                       try {
                           stack.push(Long.parseLong(token));
                       } catch (NumberFormatException e) {
                           throw new NumberFormatException("Invalid symbol " + token + " entered!");
                       }
                       break;
               }
           }
           if (stack.stack.size() == 1) {
               long answer = stack.pop();
               System.out.println("Answer to the calculation is " + answer);
               return answer;

           } else {
               LongStack overflow = new LongStack();

               for (int num = 0; num < stack.stack.size(); num++) {
                   overflow.push(stack.stack.get(num));
               }
               throw new RuntimeException("Not enough operators for the amount of numbers entered or " +
                       "not enough numbers in order to do the operations entered. Elements "
                       + overflow + " are left over from the input [" + pol +
                       "] hence calculation is impossible!");

           }
       }
   }
}




