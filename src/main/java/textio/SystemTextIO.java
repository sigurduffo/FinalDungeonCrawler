package textio;

import java.util.Scanner;

public class SystemTextIO implements TextIO {
	private Scanner scanner;

	public SystemTextIO () {
		scanner = new Scanner(System.in);
	}

	@Override
	public void put(String message) {
		System.out.println(message);
	}

	@Override
	public String get(String prompt) {
		System.out.print(prompt + ": ");
		return scanner.nextLine();
	}

	@Override
	public int getInt(String prompt, int min, int max) {
		int value;
		while (true) {
			System.out.print(prompt + ": ");
			try {
				value = Integer.parseInt(scanner.nextLine());
				if (value >= min && value <= max) {
					break;
				} else {
					System.out.println("Please enter a number between " + min + " and " + max + ".");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number.");
			}
		}
		return value;
	}
}
