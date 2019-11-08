package Numer0n;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Numer0n {
	static StringBuilder sb = new StringBuilder();

	public static void main(String... args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 桁数を設定
		System.out.println("桁数を入力してください");
		String k;
		while (!Pattern.matches("^[1-9]{1}$", k = br.readLine())) {
			System.out.print("正しい形式で入力してください");
		}
		int n = Integer.parseInt(k);
		// 乱数初期固定 当てたい数字
		int[] number = getRandNum(n);
		// 処理回数
		int cnt = 0;
		// 結果の個数
		int eat, bite;
		String numberStr;
		System.out.println("-----START-----");
		while (true) {
			cnt++;
			// 入力数値
			while (!Pattern.matches("^[0-9]{" + n + "}$", numberStr = br.readLine())) {
				System.out.print("正しい形式で入力してください");
			}
			int[] num = Arrays.stream(numberStr.split("")).mapToInt(Integer::parseInt).toArray();
			num = Arrays.stream(num).distinct().toArray();
			if (num.length != n) {
				System.out.println("値が重複しています");
				System.out.println("再度入力してください");
				continue;
			}
			// クリア
			if (Arrays.equals(number, num)) {
				System.out.println(n + "EAT\nClear!!");
				break;
			}
			// Bite,eat数を把握する
			eat = getEat(number, num);
			bite = getBite(number, num);
			print(eat, "EAT");
			print(bite - eat, "BITE");
		}
		System.out.println(sb.append(cnt).append("回目に終了").toString());
	}

	static int[] getRandNum(int n) {
		List list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		Collections.shuffle(list);
		int[] ary = new int[n];
		for (int i = 0; i < ary.length; i++) {
			ary[i] = Integer.parseInt(list.get(i).toString());
			// System.out.print(ary[i]); // 答えが表示されます
		}
		return ary;
	}

	static int getEat(int[] number, int[] num) {
		int eat = 0;
		for (int i = 0; i < num.length; i++) {
			if (number[i] == num[i]) {
				eat++;
			}
		}
		return eat;
	}

	static int getBite(int[] number, int[] num) {
		int bite = 0;
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < num.length; j++) {
				if (number[i] == num[j]) {
					bite++;
				}
			}
		}
		return bite;
	}

	static void print(int num, String str) {
		sb.setLength(0);
		sb.append(str);
		sb.append("は、");
		sb.append(num);
		sb.append("です。");
		System.out.println(sb.toString());
		sb.setLength(0);
	}
}