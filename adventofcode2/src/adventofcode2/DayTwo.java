package adventofcode2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DayTwo {

	private static int countSafe2(List<String> numbers) {

		int res = 0;
		res += countSafe(numbers);
		for (int i = 0; i < numbers.size(); i++) {
			List<String> buff = new ArrayList<>(numbers);
			buff.remove(i);
			res += countSafe(buff);
			if (res >= 1) {
				return 1;
			}
		}
		for (String sad : numbers) {
			System.out.print(sad + " ");
		}
		System.out.println();
		return 0;
	}

	private static int countSafe(List<String> numbers) {
		boolean increasing = true;
		int firstNumber = Integer.parseInt(numbers.get(0));
		int secondNumber = Integer.parseInt(numbers.get(1));
		if (firstNumber == secondNumber) {
			return 0;
		}
		if (firstNumber > secondNumber) {
			increasing = false;
		}
		for (int i = 0; i < numbers.size() - 1; i++) {
			firstNumber = Integer.parseInt(numbers.get(i));
			secondNumber = Integer.parseInt(numbers.get(i + 1));
			if (increasing) {
				int diff = secondNumber - firstNumber;
				if (diff < 1 || diff > 3) {
					return 0;
				}
			} else {
				int diff = firstNumber - secondNumber;
				if (diff < 1 || diff > 3) {
					return 0;
				}
			}
		}
		System.out.println(numbers.toString());
		return 1;
	}

	public static void readFile() {
		int res = 0;
		try {
			File file = new File("D:\\workspaces\\adventofcode2\\adventofcode2\\src\\adventofcode2\\daytwo.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				// System.out.println(data);
				List<String> numbers = Arrays.asList(data.split(" "));
				res += countSafe2(numbers);
			}
			System.out.println(res);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
