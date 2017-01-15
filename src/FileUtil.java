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
			} catch (Exception e) {
			}
		}
		return fileFullNameMap;
	}
	
	/**
	 * Get most recent rev of sheet
	 * @param levelName 
	 * @return most recent rev in format (REVx_x) e.g. (REV1_0)
	 */
	public String getRev(String levelName) {
		ArrayList<String> commonFolderName = new ArrayList<String>();
		listFile();
		for (int i = 0; i < folderList.length; i++) {
			if(folderList[i].getName().substring(0, folderList[i].getName().indexOf('(')).equals(levelName)) commonFolderName.add(folderList[i].getName().substring(folderList[i].getName().indexOf('(')));
		}
		int index = -1;
		double revVal = 0.0;
		for (int i = 0; i < commonFolderName.size(); i++) {
			double tempVal = Double.parseDouble(commonFolderName.get(i).charAt(commonFolderName.get(i).indexOf('V') + 1) + "." + commonFolderName.get(i).charAt(commonFolderName.get(i).indexOf('_') + 1));
			if (tempVal > revVal) index = i;
		}
		return commonFolderName.get(index);
	}
	
	private void listFile() {
		folderList = (new File(path)).listFiles();
	}
}
