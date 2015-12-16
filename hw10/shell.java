//I used several sources to do this homework including 
//http://stackoverflow.com/questions/13991007/execute-external-program-in-java
//http://www.avajava.com/tutorials/lessons/how-do-i-run-another-application-from-java.html
//https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html
//http://www.coderanch.com/t/524747/threads/java/Java-processes


import java.io.*; 
import java.util.*; 

public class shell{

public static void main(String [] args) throws Exception{
	
Scanner my_Scanner = new Scanner(System.in); 

String input; 						//Creating variables to
String partOne = "";				// hold input and flags needed to
String partTwo = "" ;				//run program, as well as a scanner to accept input.
boolean status = true;
int i = 0;
while(status){			
	
	input = new String();								//take a program name
	System.out.println("Enter program to run : ");
	input = my_Scanner.next();
	
	if(input.toLowerCase() == "exit"){			//if input is exit, then end the program.
		status = false;
		break;
	}
	while(input.charAt(i)!= '>' && i< input.length()-1){			 //getting the length of the program and making sure it's not
		i += 1; 													// to be redirected.
	} 
 
	if(i==input.length()-1){ 								//creating substrings with program to be run.		
			partOne = input; 								//if the input is just a program itself it is run
	} 
	else{ 
		partOne = input.substring(0, i-1); 
		partTwo = input.substring(i+2, input.length()); 
	} 

	if(partOne.endsWith("&")){ 		//if an ampersan is at the end, a non-blocking call is made.
	ProcessBuilder my_process = new ProcessBuilder(partOne.substring(0, partOne.length()-1)); 
 
 
 	if(partTwo != ""){  //checking the second substring for any further commnads like redirecting output;
 		my_process.redirectOutput(new File(partTwo)); 
 	} 
 		my_process.start(); 
 		System.out.println("Run " + my_process.command());
 		if(partTwo!=""){ 
 		System.out.println("run and direct: " + partTwo); 		
		} 
 
 	}		 
 		else{ 
 		 
 		ProcessBuilder my_process = new ProcessBuilder(partOne); 
 		if(partTwo != ""){ 
 			my_process.redirectOutput(new File(partTwo)); 
 			} 
 			Process p = my_process.start(); 
 			p.waitFor(); 
 			System.out.println("run:" + my_process.command()); 
 		if(partTwo!=""){ 
			System.out.println("run and direct: "+ partTwo) ;
				}
	
	
	
}

}
}
}