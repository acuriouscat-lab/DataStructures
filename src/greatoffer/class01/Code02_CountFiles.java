package greatoffer.class01;

import java.io.File;
import java.util.LinkedList;
import java.util.Stack;

public class Code02_CountFiles {

    // problem
    //给定一个文件目录的路径，
    //写一个函数统计这个目录下所有的文件数量并返回
    //隐藏文件也算，但是文件夹不算

    public static int getFileNumber(String folderPath) {
        File root = new File(folderPath);
        if (root.isFile()) {
            return 1;
        }
        int count = 0;
        LinkedList<File> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            File directions = queue.poll();
            for (File file : directions.listFiles()) {
                if (file.isFile()) {
                    count++;
                } else if (file.isDirectory()) {
                    queue.add(file);
                }
            }
        }
        return count;
    }

    // 注意这个函数也会统计隐藏文件
    public static int getFileNumber2(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int files = 0;
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            for (File next : folder.listFiles()) {
                if (next.isFile()) {
                    files++;
                }
                if (next.isDirectory()) {
                    stack.push(next);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
        // 你可以自己更改目录
        String path = "D:\\DataStructures\\src";
        System.out.println(getFileNumber(path));
        System.out.println(getFileNumber2(path));
    }

}
