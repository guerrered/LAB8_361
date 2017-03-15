
public class Employee {

	String lastName;
	String firstName;
	String Phone;
	String Department;
	
	public Employee(String LastName, String FirstName, String Phone, String Department){
		lastName = LastName;
		firstName = FirstName;
		this.Phone = Phone;
		this.Department = Department;
	}
	
	public String toString(){
		String str="";
		str = str+lastName+", "+firstName+ " "+ Phone + " " + Department;
		return str;
	}
	
	
	
	
}
