package playground;

import java.io.*;
import java.util.*;

/*
Build an expression parser.
The format of an individual statement will be:
“( CMD VAL VAL )”

VAL can be an integer or another statement.

You can assume valid inputs for this entire exercise.
If you need to, clarify what you assume are valid inputs.

The first two commands to implement are ADD and MULT,
which add and multiply the values, respectively.

Example:
parse("( ADD 3 4 )") == 7

parse("( ADD ( ADD 1 2 ) ( ADD 3 4 ) )") = 10

Notes:
======

StringTokenizer
  -- constructor accepts delimiters

Data Structures:
 Stack
    push ( "(")
    push( "add")
    push( 3 )
    push( 7 )
    push( ")" )

Iterative solution vs Recursive solution ?

 */

public class ExpressionParser {

    public static void main(String[] args) {
        System.out.println("result: " + evaluate("( ADD 1 2 )"));
        System.out.println("result: " + evaluate("( MULT ( ADD ( ADD 1 3 ) 2 ) ( MULT 3 4 ) )"));
    }

    private static int evaluate(final String expression) {
        Stack<String> stack = new Stack<String>();
        StringTokenizer tokenizer = new StringTokenizer(expression);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.equals("(")) {
                // Skip this character.
                // Do not push opening paren onto stack.
            }
            else if (token.equals(")")) {
                String top = stack.peek();
                if (isInteger(top)) {
                    int x = Integer.valueOf(stack.pop());
                    int y = Integer.valueOf(stack.pop());
                    String operation = stack.pop();
                    int intermediate = 0;
                    if (operation.equals("ADD")) {
                        intermediate = x + y;
                    } else if (operation.equals("MULT")) {
                        intermediate = x * y;
                    }
                    stack.push("" + Integer.valueOf(intermediate));
                } else {
                    throw new IllegalStateException("oops");
                }
            }
            else if (token.equals("ADD")) {
                stack.push(token);
            }
            else if (token.equals("MULT")) {
                stack.push(token);
            } else {
                // We are assuming that there is no bad input.
                // This token is an integer value
                stack.push(token);
            }
        }
        return Integer.parseInt(stack.pop());

    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

