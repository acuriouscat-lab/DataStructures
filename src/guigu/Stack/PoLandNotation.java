package guigu.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PoLandNotation {
    public static void main(String[] args) {

        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)×4)-5 => 转成  1 2 3 + 4 × + 5 –
        //2. 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)×4)-5" =》 中缀的表达式对应的List
        //   即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
        //   即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
        String expression = "10+((2+3)*4)-5";//注意表达式
        ArrayList<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);

        List<String> strings = parseSuffixExpressionList(infixExpressionList);
        System.out.println(strings);

        int res = calculator(strings);
        System.out.println(res);

//        //为了方便逆波兰表达式的数字和符号使用空格隔开
//        String suffixExpression = "30 4 + 5 * 6 - ";
//
//        //1. 先将"3 4 + 5 * 6 - " => 放到ArrayList中
//        ArrayList<String> expressionList = getExpressionList(suffixExpression);
//
//        System.out.println("exprissionList:" + expressionList);
//        //2. 将ArrayList传递给一个方法，遍历Arraylist配合栈完成计算
//        int res = calculator(expressionList);
//
//        System.out.println(res);

    }

    //将表达式放到ArrayList
    //1. 先将"3 4 + 5 * 6 - " => 放到ArrayList中
    public static ArrayList<String> getExpressionList(String expression) {
        String[] s = expression.split(" ");
        ArrayList<String> list = new ArrayList<String>();
        for (String ss : s) {
            list.add(ss);
        }
        return list;
    }
    //2. 将ArrayList传递给一个方法，遍历Arraylist配合栈完成计算
    public static int calculator(List<String> list) {
        Stack<String> stack = new Stack<>();

        for (String item : list) {
            if (item.matches("\\d+")) {
                stack.push(item);
            }else{
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num2 - num1;
                } else if (item.equals("*")) {
                    res = num2 * num1;
                } else if (item.equals("/")) {
                    res = num2 / num1;
                }else {
                    throw new RuntimeException("运算符输入有错误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式转成后缀表达
    public static ArrayList<String> toInfixExpressionList(String string) {
        ArrayList<String> arrayList = new ArrayList<>();
        char c;
        String s;
        int i = 0;
        do {
            if ((c = string.charAt(i)) < 48 || (c = string.charAt(i)) > 57) {
                arrayList.add(c + "");
                i++;
            }else {
                s = "";
                while (i < string.length() && (c = string.charAt(i)) >= 48 && (c = string.charAt(i)) <= 57) {
                    s += c;
                    i++;
                }
                arrayList.add(s);
            }
        } while (i<string.length());

        return arrayList;
    }

    //3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
    //   即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
    public static List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> stack = new Stack<String>(); //符号栈
        List<String> ls = new ArrayList<>(); //存放数据
        //便利ls
        for (String ss : list) {
            if (ss.matches("\\d+")) {
                ls.add(ss);
            }else if(ss.equals("(")){
                stack.push(ss);
            } else if (ss.equals(")")) {
                while (!stack.peek().equals("(")) {
                    ls.add(stack.pop());
                }
                stack.pop();
            }else{
                while (stack.size() != 0 && Operation.getValue(ss) <= Operation.getValue(stack.peek())) {
                    ls.add(stack.pop());
                }
                stack.push(ss);
            }
        }

        while (stack.size() > 0) {
            ls.add(stack.pop());
        }
        return ls;
    }

}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 1;
    private static int DVI = 1;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DVI;
                break;
            default:
                System.out.println("输入有误");
                break;
        }
        return result;
    }
        
}



