import java.util.*;

public class LongStack {

    private ArrayList<Long> stack;
    private ArrayList<Long> clone_of_stack;
    private int top_of_stack;


   public static void main (String[] argum) {
      // TODO!!! Your tests here!
      String user_input;
      int result;


//      Scanner scanner = new Scanner(System.in);
//      LongStack stack = new LongStack();

//      System.out.println("Enter a valid post-fix expression one token " +
//              "at a time with a space between each token (e.g. 5 4 + 3 2 1 - + *)");
//      System.out.println("Each token must be an integer or an operator (+,-,*,/)");
//      user_input = scanner.nextLine();
//
//      result = stack.evaluate(user_input);
//      System.out.println();
//      System.out.println("That expression equals " + result);

   }

   LongStack() {
      // TODO!!! Your constructor here!
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

    private static String cleanExpr(String expr){
        //remove all non-operators, non-whitespace, and non digit chars
        return expr.replaceAll("[^\\^\\*\\+\\-\\d/\\s]", "");
    }

   public static long interpret (String pol) {
       String cleanExpr = cleanExpr(pol);
       LinkedList<Long> stack = new LinkedList<Long>();
       for(String token:cleanExpr.split("\\s")){
           Long tokenNum = null;
           try{
               tokenNum = Long.parseLong(token);
           }catch(NumberFormatException e){}
           if(tokenNum != null){
               stack.push(Long.parseLong(token+""));
           }else if(token.equals("*")){
               long op2 = stack.pop();
               long op1 = stack.pop();
               stack.push(op1 * op2);
           }else if(token.equals("/")){
               long op2 = stack.pop();
               long op1 = stack.pop();
               stack.push(op1 / op2);
           }else if(token.equals("-")){
               long op2 = stack.pop();
               long op1 = stack.pop();
               stack.push(op1 - op2);
           }else if(token.equals("+")){
               long op2 = stack.pop();
               long op1 = stack.pop();
               stack.push(op1 + op2);
           }else{//just in case
               System.out.println("Error");
           }
       }
       System.out.println("Final answer: " + stack.pop());
       return stack.pop();
   }

}




