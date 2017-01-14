
public class DecodeSubjectName {
	private String address = "";
	
	public DecodeSubjectName(String levelName) {
		
	}
	
	@Override
	public String toString() {
		return address;
	}
	
	public static void main(String[] args) {
		DecodeSubjectName test = new DecodeSubjectName("MJ-BB01");
		System.out.println(test);
	}
}
