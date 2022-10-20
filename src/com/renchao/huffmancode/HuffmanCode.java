package com.renchao.huffmancode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class HuffmanCode {
    private static final HashMap<Byte, String> huffmanCode = new HashMap<>();

    public static void main(String[] args) {
        String in = "d:\\src.bmp";
        String out = "d:\\google22.zip";
        zipFile(in,out);
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        byte[] huffman = huffman(contentBytes);
//
//        System.out.println(Arrays.toString(huffman));
//        System.out.println(decode(huffmanCode,huffman));
//        decode(huffmanCode,huffman);

    }

    public static void zipFile(String in, String out) {
        byte[] file;
        FileInputStream fis = null;
        ObjectOutputStream outs = null;
        try {
            fis = new FileInputStream(in);
            file = new byte[fis.available()];
            fis.read(file);
            byte[] huffman = huffman(file);
            System.out.println("压缩编码完成。。。。。");
            outs = new ObjectOutputStream(new FileOutputStream(out));
            outs.writeObject(huffman);
            outs.writeObject(huffmanCode);
            outs.flush();
            System.out.println("文件保存完成。。。。。");
            outs.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decode(Map<Byte, String> huffmanCodes, byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length - 1; i++) {
            stringBuilder.append(byteToBitString(true, bytes[i]));
        }
        stringBuilder.append(byteToBitString(false, bytes[bytes.length - 1]));
//        return stringBuilder.toString();
        HashMap<String, Byte> sm = new HashMap<>();
        for (Map.Entry<Byte,String> entry : huffmanCode.entrySet()) {
            sm.put(entry.getValue(), entry.getKey());
        }

        StringBuilder str = new StringBuilder();
        Byte b;
        ArrayList<Byte> bs = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); i++) {
            str.append(stringBuilder.substring(i, i + 1));
            if ((b = sm.get(str.toString())) != null) {
                bs.add(b);
                str.delete(0, str.length());
            }
        }
        byte[] bs2 = new byte[bs.size()];
        int i = 0;
        for (Byte by : bs) {
            bs2[i++] = by;
        }

        String s = new String(bs2);
        System.out.println(s);
    }

    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag)
            temp |= 256;
        String str = Integer.toBinaryString(temp);
        if (flag)
            return str.substring(str.length() - 8);
        else
            return str;
    }

    public static byte[] huffman(byte[] bytes) {
        //把字符数组转集合中
        List<Node> list = list(bytes);
        //得到赫夫曼数
        Node root = createHuffmanTree(list);
        //得到字符对应的赫夫曼编码huffmanCode
        getCodes(root);

        //把字符数组转为二进制编码返回
        return zip(bytes);

    }

    public static byte[] zip(byte[] bytes) {
        StringBuilder str = new StringBuilder();
        for (byte b : bytes) {
            str.append(huffmanCode.get(b));
        }
        byte[] bs = new byte[(str.length() + 7) / 8];
        int k = 0;
        int i;
        for (i = 0; i < str.length() - 8; i += 8) {
            bs[k++] = (byte) Integer.parseInt(str.substring(i, i + 8), 2);
        }
        bs[k] = (byte) Integer.parseInt(str.substring(i), 2);
        return bs;
    }

    /**
     * @param node 节点
     * @param code 节点左右路径表示，左表示0，右表示1
     * @param str  路径拼接
     */
    public static void getCodes(Node node, String code, StringBuilder str) {
        if (node == null)
            return;
        StringBuilder strCode = new StringBuilder(str);
        strCode.append(code);
        if (node.data == null) {
            getCodes(node.left, "0", strCode);
            getCodes(node.right, "1", strCode);
        } else {
            huffmanCode.put(node.data, strCode.toString());
        }
    }

    public static void getCodes(Node root) {
        getCodes(root, "", new StringBuilder());
    }

    public static Node createHuffmanTree(List<Node> list) {
        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);

            Node node = new Node(null, leftNode.weight + rightNode.weight);
            node.left = leftNode;
            node.right = rightNode;

            list.remove(leftNode);
            list.remove(rightNode);
            list.add(node);
        }
        return list.get(0);
    }

    //前序遍历
    public static void preOrder(Node node) {
        if (node == null) {
            System.out.println("数是空的。。。");
            return;
        }
        node.preOrder();
    }

    public static List<Node> list(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> bi = new HashMap<>();
        for (Byte b : bytes) {
            bi.merge(b, 1, Integer::sum);
        }
        for (Map.Entry<Byte, Integer> bie : bi.entrySet()) {
            nodes.add(new Node(bie.getKey(), bie.getValue()));
        }
        return nodes;
    }
}
