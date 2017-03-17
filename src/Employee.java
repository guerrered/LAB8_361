
public class Employee {

	String lastName;
	String firstName;
	String Phone;
	String Department;
	String Gender;
	String Title;
	public Employee(String LastName, String FirstName, String Phone, String Department, String Gender, String Title){
		lastName = LastName;
		firstName = FirstName;
		this.Phone = Phone;
		this.Department = Department;
		this.Gender = Gender;
		this.Title = Title;
	}
	
	public String toString(){
		String str="";
		str = str+"LastName: "+lastName+", First Name: "+firstName+ ", CellPhone: "+ Phone + ", Department: " + Department + ", Gender: " + Gender + ", Title: " + Title;
		return str;
	}
	
	
	
	
}
