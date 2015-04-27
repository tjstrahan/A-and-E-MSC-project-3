package hospital.address;

public class testEmail {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Email(1));
		t1.start();
		
		Thread t2 = new Thread(new Email(2));
		t2.start();
	}

}
