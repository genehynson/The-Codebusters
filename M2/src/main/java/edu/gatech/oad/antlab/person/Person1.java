package main.java.edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 1
 *  returns their name and a
 *  modified string 
 *  
 *  @author Bob
 *  @version 1.1
 */
public class Person1 {
  /** Holds the persons real name */
  private String name;
  	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
  public Person1(String pname) {
    name = pname;
  }
  	/**
	 * This method should take the string
	 * input and return its characters rotated
	 * 2 positions.
	 * given "gtg123b" it should return
	 * "g123bgt".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
  private static String calc(String input) {
		String[] letters = new String[input.length()];
		letters[letters.length-2] = input.substring(0, 0);
		letters[letters.length-1] = input.substring(1, 1);
		for (int i = 0; i < letters.length-2; i++){
			letters[i] = input.substring(i,i);
		}
		String reordered = "";
		for (int j = 0; j < letters.length; j++) {
			reordered.concat(letters[j]);
		}
		
		return reordered;
	}
	
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}

}
