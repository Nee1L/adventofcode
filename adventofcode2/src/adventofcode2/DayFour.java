package adventofcode2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFour {

	private static void partTwo(List<List<String>> arr) {
		Pattern pattern = Pattern.compile("(MAS)|(SAM)");
		int res = 0;
		for (int i = 0; i < arr.size() - 2; i++) {
			for (int j = 0; j < arr.get(i).size() - 2; j++) {
				String buff1 = arr.get(i).get(j) + arr.get(i + 1).get(j + 1) + arr.get(i + 2).get(j + 2);
				String buff2 = arr.get(i + 2).get(j) + arr.get(i + 1).get(j + 1) + arr.get(i).get(j + 2);
				Matcher matcher = pattern.matcher(buff1);
				Matcher matcher2 = pattern.matcher(buff2);
				while (matcher.find()) {
					while (matcher2.find()) {
						res++;
					}
				}
			}
		}
		System.out.println(res);
	}

	private static int findMatches(String input) {
		int res = 0;
		Pattern pattern = Pattern.compile("(XMAS)");
		Pattern pattern2 = Pattern.compile("(SAMX)");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			res++;
		}
		matcher = pattern2.matcher(input);
		while (matcher.find()) {
			res++;
		}
		return res;
	}

	public static void readFile() {
		List<List<String>> arr = new ArrayList<>();
		ArrayList<String> horizontally = new ArrayList<>();
		int res = 0;
		try {
			File file = new File("D:\\workspaces\\adventofcode2\\adventofcode2\\src\\adventofcode2\\dayfour.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				res += findMatches(data);
				List<String> parse = Arrays.asList(data.split(""));
				System.out.println(data);
				arr.add(parse);
			}
			System.out.println();
			for (int i = 0; i < arr.size(); i++) {
				String buff = "";
				for (int j = 0; j < arr.get(i).size(); j++) {

					buff = buff + arr.get(j).get(i);
					// System.out.print(arr.get(j).get(i));
				}
				res += findMatches(buff);
				horizontally.add(buff);
				System.out.println(buff);
			}
			System.out.println();
			for (int k = 0; k < arr.size() + arr.get(0).size(); k++) {
				String buff = "";
				for (int i = 0; i < arr.size(); i++) {
					for (int j = 0; j < arr.get(i).size(); j++) {
						if (i + j == k) {
							buff = buff + arr.get(i).get(j);
						}
					}
				}
				System.out.println(buff);
				res += findMatches(buff);
			}
			for (int k = -arr.size(); k < arr.size(); k++) {
				String buff = "";
				for (int i = 0; i < arr.size(); i++) {
					for (int j = 0; j < arr.get(i).size(); j++) {
						if (i == j + k) {
							buff = buff + arr.get(i).get(j);
						}
					}
				}
				System.out.println(buff);
				res += findMatches(buff);
			}
			System.out.println(res);
			partTwo(arr);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
