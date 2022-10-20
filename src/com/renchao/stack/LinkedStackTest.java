package com.renchao.stack;

import com.renchao.Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkedStackTest {
    public static void main(String[] args) {
        String str = "(69*5+7)-6+1+((2+3)*4)-5";
        calculationFormula(str);

//        boolean b = true;
//        while (b) {
//            System.out.print("1 添加\t2 取出\t3 显示\t0 退出\n请选择:");
//            int key = Utility.readInt();
//            switch (key) {
//                case 1:
//                    System.out.print("请输入想要添加的数字:");
//                    int ints = Utility.readInt();
//                    ls.push(ints);
//                    break;
//                case 2:
//                    System.out.println(ls.pop());
//                    break;
//                case 3:
//                    ls.show();
//                    break;
//                case 0:
//                    b = false;
//                    break;
//            }
//        }
    }

    public static void calculationFormula(String str) {
        if (!str.matches("(\\(*\\d+\\)*[+\\-*/]?)+\\d+\\)*")) {
            System.out.println("算式输入错误。");
            return;
        }
        LinkedStack<Integer> ls1 = new LinkedStack<>();
        LinkedStack<Character> ls2 = new LinkedStack<>();
        String g1;
        String g2;
        String g3;
        String g4;
        char ope;
        Integer num1;
        Integer num2;
//        String str = "5*(3+4)";
        Matcher matcher = Pattern.compile("(\\(?)(\\d+)(\\)?)([+\\-*/])?").matcher(str);
        while (matcher.find()) {
            //利用正则表达式，取出1:左括号 2:数值 3:右括号 4:运算符
            g1 = matcher.group(1);
            g2 = matcher.group(2);
            g3 = matcher.group(3);
            g4 = matcher.group(4);

            //如果有左括号，直接入符号栈
            if (g1.equals("("))
                ls2.push(g1.charAt(0));

            //数值直接入栈
            ls1.push(Integer.decode(g2));

            //如果有右括号，把括号里的算式计算完
            if (g3.equals(")"))
                calculate(ls1, ls2);

            //如果g4为null，说明算式结束
            if (g4 == null)
                break;
            //把字符转为char,判断符号栈如果为空，就直接入栈
            ope = g4.charAt(0);
            if (ls2.isNull())
                ls2.push(ope);

                //判断前一个运算符优先级如果大于当前运算符，则先计算前面，且排除左括号
            else if (!(ls2.peek() == '(') && priority(ls2.peek()) >= priority(ope)) {
                num1 = ls1.pop();
                num2 = ls1.pop();
                char cc = ls2.pop();
                ls1.push(cal(num1, num2, cc));
                ls2.push(ope);
            } else //否则运算符直接入栈
                ls2.push(ope);
        }
        //数值和运算符全部入栈后，再逐个进行计算
        calculate(ls1, ls2);
        System.out.println(ls1.pop());
    }

    public static void calculate(LinkedStack<Integer> ls1, LinkedStack<Character> ls2) {
        Integer num1;
        Integer num2;
        Character ope;
        while (true) {
            ope = ls2.pop();
            if (ope == null || ope == '(')
                return;
            num1 = ls1.pop();
            num2 = ls1.pop();

            ls1.push(cal(num1, num2, ope));
        }
    }

    public static int priority(char c) {
//        if (c == '(' || c == ')')
//            return 2;
        if (c == '*' || c == '/')
            return 1;
        if (c == '+' || c == '-')
            return 0;
        return -1;
    }

    public static int cal(int num1, int num2, char c) {
        int res = 0;
        switch (c) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }
}

class LinkedStack<T> {
    public StackNode<T> head = new StackNode<>(null);

    public boolean isNull() {
        return head.next == null;
    }

    public T peek() {
        return head.next.value;
    }

    public void push(T value) {
        StackNode<T> sn = new StackNode<>(value);
        sn.next = head.next;
        head.next = sn;
    }

    public T pop() {
        if (head.next == null) {
//            System.out.println("栈空。。");
            return null;
        }
        T value = head.next.value;
        head.next = head.next.next;
        return value;
    }

    public void show() {
        StackNode<T> temp = head;
        while (true) {
            if (temp.next == null) {
                return;
            }
            temp = temp.next;
            System.out.println(temp);
        }
    }
}

class StackNode<T> {
    public T value;
    public StackNode<T> next;

    @Override
    public String toString() {
        return "" + value;
    }

    public StackNode(T value) {
        this.value = value;
    }
}