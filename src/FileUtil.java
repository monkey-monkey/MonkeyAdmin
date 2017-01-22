/*
Copyright [2017] [Chutipon]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;


class FileUtil {

    private String path;
    private File[] folderList;

    /**
     * Constructor of class
     *
     * @param path path of
     */
    FileUtil(String path) {
        this.path = path;
    }

    private ArrayList<String> getFileNameList() {
        System.out.println("FileUtil.getFileNameList() is called");
        ArrayList<String> fileNameList = new ArrayList<>();
        listFile();
        for (File aFolderList : folderList) {
            fileNameList.add(aFolderList.getName());
        }
        return fileNameList;
    }

    HashMap<String, String> getMapFullNameFromFolder() throws FileNotFoundException {
        HashMap<String, String> fileFullNameMap = new HashMap<>();
        ArrayList<String> fileNameList = getFileNameList();
        for (String aFileNameList : fileNameList) {
            try {
                fileFullNameMap.put(aFileNameList.substring(0, aFileNameList.indexOf('_')), aFileNameList);
            } catch (Exception ignored) {
            }
        }
        return fileFullNameMap;
    }

    /**
     * Get list of level in subject
     *
     * @return ArrayList of all available name in subject
     * @throws FileNotFoundException error occur from invalid file location
     */
    ArrayList<String> getListNameFromFolder() throws FileNotFoundException {
        ArrayList<String> folderNameList = new ArrayList<>();
        ArrayList<String> fileNameList = getFileNameList();
        for (String aFileNameList : fileNameList) {
            try {
//				System.out.println(fileNameList.get(i).substring(fileNameList.get(i).indexOf('-') + 1, fileNameList.get(i).indexOf('_')));
                folderNameList.add(
                        aFileNameList.substring(aFileNameList.indexOf('-') + 1,
                                aFileNameList.indexOf('_'))
                );
            } catch (Exception ignored) {
            }
        }
        return folderNameList;
    }

    /**
     * Get sub-level name from folder e.g. A, B, C
     *
     * @return Array list of sub-level name
     */
    ArrayList<String> getSubListNameFromFolder() {
        ArrayList<String> folderNameSubList = new ArrayList<>();
        ArrayList<String> fileNameList = getFileNameList();
        for (String aFileNameList : fileNameList) {
            int index = getIndexOfNum(aFileNameList);
            if (!folderNameSubList.contains("" + aFileNameList.charAt(index - 1)) && aFileNameList.charAt(index - 1) != 'V') {
                folderNameSubList.add("" + aFileNameList.charAt(index - 1));
            }
        }
        return folderNameSubList;
    }

    /**
     * Get number list of folder indicate using key e.g. 'B' -> 01, 02, 03
     *
     * @param key Indicator of the level wanted to get the list
     * @return Array list of number of level in folder
     */
    ArrayList<String> getNumberListFromFolder(Character key) {
        ArrayList<String> folderNumberList = new ArrayList<>();
        ArrayList<String> fileNameList = getFileNameList();
        for (String aFileNameList : fileNameList) {
            int indexOfKey = getIndexOfNum(aFileNameList);
            if (!(folderNumberList.contains(aFileNameList.substring(indexOfKey, indexOfKey + 2)) &&
                    aFileNameList.charAt(indexOfKey - 1) != 'V') &&
                    aFileNameList.charAt(indexOfKey - 1) == key) {
                folderNumberList.add(aFileNameList.substring(indexOfKey, indexOfKey + 2));
            }
        }
        return folderNumberList;
    }

    /**
     * Get most recent rev of sheet
     *
     * @param levelName name of level to get most recent revision
     * @return most recent rev in format (REVx_x) e.g. (REV1_0)
     */
    String getRev(String levelName) {
        ArrayList<String> commonFolderName = new ArrayList<>();
        listFile();
        System.out.println("FileUtil.getRev() >>> for each loop times = " + folderList.length);
        for (File aFolderList : folderList) {
            if (aFolderList.getName().substring(0, aFolderList.getName().indexOf('(')).equals(levelName)){
                commonFolderName.add(aFolderList.getName().substring(aFolderList.getName().indexOf('(')));
                System.out.println("FileUtil.getRev() >>> common folder add " + aFolderList.getName().substring(aFolderList.getName().indexOf('(')));
            }
        }
        int index = -1;
        double revVal = 0.0;
        for (int i = 0; i < commonFolderName.size(); i++) {
            double tempVal = Double.parseDouble(
                    commonFolderName.get(i).charAt(commonFolderName.get(i).indexOf('V') + 1) +
                            "." +
                            commonFolderName.get(i).charAt(commonFolderName.get(i).indexOf('_') + 1)
            );
            if (tempVal > revVal) {
                index = i;
                revVal = tempVal;
            }
        }
        return commonFolderName.get(index);
    }

    /**
     * call method copyFileUsingFileChannels for copying file using file channel
     *
     * @param destinationPath location of file to be copied to
     * @return boolean if file is copy success
     */
    boolean copy(String destinationPath) {
        try {
            copyFileUsingFileChannels(new File(path), new File(destinationPath));
            CheckExcessFile addFileLog = new CheckExcessFile(destinationPath.substring(0, destinationPath.lastIndexOf('\\')) + "\\");
            if (addFileLog.getHasLog())
                addFileLog.addFileToLog(destinationPath.substring(destinationPath.lastIndexOf('\\') + 1));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void copyFileUsingFileChannels(File source, File dest) throws IOException {
        try (FileChannel inputChannel = inputExtracted(source).getChannel();
             FileChannel outputChannel = outputExtracted(dest).getChannel()) {
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        }
    }

    private FileOutputStream outputExtracted(File dest) throws FileNotFoundException {
        return new FileOutputStream(dest);
    }

    private FileInputStream inputExtracted(File source) throws FileNotFoundException {
        return new FileInputStream(source);
    }

    /**
     * List file if folder and store in array
     */
    private void listFile() {
        folderList = (new File(path)).listFiles();
    }

    /**
     * Get index of first number in level name
     *
     * @param levelName input for the method
     * @return index shows the first number located in the string
     */
    private int getIndexOfNum(String levelName) {
        int index = levelName.length();
        for (int i = 0; i < 10; i++) {
            try {
                int temp = levelName.indexOf((char) (48 + i));
                if (temp == -1) continue;
                if (temp < index) index = temp;
            } catch (Exception ignored) {
            }
        }
        return index;
    }
}
