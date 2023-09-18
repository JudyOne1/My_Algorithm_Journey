package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static class47.Code02_RestoreWays.process0;

public class Code03_PrintAllSubsquences {

	// s -> "abc" ->
	public static List<String> subs(String s) {
		char[] str = s.toCharArray();
		String path = "";
		List<String> ans = new ArrayList<>();
		process1(str, 0, ans, path);
		return ans;
	}

	// str 固定参数
	// 来到了str[index]字符，index是位置
	// str[0..index-1]已经走过了！之前的决定，都在path上
	// 之前的决定已经不能改变了，就是path
	// str[index....最后]还能决定，之前已经确定，而后面还能自由选择的话，
	// 把所有生成的子序列，放入到ans里去
	public static void process1(char[] str, int index, List<String> ans, String path) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		// path不变 没有要index位置的字符
		process1(str, index + 1, ans, path);
		// path变了  要了index位置的字符
		process1(str, index + 1, ans, path + String.valueOf(str[index]));
	}

	public static List<String> subsNoRepeat(String s) {
		char[] str = s.toCharArray();
		String path = "";
		HashSet<String> set = new HashSet<>();
		process2(str, 0, set, path);
		List<String> ans = new ArrayList<>();
		for (String cur : set) {
			ans.add(cur);
		}
		return ans;
	}

	public static void process2(char[] str, int index, HashSet<String> set, String path) {
		if (index == str.length) {
			set.add(path);
			return;
		}
		String no = path;
		process2(str, index + 1, set, no);
		String yes = path + String.valueOf(str[index]);
		process2(str, index + 1, set, yes);
	}

	public static void main(String[] args) {
		String test = "acccc";
		List<String> ans1 = subs(test);
		List<String> ans2 = subsNoRepeat(test);

		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=================");
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=================");

	}
	public static List<String> printAllSon(String str){
		char[] chars = str.toCharArray();
		String path = "";
		List<String> ans = new ArrayList<>();
		process0(chars,0,path,ans);
		return ans;
	}

	private static void process0(char[] chars, int index,String path, List<String> ans) {
		if (index == chars.length){
			//到最后了，将结果保存
			ans.add(path);
		}
		//可以要可以不要，要就带上去下一个位置，不要就直接去下一个位置。
		process0(chars,index+1,path+chars[index],ans);
		process0(chars,index+1,path,ans);
	}

}
