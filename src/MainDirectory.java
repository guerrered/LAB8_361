
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MainDirectory {
    static List<Employee> mainList= new ArrayList<>();
	static Gson g = new Gson();
	public MainDirectory()
	{
		
	}
    
	
	public static void add(String in){
		Employee emp = g.fromJson(in, Employee.class);
		mainList.add(emp);
		//mainList.add(ep);
	}
    
	public static List<Employee> getList() {
		// TODO Auto-generated method stub
		return mainList;
	}
	

	public static void clear() {
		// TODO Auto-generated method stub
		mainList.clear();
	}

	public static void getUpdate(List<Employee> newOne)
	{
		mainList=newOne;
	}
	
	public static int getListSize(){
		return mainList.size();
	}
	
	public static void print(){
		if(mainList.isEmpty()){
			System.out.println("<empty directory>");
		}
		else{
			List<String> sorted = sort();
			for(int i=0; i<sorted.size(); i++){
				System.out.println(sorted.get(i));
			}
		}
		System.out.println();
	}
	
	public static String getAllEmployees(){
		
		String ret = "";
		if(mainList.isEmpty()){
			ret  = "<empty directory>";
		}
		else{
			List<String> sorted = sort();
			for(int i=0; i<sorted.size(); i++){
				ret = ret + sorted.get(i) + "\n";
			}
		}
		return ret;
		//System.out.println();
		
	}
	@SuppressWarnings("unchecked")
	public static List<String> sort(){
		List<String>sorted= new ArrayList<>();
		for(int i =0; i<mainList.size(); i++){
			sorted.add(mainList.get(i).toString());
		}
		sorted.sort(c);
		return sorted;
	}
	public static Comparator<String> c = new Comparator<String>(){
		public int compare(String p1,String p2){
			return p1.compareTo(p2);
			
		}
	};
	
	public void tableBuilder(){
		File inputFile = new File("Lab9.html");
		File tempFile = new File("myTempFile.txt");
		
		BufferedReader reader = null;
		BufferedWriter writer = null;

		String curLine ="temp";
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			writer = new BufferedWriter(new FileWriter(tempFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			//write the start of the file
			while((curLine = reader.readLine()) !=null){
				//skip the table
				if(!curLine.contains("<table")){
					writer.write(curLine);
				}
				else{
					//writes <table style="width:100%">
					writer.write(curLine +"\n");
					//write the table
					writer.write(newTable());
					
					//write the rest of the file
					while((curLine = reader.readLine())!=null){
						if(curLine.contains("</table>")){
							do{
								writer.write(curLine);
							}while((curLine = reader.readLine())!=null);
						}
					}
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//change name of file
		
	}
	
	public String newTable(){
		/*
	<tr>
    <th>Firstname</th>
    <th>Lastname</th> 
    <th>Department</th>
    <th>Title</th>
    <th>Phone Number</th>
  	</tr>
		 */
		
		String nt ="\t<tr>";
		for(int i=0; i<mainList.size(); i++){
			Employee e = mainList.get(i);
			
			nt+=("\n\t\t<th>" + e.firstName + "</th>"
			+"\n\t\t<th>"+ e.lastName + "</th>" 
			+"\n\t\t<th>"+ e.Department + "</th>" 
			+"\n\t\t<th>"+ e.Title + "</th>" 
			+"\n\t\t<th>"+ e.Phone+ "</th>");
			
		}
		nt+="\t</tr>\n";
		return nt;
	}

}