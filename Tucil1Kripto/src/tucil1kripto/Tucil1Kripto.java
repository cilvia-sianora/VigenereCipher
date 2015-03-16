package tucil1kripto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cilvia
 */
public class Tucil1Kripto {

	public static void main(String[] args) {
		Vigenere kripto = new Vigenere();
		String ciphertextBiasa = "LVVQHZNGFHRVL";
		String ciphertextVarian = "LVVQISIACEEFG";
		String plaintext = "Undang-Undang Dasar Negara Republik Indonesia Tahun 1945";
		String key = "sony";

		kripto.encryptInput(plaintext,key);
		kripto.encryptVarianVigenereStandard();
		kripto.ciphertextGroupOfFive();
		System.out.println(kripto.getCiphertext());
	}
	
}
