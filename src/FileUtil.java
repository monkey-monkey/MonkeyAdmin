import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileUtil {
	
	private String path;
	private File[] folderList;
	
	public FileUtil(String path) {
		this.path = path;
	}
	
	public ArrayList<String> getFileNameList() {
		ArrayList<String> fileNameList = new ArrayList<String>();
		listFile();
		for (int i = 0; i < folderList.length; i++) {
			fileNameList.add(folderList[i].getName());
		}
		return fileNameList;
	}
	
	public HashMap<String, String> getMapFromFolder() throws FileNotFoundException{
		HashMap<String, String> fileFullNameMap = new HashMap<String, String>();
		ArrayList<String> fileNameList = getFileNameList();
		for (int i = 0; i < fileNameList.size(); i++) {
			try {
				fileFullNameMap.put(fileNameList.get(i).substring(0, fileNameList.get(i).indexOf('_')), fileNameList.get(i));
//				System.out.println(fileNameList.get(i).substring(0, fileNameList.get(i).indexOf('_')) + " " + fileNameList.get(i));
			} catch (Exception e) {
			}
		}
		return fileFullNameMap;
	}
	
	private void listFile() {
		folderList = (new File(path)).listFiles();
	}
}
