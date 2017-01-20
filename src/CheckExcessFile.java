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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CheckExcessFile {
    private String path = Index.VDO_LOCATION;
    private File folder;
    private ArrayList<File> fileList = new ArrayList<>();
    private ArrayList<String> fileNameList = new ArrayList<>();
    private ArrayList<String[]> fileLog = new ArrayList<>();

    public CheckExcessFile(String id) {
        path += id + "\\";
        folder = new File(path);
        listFile();
        getHistoryFromFile();
        listFileName();
    }

    public ArrayList<String[]> getFileLog() {
        return fileLog;
    }

    private void listFile() {
        File temp[] = folder.listFiles();
        if (temp != null) {
            Collections.addAll(fileList, temp);
        }
    }

    private void listFileName() {
        for (File aFileList : fileList) {
            fileNameList.add(aFileList.getName());
        }
    }

    private void getHistoryFromFile() {
        File storageLog = new File(path + "log.csv");
        ArrayList<String> temp = new ArrayList<>();
        try {
            Scanner logReader = new Scanner(storageLog);
            while (logReader.hasNextLine()) {
                temp.add(logReader.next());
            }
            for (String aTemp : temp) {
                fileLog.add(new String[]{aTemp.substring(0, aTemp.indexOf(',')), aTemp.substring(aTemp.indexOf(',') + 1, aTemp.length())});
            }
        } catch (FileNotFoundException ignored) {
        }
    }
}
