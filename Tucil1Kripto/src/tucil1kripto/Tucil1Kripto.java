/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tucil1kripto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anggi
 */
public class Tucil1Kripto {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Vigenere kripto = new Vigenere();
		String ciphertextBiasa = "LVVQHZNGFHRVL";
		String ciphertextVarian = "LVVQISIACEEFG";
		String plaintext = "Undang-Undang Dasar Negara Republik Indonesia Tahun 1945";
		String key = "sony";
//		ciphertext = kripto.vigenereCipherStandard("THISPLAINTEXT", "sony");
		kripto.encryptInput(plaintext,key);
		//kripto.encryptVigenereCipherStandard();
		kripto.encryptVarianVigenereStandard();
		kripto.ciphertextGroupOfFive();
		System.out.println(kripto.getCiphertext());
		
//		kripto.encryptInput(plaintext,key);
//		System.out.println(kripto.encryptVigenereCipherExtended());
//		kripto.encryptInput(plaintext,key);
//		System.out.println(kripto.encryptVarianVigenereStandard());
//		kripto.encryptInput(plaintext,key);
//		System.out.println(kripto.encryptVarianVigenereExtended());
		
//		char hurufA = 'A';
//		String coba = Character.toString(hurufA);
//		if(coba.matches("[^a-zA-Z]")){
//			System.out.println("bukan alphabet");
//		} else {
//			System.out.println("alphabet");
//		}
//		
//		PrintWriter out = null;
//		try {
//			out = new PrintWriter("C:\\Users\\Anggi\\Documents\\kuliah\\Semester 6\\Kripto\\ciphertext.txt");
//		} catch (FileNotFoundException ex) {
//			System.out.println(ex.getMessage());
//		}
//		out.println(ciphertext);
//		out.close();
		
		kripto.decryptInput(ciphertextVarian, key);
//		System.out.println(kripto.decryptVarianVigenereStandard());
	}
	
}
