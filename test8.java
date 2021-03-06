import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 题目：简单错误记录 题目描述：开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。 处理:
 * 1.记录最多8条错误记录，对相同的错误记录(即文件名称和行号完全匹配)只记录一条，错误计数增加；(文件所在的目录不同，文件名和行号相同也要合并)
 * 2.超过16个字符的文件名称，只记录文件的最后有效16个字符；(如果文件名不同，而只是文件名的后16个字符和行号相同，也不要合并)
 * 3.输入的文件可能带路径，记录文件名称不能带路径
 * 
 * 输入描述： 一行或多行字符串。每行包括带路径文件名称，行号，以空格隔开。 文件路径为windows格式
 * 如：E:\V1R2\product\fpgadrive.c 1325
 * 
 * 输出描述： 将所有的记录统计并将结果输出，格式：文件名代码行数数目，一个空格隔开，如: fpgadrive.c 1325 1
 * 结果根据数目从多到少排序，数目相同的情况下，按照输入第一次出现顺序排序。 如果超过8条记录，则只输出前8条记录.
 * 如果文件名的长度超过16个字符，则只输出后16个字符
 * 
 * 输入例子： E:\V1R2\product\fpgadrive.c 1325
 * 
 * 输出例子： fpgadrive.c 1325 1
 */
public class test8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        while(sc.hasNext()){
            String s = sc.nextLine();
            String file_path = s.split(" ")[0];
            // regex中 \ 需要用\\\\表示 \\\\ ->(java解析) \\ ->(regex解析) \
            String[] pathArray = file_path.split("\\\\");
            String file_name = pathArray[pathArray.length-1];
            if(file_name.length() > 16){
                file_name = file_name.substring(file_name.length() - 16, file_name.length());
            }
            String row = s.split(" ")[1];

            String newName = file_name + " " + row;

            if(!result.containsKey(newName)){
                result.put(newName, 1);
            }else{
                result.put(newName, result.get(newName) + 1);
            }
        }

        // HashMap排序
        // 将entrySet转换为List,然后重写比较器比较即可.这里可以使用List.sort(comparator),也可以使用Collections.sort(list,comparator)
        // entry: 条目
        Set<Entry<String, Integer>> entrys = result.entrySet();
        // 通过构造函数传入参数
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(entrys);

        // 使用Collections.sort(List<T> list, Comparator<? super T> c)方法对list进行排序, 需要重写 Comparator<T>类 中的 compare()方法
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
                // 降序排序
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        int k = 0;
        for(Map.Entry<String, Integer> entry : list){
            System.out.println(entry.getKey() + " " + entry.getValue());
            k++;
            if(k == 9){
                break;
            }
        }
    }
}