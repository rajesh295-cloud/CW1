package backend;

public class ComboItems {
	/*
	 * This class us used to bind key and value into single object
	 * That object is them passed into combobox
	 * The object can then return value for selected key
	 */
	private String key; //Stores key
	private String value; //Stores value
	
	public ComboItems(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String toString() {
		/*
		 * Overrides default java's toString method
		 * Allows us to control return value of this object
		 */
		return key;
	}
	
	public String getKey() {
		/*
		 * This function returns key of this object
		 */
		return key;
	}
	
	public String getValue() {
		/*
		 * This function returns value of this object
		 */
		return value;
	}

}
