package adventofcode2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class DayOne {

	private static void partTwo(HashMap<Integer, Integer> bebra, ArrayList<Integer> left, ArrayList<Integer> right) {
		for (Integer number : bebra.keySet()) {
			for (int j = 0; j < right.size(); j++) {
				if (number.intValue() == right.get(j).intValue()) {
					bebra.put(number, bebra.get(number) + 1);
				}
				if (number.intValue() < right.get(j).intValue()) {
					break;
				}
			}
		}
	}

	private static int count2(HashMap<Integer, Integer> bebra, ArrayList<Integer> left) {
		int sum = 0;
		for (int i = 0; i < left.size(); i++) {
			System.out.println(left.get(i) + " " + " " + bebra.get(left.get(i)));
			sum += left.get(i) * bebra.get(left.get(i));
		}
		return sum;
	}

	private static int count(ArrayList<Integer> left, ArrayList<Integer> right) {
		int sum = 0;
		for (int i = 0; i < left.size(); i++) {
			sum += Math.abs(left.get(i) - right.get(i));
		}
		return sum;
	}

	public static void readFile() {
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();
		HashMap<Integer, Integer> bebra = new HashMap<>();
		int asd = 0;
		try {
			File file = new File("D:\\workspaces\\adventofcode2\\adventofcode2\\src\\adventofcode2\\dayone.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] numbers = data.split("   ");
				left.add(Integer.parseInt(numbers[0]));
				right.add(Integer.parseInt(numbers[1]));
				bebra.put(Integer.parseInt(numbers[0]), 0);
				System.out.println(data);
			}
			Collections.sort(left);
			Collections.sort(right);
			System.out.println();
			System.out.println(count(left, right));
			partTwo(bebra, left, right);
			System.out.println();
			System.out.println(count2(bebra, left));
			System.out.println();
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
