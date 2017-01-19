import java.io.FileNotFoundException;

public class DecodeSubjectName {
	private String path = "";
	private String fullName = "";
	
	public DecodeSubjectName(String levelName) {
		path += Index.DB_LOCATION;
		switch (levelName.charAt(0)) {
		case 'M':
			path += "MATH_DB";
			break;
		case 'P':
			path += "PHYSICS_DB";
			break;
		default:
			break;
		}
		path += "\\" + levelName.substring(0, levelName.indexOf('-')) + "\\";
		FileUtil tempFolder = new FileUtil(path);
		try {
			path += tempFolder.getMapFullNameFromFolder().get(levelName.substring(0, levelName.indexOf('0') - 1)) + "\\";
			fullName = path;
		} catch (FileNotFoundException e) {
		}
		try {
			FileUtil revGetter = new FileUtil(path);
			path += levelName + revGetter.getRev(levelName) + "\\" + levelName;
		} catch (Exception e) {
		}
	}
	
	@Override
	public String toString() {
		return path;
	}
	
	public String getFullName() {
		return fullName;
	}
	
}
