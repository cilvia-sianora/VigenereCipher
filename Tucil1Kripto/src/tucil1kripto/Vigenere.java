package tucil1kripto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Cilvia
 */
public class Vigenere {
	private String plaintext, key, ciphertext;
	private int iDecryptKey;
	
	public String getCiphertext() {
		return ciphertext;
	}
	public String getKey() {
		return key;
	}
	public String getPlaintext() {
		return plaintext;
	}
	
	/** ENCRYPT **/
	
	/* Mengenkripsi plaintext menggunakan Vigenere Cipher Standard */
	public void encryptVigenereCipherStandard(){
		encryptVigenereCipher();
		encryptStandard();
	}
	
	/* Mengenkripsi plaintext menggunakan Vigenere Cipher Standard */
	public void encryptVigenereCipherExtended(){
		encryptVigenereCipher();
		encryptExtended();
		
	}
	
	/* Mengenkripsi plaintext menggunakan Varian Vigenere Cipher Standard */
	public void encryptVarianVigenereStandard(){
		encryptVarianVigenere("standard");
		encryptStandard();
		
	}
	
	/* Mengenkripsi plaintext menggunakan Varian Vigenere Cipher Standard */
	public void encryptVarianVigenereExtended(){
		encryptVarianVigenere("extended");
		encryptExtended();
		
	}
	
	/* Mengatur panjang key sesuai aturan Varian Vigenere Cipher */
	private void encryptVarianVigenere(String method){
		String plaintextToKey = "";
		if(method.equals("standard")){
			plaintextToKey = plaintext.replaceAll("[^a-zA-Z]", "");
		} else {
			plaintextToKey = plaintext;
		}
		
		int i = 0;
		if (key.length() < plaintextToKey.length()){
			do{
				key += plaintextToKey.charAt(i);
				i++;
			}while(key.length() < plaintextToKey.length());
		} else if (key.length() > plaintextToKey.length()) {
			key = key.substring(0, plaintextToKey.length()-1);
		}
	}
	
	/* Mengatur panjang key sesuai aturan Vigenere Cipher */
	private void encryptVigenereCipher(){
		int i = 0;
		int keyLength = key.length();
		
		if (key.length() < plaintext.length()){
			do{
				key += key.charAt(i);
				if(i == keyLength-1){
					i = 0;
				} else {
					i++;
				}
			}while(key.length() < plaintext.length());
		} else if (key.length() > plaintext.length()) {
			key = key.substring(0, plaintext.length()-1);
		}
	}
	
	/* Mengenkripsi plaintext secara standard (26 huruf alfabet) */
	private void encryptStandard(){
		key = key.toUpperCase();
		plaintext = plaintext.toUpperCase();
		ciphertext = "";
		
		int iPlaintext = 0;
		int iKey = 0;
		int charPlaintext, charKey, intCiphertext;
		char charCiphertext;
		String charPlain;
		
		while(iPlaintext < plaintext.length()){
			charPlaintext = (int) plaintext.charAt(iPlaintext);
			charKey = (int) key.charAt(iKey);
			
			charPlain = Character.toString(plaintext.charAt(iPlaintext));
			if(charPlain.matches("[^a-zA-Z]")){
				charCiphertext = plaintext.charAt(iPlaintext);
			} else {
				intCiphertext = charPlaintext + charKey - 65;
				if (intCiphertext > 90){
					intCiphertext = 64 + intCiphertext - 90;
				}
				charCiphertext = (char) intCiphertext;
				iKey++;
			}
			
			ciphertext += charCiphertext;
			iPlaintext++;
		}
	}
	
	/* Mengenkripsi plaintext secara extended (256 karakter ASCII) */
	private void encryptExtended(){
		int i = 0;
		int charPlaintext, charKey, intCiphertext;
		char charCiphertext;
		ciphertext = "";
		
		while(i < plaintext.length()){
			charPlaintext = (int) plaintext.charAt(i);
			charKey = (int) key.charAt(i);
			
			intCiphertext = charPlaintext + charKey - 1;
			if (intCiphertext > 255){
				intCiphertext -= 255;
			}
			charCiphertext = (char) intCiphertext;
			
			ciphertext += charCiphertext;
			i++;
		}
		
	}
	
	/** DECRYPT **/
	
	/* Mengenkripsi plaintext menggunakan Vigenere Cipher Standard */
	public void decryptVigenereCipherStandard(){
		iDecryptKey = 0;
		decryptVigenereCipher();
		decryptStandard(0);
	}
	
	/* Mengenkripsi plaintext menggunakan Vigenere Cipher Standard */
	public void decryptVigenereCipherExtended(){
		decryptVigenereCipher();
		decryptExtended(0);
	}
	
	/* Mengenkripsi plaintext menggunakan Varian Vigenere Cipher Standard */
	public void decryptVarianVigenereStandard(){
		int keyLength = key.length();
		iDecryptKey = 0;
		int i = 0;
		do{
			decryptVarianVigenere(i,"standard"); // membuat key, i untuk plaintext
			decryptStandard(plaintext.length()); // mendekripsi, parameter buat idx awal mendekrip
			i = plaintext.length() - keyLength;
		}while(this.key.length() < this.ciphertext.length());
	}
	
	/* Mengenkripsi plaintext menggunakan Varian Vigenere Cipher Standard */
	public void decryptVarianVigenereExtended(){
		int keyLength = key.length();
		int i = 0;
		do{
			decryptVarianVigenere(i,"extended"); // membuat key, i untuk plaintext
			decryptStandard(plaintext.length()); // mendekripsi, parameter buat idx awal mendekrip
			i = plaintext.length() - keyLength;
		}while(key.length() < ciphertext.length());
	}
	
	/* Mengatur panjang key sesuai aturan Varian Vigenere Cipher */
	private void decryptVarianVigenere(int idxAwal, String method){
		if (key.length() < ciphertext.length()){
			if(!plaintext.equals("")){
				String charTemp;
				do{
					charTemp = Character.toString(plaintext.charAt(idxAwal));
					if((method.equals("standard")) && (charTemp.matches("[^a-zA-Z]"))){
						//DO NOTHING
					} else {
						key += plaintext.charAt(idxAwal);
					}
					idxAwal++;
				}while((key.length() < ciphertext.length()) && (idxAwal < plaintext.length()));
			}
		} else if (key.length() > ciphertext.length()) {
			key = key.substring(0, ciphertext.length()-1);
		}
	}
	
	/* Mengatur panjang key sesuai aturan Vigenere Cipher */
	private void decryptVigenereCipher(){
		int i = 0;
		int keyLength = key.length();
		
		if (key.length() < ciphertext.length()){
			do{
				key += key.charAt(i);
				if(i == keyLength-1){
					i = 0;
				} else {
					i++;
				}
			}while(key.length() < ciphertext.length());
		} else if (key.length() > ciphertext.length()) {
			key = key.substring(0, ciphertext.length()-1);
		}
	}
	
	/* Mendekripsi ciphertext secara standard (26 huruf alfabet) */
	private void decryptStandard(int idxAwal){
		key = key.toUpperCase();
		ciphertext = ciphertext.toUpperCase();
		
		int charCiphertext, charKey, intPlaintext;
		char charPlaintext;
		String charCipher;
		
		
		while(idxAwal < key.length()){
			charCiphertext = (int) ciphertext.charAt(idxAwal);
			charKey = (int) key.charAt(iDecryptKey);
			
			charCipher = Character.toString(ciphertext.charAt(idxAwal));
			if(charCipher.matches("[^a-zA-Z]")){
				charPlaintext = ciphertext.charAt(idxAwal);
			} else {
				intPlaintext = charCiphertext - charKey + 65;
				if (intPlaintext < 65){
					intPlaintext = 90 - (64 - intPlaintext);
				}
				charPlaintext = (char) intPlaintext;
				iDecryptKey++;
			}
			
			plaintext += charPlaintext;
			idxAwal++;
		}
	}
	
	/* Mendekripsi ciphertext secara extended (256 karakter ASCII) */
	private void decryptExtended(int idxAwal){
		int charCiphertext, charKey, intPlaintext;
		char charPlaintext;
		
		while(idxAwal < key.length()){
			charCiphertext = (int) ciphertext.charAt(idxAwal);
			charKey = (int) key.charAt(idxAwal);
			
			intPlaintext = charCiphertext - charKey + 1;
			if (intPlaintext < 0){
				intPlaintext += 255;
			}
			charPlaintext = (char) intPlaintext;
			
			plaintext += charPlaintext;
			idxAwal++;
		}
	}
	
	/* OTHER */
	
	public void ciphertextWithoutSpace(){
		ciphertext = ciphertext.replaceAll("\\s+","");
	}
	
	public void ciphertextGroupOfFive(){
		int i = 1;

		ciphertextWithoutSpace();
		
		String temp = "";
		while(i <= ciphertext.length()){
			temp += ciphertext.charAt(i-1);
			if(i % 5 == 0){
				temp += " ";
			}
			i++;
		}
		ciphertext = temp;
	}
	
	public void encryptFile(String filePlaintext, String key){
		clearAttribute();
		this.key = key;
		try {
			plaintext = new Scanner(new File(filePlaintext)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void encryptInput(String plaintext, String key){
		clearAttribute();
		this.plaintext = plaintext;
		if (key.length() > 25){
			this.key = key.substring(0, 24);
		} else {
			this.key = key;
		}
		
	}
	
	public void decryptFile(String fileCiphertext, String key){
		clearAttribute();
		this.key = key;
		try {
			ciphertext = new Scanner(new File(fileCiphertext)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void decryptInput(String ciphertext, String key){
		clearAttribute();
		this.ciphertext = ciphertext;
		if (key.length() > 25){
			this.key = key.substring(0, 24);
		} else {
			this.key = key;
		}
	}
	
	public void ciphertextToFile(String fileOutput){
		PrintWriter out = null;
		try {
			out = new PrintWriter(fileOutput);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		out.println(ciphertext);
		out.close();
	}
	
	private void clearAttribute(){
		plaintext = "";
		key = "";
		ciphertext = "";
	}
}
