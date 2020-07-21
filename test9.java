import java.util.Scanner;

import sun.net.www.content.audio.basic;

/**
 * 题目：扑克牌大小
 * 题目描述：扑克牌游戏大家应该都比较熟悉了，一副牌由54张组成，含3~A，2各4张，小王1张，大王1张。牌面从小到大用如下字符和字符串表示（其中，小写joker表示小王，大写JOKER表示大王）:)
 * 3 4 5 6 7 8 9 10 J Q K A 2 joker JOKER
 * 输入两手牌，两手牌之间用“-”连接，每手牌的每张牌以空格分隔，“-”两边没有空格，如：4 4 4 4-joker JOKER
 * 请比较两手牌大小，输出较大的牌，如果不存在比较关系则输出ERROR
 * 
 * 基本规则：
 * （1）输入每手牌可能是个子，对子，顺子（连续5张），三个，炸弹（四个）和对王中的一种，不存在其他情况，由输入保证两手牌都是合法的，顺子已经从小到大排列；
 * （2）除了炸弹和对王可以和所有牌比较之外，其他类型的牌只能跟相同类型的存在比较关系（如，对子跟对子比较，三个跟三个比较），不考虑拆牌情况（如：将对子拆分成个子）
 * （3）大小规则跟大家平时了解的常见规则相同，个子，对子，三个比较牌面大小；顺子比较最小牌大小；炸弹大于前面所有的牌，炸弹之间比较牌面大小；对王是最大的牌；
 * （4）输入的两手牌不会出现相等的情况。
 * 
 * 答案提示： （1）除了炸弹和对王之外，其他必须同类型比较。 （2）输入已经保证合法性，不用检查输入是否是合法的牌。
 * （3）输入的顺子已经经过从小到大排序，因此不用再排序了.
 * 
 * 输入描述： 输入两手牌，两手牌之间用“-”连接，每手牌的每张牌以空格分隔，“-”两边没有空格，如4 4 4 4-joker JOKER。
 * 
 * 输出描述： 输出两手牌中较大的那手，不含连接符，扑克牌顺序不变，仍以空格隔开；如果不存在比较关系则输出ERROR。
 * 
 * 输入例子： 4 4 4 4-joker JOKER
 * 
 * 输出例子： joker JOKER
 */
public class test9 {
    public static void main(String[] args) {
        // String a = "a";
        // System.out.println(a.split(" ").length);
        // System.out.println(a.split(" ")[0]);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String two = sc.nextLine();
            String[] twoArray = two.split("-");
            String first = twoArray[0];
            String second = twoArray[1];
            
            String[] firstArray = first.split(" ");
            String[] secondArray = second.split(" ");

            // 两手牌中含对王的情况
            if(first.equals("joker JOKER") || second.equals("joker JOKER")){
                System.out.println("joker JOKER");
            // 两手牌中含炸弹的情况
            // 第一副手牌是炸弹，第二幅手牌不是
            }else if(firstArray.length == 4 && sameElement(firstArray) && (!sameElement(secondArray) || secondArray.length != 4)){
                System.out.println(first);
            // 第二副手牌是炸弹，第一幅手牌不是
            }else if(secondArray.length == 4 && sameElement(secondArray) && (!sameElement(firstArray) || firstArray.length != 4)){
                System.out.println(second);
            // 两手牌都是炸弹
            }else if(firstArray.length == 4 && sameElement(firstArray) && secondArray.length == 4 && sameElement(secondArray)){
                if(first.charAt(0) > second.charAt(0)){
                    System.out.println(first);
                }else{
                    System.out.println(second);
                }
            // 两手牌是个子，对子，三个
            }else if(firstArray.length == secondArray.length && sameElement(firstArray) && sameElement(secondArray)){
                if(first.charAt(0) > second.charAt(0)){
                    System.out.println(first);
                }else{
                    System.out.println(second);
                }
            // 两手牌是顺子
            }else if(firstArray.length == 5 && firstArray.length == secondArray.length && continuity(firstArray) && continuity(secondArray)){
                if(Integer.parseInt(firstArray[0]) > Integer.parseInt(secondArray[0])){
                    System.out.println(first);
                }else{
                    System.out.println(second);
                }
            // 其余情况输出ERROR
            }else{
                System.out.println("ERROR");
            }
        }
    }
    
    // 判断字符串中元素相同
    public static boolean sameElement(String[] stringArray){
        String temp = stringArray[0];
        for(String s : stringArray){
            if(!s.equals(temp)){
                return false;
            }
        }
        return true;
    }

    // 判断字符串中的元素是否连续
    public static boolean continuity(String[] s){
        if(s[s.length-1].charAt(0) >= 'J' && s[s.length-1].charAt(0) <= 'K'){
            s[s.length-1] = String.valueOf(s[s.length-1].charAt(0) - 'J' + 11);
        }else if(s[s.length-1].charAt(0) == 'A'){
            s[s.length-1] = "14";
        }else if(s[s.length-1].charAt(0) == '2'){
            s[s.length-1] = "15";
        }else if(s[s.length-1].equals("joker")){
            s[s.length-1] = "16";
        }else if(s[s.length-1].equals("Joker")){
            s[s.length-1] = "17";
        }

        return Integer.parseInt(s[s.length-1]) - Integer.parseInt(s[0]) == s.length - 1 ? true : false;
    }
}