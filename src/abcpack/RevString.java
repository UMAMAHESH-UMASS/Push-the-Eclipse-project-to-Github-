package abcpack;

import java.util.Scanner;

public class  RevString{

	public static void main(String[] args) {
		
		//Reverse String
			
	      Scanner in = new Scanner(System.in);
	      String original, reverse = "";
	      System.out.println("Enter a string to reverse");
	      original = in.nextLine();
	 
	      int length = original.length();
	 
	      for ( int i = length - 1 ; i >= 0 ; i-- )
	         reverse = reverse + original.charAt(i);
	 
	      System.out.println("Reverse of entered string is: "+reverse);
	      
	      //with out using loop and string builder
	      //call method
	      String a=reverse("bhanu prakash");
	      System.out.println(a);
	      	}
	 //with out using loop and string builder
	//method body
    static String reverse(String str) {
  	   if(str.length() == 0)
  	     return "";
  	   return str.charAt(str.length() - 1) +    reverse(str.substring(0,str.length()-1));
  	 }


}
