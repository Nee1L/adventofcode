package adventofcode2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayNine {

	private static void partTwoCalculations(ArrayList<String> result, ArrayList<Pair> dots, ArrayList<Pair> numbers) {

		for (int i = numbers.size() - 1; i >= 0; i--) {
			for (int j = 0; j < dots.size(); j++) {
				if (numbers.get(i).index < dots.get(j).index) {
					break;
				}
				if (numbers.get(i).length <= dots.get(j).length) {
					int counter = 0;
					for (int k = dots.get(j).index; k < dots.get(j).index + numbers.get(i).length; k++) {
						result.set(k, numbers.get(i).value);
						if (numbers.get(i).index + counter < result.size()) {
							result.set(numbers.get(i).index + counter, ".");
						}
						counter++;
					}
					dots.get(j).index += numbers.get(i).length;
					dots.get(j).length -= numbers.get(i).length;
//					for (String a : result) {
//						System.out.print(a);
//					}
//					System.out.println();
					break;
				}
			}
		}
		for (String a : result) {
			System.out.print(a);
		}
		System.out.println();
		long res = 0;
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i).equals(".")) {
				continue;
			}
			res += i * Integer.parseInt(result.get(i));
		}
		System.out.println(res);

	}

	private static void partOneCalculations(ArrayList<String> result) {
		boolean lol = true;
		while (lol) {
			int i = result.indexOf(".");
			for (int j = result.size() - 1; j >= 0; j--) {
				if (!result.get(j).equals(".")) {
					if (j < i) {
						lol = false;
						break;
					}
					result.set(i, result.get(j));
					result.remove(j);
					break;
				}
			}
		}
		for (String a : result) {
			System.out.print(a);
		}
		System.out.println();
		long res = 0;
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i).equals(".")) {
				break;
			}
			res += i * Integer.parseInt(result.get(i));
		}
		System.out.println(res);
	}

	private static void partOne(String[] input) {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<Pair> dots = new ArrayList<>();
		ArrayList<Pair> numbers = new ArrayList<>();
		boolean lol = true;
		for (int i = 0; i < input.length; i++) {
			int elem = Integer.parseInt(input[i]);
			Pair buff = new Pair();
			buff.index = result.size();
			for (int j = 0; j < elem; j++) {
				buff.length += 1;
				if (i == 0 || i % 2 == 0) {
					result.add(String.valueOf(i / 2));
				} else {
					result.add(".");

				}
			}
			if (i == 0 || i % 2 == 0) {
				buff.value = String.valueOf(i / 2);
				numbers.add(buff);
			} else {
				dots.add(buff);
			}
		}
		for (String a : result) {
			System.out.print(a);
		}
		System.out.println();
		// partOneCalculations(result);
		partTwoCalculations(result, dots, numbers);
	}

	public static void readFile() {
		try {
			File file = new File("D:\\workspaces\\adventofcode2\\adventofcode2\\src\\adventofcode2\\daynine.txt");
			Scanner scanner = new Scanner(file);
			StringBuilder sb = new StringBuilder();

			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			String data = new String(sb);
			String[] bebra = data.split("");
			partOne(bebra);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
