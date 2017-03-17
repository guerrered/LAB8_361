
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collection;
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

}