package affineCipher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.lang.StringBuilder;

public class AffineCipher {

	public static void main(String[] args) {

		int A = Integer.parseInt(args[0]); // Variables for encryption
		int B = Integer.parseInt(args[1]);
		BufferedReader inputText = null;
		StringBuilder inputToEncode = new StringBuilder();

		try {
			inputText = new BufferedReader(new FileReader(args[2]));		
			String s;
			while ((s = inputText.readLine()) != null) {
				inputToEncode.append(s);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (inputText != null)
					inputText.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		String str = new String(inputToEncode); // StringBuilder -> String -> Array
		char[] arrayToEncode = str.toCharArray();

		LinkedHashMap<Character, Integer> letterMap = new LinkedHashMap<Character, Integer>(); // Creates L-Hashmap: char->int
		String aToZ = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
		char[] aToZArray = aToZ.toCharArray();
		for (int i = 0; i < aToZArray.length; i++) {
			letterMap.put(aToZArray[i], i);
		}

		LinkedHashMap<Integer, Character> integerMap = new LinkedHashMap<Integer, Character>(); // Second L-Hashmap int->char
		for (int i = 0; i < aToZArray.length; i++) {
			integerMap.put(i, aToZArray[i]);
		}

		char[] encryptedArray = arrayToEncode;					
		for (int i = 0; i < arrayToEncode.length; i++) {		
			int x, y = 0;
			if (letterMap.get(arrayToEncode[i]) != null) {	//Find in L-Hashmap(char->int)
				x = letterMap.get(arrayToEncode[i]);				//Get numeric value
				y = ((A * x) + B) % 53;										//Encrypt value
				encryptedArray[i] = integerMap.get(y);								//return to char via L-Hashmap(int->char)
			} else {
				encryptedArray[i] = arrayToEncode[i];		//Retains numbers and symbols
			}
		}

		StringBuilder encryptedString = new StringBuilder();
		for (char c : encryptedArray) {
			encryptedString.append(c);
		}
		System.out.println(encryptedString);
	}

}
