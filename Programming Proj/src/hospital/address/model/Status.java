package hospital.address.model;

/**
 * @author Kieron Allsop Gets the Status code from the Queue timer and makes an
 *         Object to wrap an Integer in so it can be displayed in an observable
 *         list.
 */
public class Status {
	/**
	 * Instance Var for the wrapper code. 
	 */
	private Integer code;

	/**
	 * Default Constructor
	 */
	public Status() {
	}

	/**
	 * Constructor with Args.
	 * @param code
	 */
	public Status(Integer code) {
		this.code = code;
	}

	/**
	 * Getter for getCode()
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Setter for setCode
	 * @param code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

} // Class Close.
