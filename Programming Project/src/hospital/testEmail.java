package hospital;

public class testEmail {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Email(1));
		t1.start();
		

	}

}