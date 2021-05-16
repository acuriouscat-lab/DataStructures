package guigu.huffmantree.huffmancode;


import java.util.*;

public class HuffManCode {

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println(bytes.length);

        byte[] huffmanzip = huffmanzip(bytes);
        System.out.println("huffmanzip" + Arrays.toString(huffmanzip)+"长度+ "+ huffmanzip.length);

        /*
        List<Node> nodes = getNodes(bytes);
        System.out.println("ndoes: " + nodes);

        //创建赫夫曼树
        Node node = createHhuffmanTree(nodes);
        preOreder(node);

        //生成heffumantree对应的编码
        Map<Byte, String> codes = getCodes(node);
        System.out.println("code:" + codes);

        //根据编码 压缩得到的压缩后的何福满编码字节数组
        byte[] zip = zip(bytes, huffmanCode);
        System.out.println("zip:" + Arrays.toString(zip));
        */
    }

    public static byte[] huffmanzip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //创建赫夫曼树
        Node node = createHhuffmanTree(nodes);
        //生成heffumantree对应的编码
        Map<Byte, String> codes = getCodes(node);
        //根据编码 压缩得到的压缩后的何福满编码字节数组
        byte[] zip = zip(bytes, huffmanCode);

        return zip;

    }


    /**  将字符串对应的byte[]数组，通过何福满表编码表 返回一个何福满编码 压缩后的byte[]
     * @param bytes        原始的字符串对应的byte[]数组
     * @param huffmanCodes 生成的和编码表
     * @return 返回编码处理过后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes生成的编码表将bytes转化成huffmanCodes对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte by : bytes) {
            stringBuilder.append(huffmanCodes.get(by));
        }

        int len;    //用来统计byte[]的长度     也可以等于 len = (stringBuilder.length()+7)/8
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length()/8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffmanBytes = new byte[len];
        int index = 0;  //用于记录存放到哪个byte[]
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte =  stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanBytes[index] = (byte) Integer.parseInt(strByte);
            index++;
        }
        return huffmanBytes;
    }

    /**
     * @param bytes 接受字节数组
     * @return 返回List数组
     */
    //将字符串中的字母出现的字数构建成Node放入到List中
    public static List<Node> getNodes(byte[] bytes) {
        //创建一个集合接受
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes数组 将其转化为 --> map[key,value]
        Map<Byte, Integer> map = new HashMap<>();
        for (byte by : bytes) {
            Integer count = map.get(by);
            if (count == null) {
                map.put(by, 1);
            } else {
                map.put(by, count + 1);
            }
        }

        //将map中的数据转入到nodes中
        Node node = null;
        Set<Byte> bytes1 = map.keySet();
        for (byte b : bytes1) {
            Integer i = map.get(b);
            node = new Node(b, i);
            nodes.add(node);
        }
        //Collections.guigu.sort(nodes);
        return nodes;
    }

    //通过List创建对应的hefumantree
    public static Node createHhuffmanTree(List<Node> list) {
        if (list == null) {
            return null;
        }
        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            //删除
            list.remove(leftNode);
            list.remove(rightNode);
            //添加
            list.add(parentNode);
        }
        return list.get(0);
    }

    //前序遍历
    public static void preOreder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("无法遍历");
        }
    }

    //生成赫夫曼树对应的赫夫曼编码
    //思路
    //将赫夫曼编码表存放在Map<Byte,String>形式
    //在生成何福满编码表示，需要去拼接路径，定义一个StringBuilder 存储某个叶子节点的路径
    static Map<Byte, String> huffmanCode = new HashMap<Byte, String>();
    static StringBuilder sb = new StringBuilder();

    //调用方便重载getCodes
    public static Map<Byte, String> getCodes(Node node) {
        if (node == null) {
            return null;
        }
        getCodes(node.left, "0", sb);
        getCodes(node.right, "1", sb);
        return huffmanCode;
    }

    /**
     * 将传入到的node节点的所有叶子节点的何福满书编码得到，并放入到集合中
     *
     * @param node          传入结点
     * @param code          路径： 左边为0 右边为1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {
            if (node.data == null) {
                //向左递归
                getCodes(node.left, "0", stringBuilder1);
                //向右递归
                getCodes(node.right, "1", stringBuilder1);
            } else {
                huffmanCode.put(node.data, stringBuilder1.toString());
            }
        }

    }

}

class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }


}
