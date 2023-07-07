package librarysystem;

import java.io.Serializable;

final public class Author implements Serializable {
	private String firstName, lastName;
	

	public Author(String f, String l) {
		firstName = f;
		lastName = l;
	}

	

}
