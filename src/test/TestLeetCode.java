package test;

import java.util.ArrayList;
import java.util.List;

public class TestLeetCode {

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        findWords(board, words);
    }

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for(String word : words){
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[0].length; j++){
                    process(board,word,res,i,j,0);
                }
            }
        }
        return res;
    }

    public static void process(char[][] board, String str, List<String> res, int i, int j, int k){
        if(k == str.length()){
            res.add(str);
            return;
        }
        if(board[i][j] != str.charAt(k)){
            return;
        }
        char cha = board[i][j];
        board[i][j] = '0';
        if(i > 0){
            process(board,str,res,i - 1,j,k + 1);
        }
        if(i < (board.length - 1)){
            process(board,str,res,i + 1,j,k + 1);
        }
        if(j > 0){
            process(board,str,res,i,j - 1,k + 1);
        }
        if(j < (board[0].length - 1)){
            process(board,str,res,i,j + 1,k + 1);
        }
        board[i][j] = str.charAt(k);
    }


}
