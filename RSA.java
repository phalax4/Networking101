
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//To encrypt X: X^e mod C
//decrypt Y: Y^d mod c

//know e need to know m
//m is totient of c
//guess a and b  2 prime factors of c
//for loop of all numbers 2 to c. c%i
public class RSA {
	private int e;
	private int c;
	public RSA(){
		int a = getNthPrime(15);
		int b = getNthPrime(23);
		//System.out.println(a+"  "+ b);
		c = a*b;
		int m = (a-1)*(b-1);	
		e = getCoprime(m);
		
		System.out.println("Public key is: "+ "e: "+e+" c: "+c);
		//up to the sqrt of n not all the way to n
		
		int d = modulo(e,(totient(m)-1),m);
		System.out.println("private:"+ "d: "+d + " c:"+c);
		
		int X = 100;
		int Y = modulo(X,e,c);
		int XX = modulo(Y,d,c);
		System.out.println(X+" encrypted to "+Y+  " and decrypted back to "+XX);
	}
	public static void main(String args[]){
		//15,23
		//787,727
		int a = getNthPrime(15);
		int b = getNthPrime(23);
		//System.out.println(a+"  "+ b);
		int c = a*b;
		int m = (a-1)*(b-1);	
		int e = getCoprime(m);
		
		System.out.println("Public key is: "+ "e: "+e+" c: "+c);
		int d = modulo(e,(totient(m)-1),m);
		System.out.println("private:"+ "d: "+d + " c:"+c);
		//Scanner red = new Scanner(System.in);
		//System.out.println("Enter word: ");
		//String word = red.nextLine();
		
		/*
		for(int i = 0;i<word.length();i++){
			int letter = (int)word.charAt(i);
			int enc = modulo(letter,e,c);
			System.out.println(word.charAt(i)+ " encrpyts to "+ enc);
		}
		Scanner in2 = new Scanner(System.in);
		System.out.println("Enter your d and c");
		int yourd = in2.nextInt();
		int yourc = in2.nextInt();
		int num=0;
		*/
		/*
		do{
			System.out.println("Enter a number to decrypt: ");
			num = in2.nextInt();
			int dec = modulo(num,yourd,yourc);
			char ch = (char) dec;
			System.out.println("That decrypts to "+ch);
		}while(num!=0);
		*/
	}
	public static String decryptWord(String word,int yourd, int yourc ){//server needs word and private key
		//char[] dword = new char[word.length];
		int num = 0; int i = 0;
		String result = "";
		String [] letters = word.split(" ");
		//System.out.println(letters[0]);
		do{
			num = Integer.parseInt(letters[i]);
			int dec = modulo(num,yourd,yourc);
			char ch = (char) dec;
			//System.out.println("That decrypts to "+ch);
			result+=ch;
			i++;
			
		}while(i<letters.length);
		
		return result;
	}
	public static String encryptWord(String word,int e, int c){//needs public key
		String numWord = "";
		//int[] numWord = new int[word.length()];
		for(int i = 0;i<word.length();i++){
			int letter = (int)word.charAt(i);
			int enc = modulo(letter,e,c);
			numWord += (enc +" ");
			System.out.println(word.charAt(i)+ " encrpyts to "+ enc);
			
		}
		return numWord;
	}
	
	//public static String encryptSentence(String text,int e, int c){
		
		
	//}
	public static int getNthPrime(int n){
		int k = 0;
		int p = 2;
		while(k<n){
			p++;
			if(isprime(p))k++;
		}
	return p;
	}
	public static boolean isprime(int p){
		for(int i = 2;i<p;i++){
			if(p%i==0){
				return false;
			}
		}
		return true;
	}
	
	public static int GCD(int a, int b){
		if(b==0)return a;
		return GCD(b,a%b);
	}
	
	//a^b mod c
	public static int modulo(int a,int b, int c){
		BigInteger aa = BigInteger.valueOf(a);
		BigInteger bb = BigInteger.valueOf(b);
		BigInteger cc = BigInteger.valueOf(c);
		
		BigInteger result = aa.modPow(bb, cc);
		return result.intValue();
		
		/*long x = 1;
		long y = 1;
		while(b>0){
			if(b%2==1){
				x=(x*y)%c;
			}
			y=(y*y)%c;
			b/=2;
		}
		return (int)x%c;*/
	}
	public static int totient(int n){
		int count = 0;
		for(int i = 1;i<n;i++){
			if(GCD(n,i)==1){
				count++;
			}
		}
		return count;
	}
	
	public static int getCoprime(int n){
		int coprime = -1;
		
		
		for(int i =1;i<3*n;i++){
			if(GCD(n,i)==1)
				coprime=i;
		}
		return coprime;
		
	}
	
	
	public static int coprime(int x){
		int iTheCoPrime = x+1;
		ArrayList coprimes = new ArrayList();
		while(iTheCoPrime>=x){
			for(int i = 2;i<x;i++){
				if(GCD(i,x)==1){
					coprimes.add(new Integer(i));
				}
			}
			Random rand = new Random();
			int rand_index = rand.nextInt(coprimes.size());
			Integer thecoprime = (Integer) coprimes.get(rand_index);
			iTheCoPrime = thecoprime.intValue();
		}
		
		return iTheCoPrime;
	}
	public static int mod_inverse(int base, int mod){
		int tot = totient(mod)-1;
		int inv = modulo(base,tot,mod);
		return inv;
	}
	public static int endecrypt(int msg_or_cipher,int key,int mod){
		return modulo(msg_or_cipher,key,mod);
	}
	
}
