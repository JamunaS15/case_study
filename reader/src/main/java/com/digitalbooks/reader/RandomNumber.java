package com.digitalbooks.reader;
import java.util.Random;
public class RandomNumber {
	public static void main(String args[]) {
		String chars = "zyxwvutsrqponml9876";
		Random rnd = new Random();
		String str = "";
		StringBuilder sb = new StringBuilder(5);
		for(int i = 0; i < 5; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		str = sb.toString();
		System.out.println(" randome "+str);
	}
}
