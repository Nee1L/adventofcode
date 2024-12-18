package adventofcode2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DaySeven {

	public static String toTernaryString(int number) {
		String result = "";
		long buff = number;
		while (buff > 0) {
			result = String.valueOf(buff % 3) + result;
			buff = buff / 3;
		}
		return result;
	}

	private static void dayTwo(ArrayList<String> values, List<List<String>> numbers) {
		long result = 0;
		for (int i = 0; i < values.size(); i++) {
			int ternaryLength = (int) Math.pow(3, numbers.get(i).size() - 1);
			for (int j = 0; j < ternaryLength; j++) {
				String max = toTernaryString(ternaryLength - 1);
				String curr = toTernaryString(j);
				for (int k = curr.length(); k < max.length(); k++) {
					curr = "0" + curr;
				}
				long res = Long.parseLong(numbers.get(i).get(0));
				for (int k = 0; k < curr.length(); k++) {
					if (curr.charAt(k) == '0') {
						res += Long.parseLong(numbers.get(i).get(k + 1));
					}
					if (curr.charAt(k) == '1') {
						res *= Long.parseLong(numbers.get(i).get(k + 1));
					}
					if (curr.charAt(k) == '2') {
						res = Long.parseLong(String.valueOf(res) + numbers.get(i).get(k + 1));
					}
				}
				if (res == Long.parseLong(values.get(i))) {
					result += res;
					break;
				}

			}
		}
		System.out.println(result);
	}

	private static void dayOne(ArrayList<String> values, List<List<String>> numbers) {
		long result = 0;
		for (int i = 0; i < values.size(); i++) {
			int binaryLength = (int) Math.pow(2, numbers.get(i).size() - 1);
			for (int j = 0; j < binaryLength; j++) {
				String max = Long.toBinaryString(binaryLength - 1);
				String curr = Long.toBinaryString(j);
				for (int k = curr.length(); k < max.length(); k++) {
					curr = "0" + curr;
				}
				long res = Long.parseLong(numbers.get(i).get(0));
				for (int k = 0; k < curr.length(); k++) {
					if (curr.charAt(k) == '0') {
						res += Long.parseLong(numbers.get(i).get(k + 1));
					}
					if (curr.charAt(k) == '1') {
						res *= Long.parseLong(numbers.get(i).get(k + 1));
					}
				}
				if (res == Long.parseLong(values.get(i))) {
					result += res;
					break;
				}

			}
		}
		System.out.println(result);
	}

	public static void readFile() {
		ArrayList<String> values = new ArrayList<>();
		List<List<String>> numbers = new ArrayList<>();
		try {
			File file = new File("D:\\workspaces\\adventofcode2\\adventofcode2\\src\\adventofcode2\\dayseven.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] parse = data.split(":");
				values.add(parse[0]);
				List<String> add = new ArrayList<>(Arrays.asList(parse[1].split(" ")));
				add.remove(0);
//				for (int i = 0; i < add.size(); i++) {
//					if (add.get(i).equals("") || add.get(i).equals(" ")) {
//						add.remove(i);
//					}
//				}
				numbers.add(add);
			}
			dayOne(values, numbers);
			dayTwo(values, numbers);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
