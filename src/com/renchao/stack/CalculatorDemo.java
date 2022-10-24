package com.renchao.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 计算器
 */
public class CalculatorDemo {
    public static void main(String[] args) {
//        String str = "1 2 3 + 4 * + 5 -";
        String str = "(69*5+7)-6+1+((2+3)*4)-5";

        // 算式字符串转成有序的list
        List<String> list = afterTransfer(str);
        // 计算
        Integer calculator = calculator(list);
        System.out.println(calculator);
    }

    // 计算
    public static Integer calculator(List<String> list) {
        if (list == null) {
            System.out.println("输入错误");
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        int num1;
        int num2;
        for (String s : list) {
            if (s.matches("\\d+")) {
                stack.push(Integer.decode(s));
            } else if (s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 - num1);
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 / num1);
            }
        }
        return stack.pop();
    }

    // 算式转list
    public static List<String> afterTransfer(String str) {
        if (!str.matches("(\\(*\\d+\\)*[+\\-*/]?)+\\d+\\)*")) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        String g;
        List<String> list = new ArrayList<>();

        Matcher matcher = Pattern.compile("\\d+|[+\\-*/()]").matcher(str);
        while (matcher.find()) {
            g = matcher.group(0);
            if (g.matches("\\d+")) {
                list.add(g);
            } else if (g.equals("(") || stack.empty() || stack.peek().equals("(")) {
                stack.push(g);
            } else if (g.equals(")")) {
                String pop;
                while (true) {
                    pop = stack.pop();
                    if (pop.equals("("))
                        break;
                    list.add(pop);
                }
            } else {
                while (true) {
                    list.add(stack.pop());
                    if (stack.empty() || priority(stack.peek().charAt(0)) < priority(g.charAt(0)) || stack.peek().equals("("))
                        break;
                }
                stack.push(g);
            }
        }
        while (true) {
            if (stack.empty())
                break;
            list.add(stack.pop());
        }
        return list;
    }

    public static int priority(char c) {
        if (c == '*' || c == '/')
            return 1;
        if (c == '+' || c == '-')
            return 0;
        return -1;
    }
}
