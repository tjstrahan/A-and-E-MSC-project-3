package hospital;

import java.util.Scanner;

public class stub {

	public static void main(String[] args) {
		Receptionist r = new Receptionist();
		
		String yes = "yes";
		String no = "no";
		String answer;
		do {
		System.out.println("Look up patient?");
		Scanner scanner = new Scanner(System.in);
		answer = scanner.next();
		if(answer.equalsIgnoreCase(yes)){
		
		r.lookUpPatient();
		} else {
			System.out.println("exiting");
		}
		
		}while (answer.equalsIgnoreCase(yes));
		

	}
		

}
