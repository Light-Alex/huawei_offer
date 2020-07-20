import java.util.Scanner;

/**
 * 题目：进制转换
 * 题目描述：写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。(多组同时输入)
 * 
 * 输入描述：
 * 输入一个十六进制的数值字符串。
 * 
 * 输出描述：
 * 输出该数值的十进制字符串。
 * 
 * 输入例子1:
 * 0xA
 * 
 * 输出例子1:
 * 10
 */
public class test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String Hexadecimal = sc.nextLine();
            System.out.println(transform(Hexadecimal));
        }
    }

    // 16进制转10进制
    public static int transform(String Hexadecimal){
        char[] charArray = Hexadecimal.toCharArray();
        int result = 0;
        for(int i = charArray.length - 1; i > 1; i--){
            if(charArray[i] >= '0' && charArray[i] <= '9'){
                result += (charArray[i] - '0') * Math.pow(16, charArray.length - 1 - i);
            }else{
                result += (10 + charArray[i] - 'A') * Math.pow(16, charArray.length - 1 - i);
            }
        }

        return result;
    }
}