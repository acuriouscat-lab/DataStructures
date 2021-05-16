package guigu.sparsearray;

import java.io.*;

/**
 * 稀疏数组
 *      数组中有很多没有意义的数据 -> 稀疏数组表示
 *
 *      稀疏数组的第一行  总行数   总列数     有意义的数据数
 *              其他行     行数      列数      值
 *
 *      掌握：
 *          二维数组 ---> 稀疏数组
 *          稀疏数组 ---> 二维数组
 */

public class SparseArrays {
    public static void main(String[] args) throws IOException {
        //定义二维数组
        int arr[][]=new int[11][11];
        arr[1][2]=1;
        arr[4][3]=1;
        arr[2][3]=2;
        arr[3][5]=2;
        //遍历二维数组
        System.out.println("原先的数组");
        for(int[] row:arr){
            for (int data : row) {
                System.out.print("   " + data);
            }
            System.out.println();
        }

        //转换为稀疏数组
        int sum = 0;//sum为稀疏数组的行数
        //遍历数组得到sum的值
        for(int[] row:arr){
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        System.out.println(sum);

        //定义稀疏数组
        int[][] sparsArray =new int[sum+1][3];
        sparsArray[0][0]=arr.length;
        sparsArray[0][1] = arr[0].length;
        sparsArray[0][2] = sum;

        int count = 1; //定义计算器
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0) {
                    sparsArray[count][0] = i;
                    sparsArray[count][1] = j;
                    sparsArray[count][2] = arr[i][j];
                    count++;
                }
            }
        }
        //遍历稀疏数组
        for (int i = 0; i < sparsArray.length; i++) {
            for (int j = 0; j < sparsArray[0].length; j++) {
                System.out.print("  "+sparsArray[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------");


        //再转换回去
        int[][] newArray = new int[sparsArray[0][0]][sparsArray[0][1]];
        for (int i = 1; i < sparsArray.length; i++) {
            newArray[sparsArray[i][0]][sparsArray[i][1]] = sparsArray[i][2];
        }

        //遍历新数组
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[0].length; j++) {
                System.out.print("  "+newArray[i][j]);
            }
            System.out.println();
        }

        //输出到文件
//        FileOutputStream fos = new FileOutputStream(new File("map.txt"));
//        for (int i = 0; i < newArray.length; i++) {
//            for (int j = 0; j < newArray[0].length; j++) {
//                fos.write(newArray[i][j]);
//            }
//            System.out.println("\r\n");
//
//        }
//        fos.close();
//
//        //读取数据
//        FileInputStream fis = new FileInputStream("map.txt");
//        int by = 0;
//        while ((by = fis.read()) != -1) {
//            System.out.println(by+"\t");
//        }

        //使用序列化流进行读写
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("map2.txt"));
//        oos.writeObject(newArray);
//        oos.close();
//        //读取数据
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map2.txt"));
//        Object o = null;
//        try {
//            o = ois.readObject();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        ois.close();
//        System.out.println(o);
//        int[][] arr2 = (int[][]) o;
//        for (int i = 0; i < arr2.length; i++) {
//            for (int j = 0; j < arr2[0].length; j++) {
//                System.out.println(arr2[i][j]);
//            }
//        }


        //使用数据输入输出流
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("map3.txt"));
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[0].length; j++) {
                dos.writeInt(newArray[i][j]);
            }
        }
        dos.close();

        DataInputStream dis = new DataInputStream(new FileInputStream("map3.txt"));
        int b=0;
        while ((b = dis.readInt()) != -1) {
            System.out.println(b);
        }
        dis.close();



    }
}
