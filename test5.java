import java.util.Scanner;

/**
 * 题目：字符集合
 * 题目描述：输入一个字符串，求出该字符串包含的字符集合(去掉重复的字符)
 * 
 * 输入描述：每组数据输入一个字符串，字符串最大长度为100，且只包含字母，不可能为空串，区分大小写。
 * 
 * 输出描述: 每组数据一行，按字符串原有的字符顺序，输出字符集合，即重复出现并靠后的字母不输出。
 * 
 * 输入例子1:
 * abcqweracb
 * 
 * 输出例子1:
 * abcqwer
 */
public class test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            char[] charArray = s.toCharArray();
            String result = "" + charArray[0];
            for(int i = 1; i < charArray.length; i++){
                if(result.indexOf(charArray[i]) != -1){
                    continue;
                }
                result += charArray[i];
            }
            System.out.println(result);
        }
    }
}