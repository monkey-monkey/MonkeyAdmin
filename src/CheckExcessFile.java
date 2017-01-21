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

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
        getLogFromFile();
        listFileName();
    }

    public ArrayList<String[]> getFileLog() {
        getLogFromFile();
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

    private void getLogFromFile() {
        File storageLog = new File(path + "log.csv");
        ArrayList<String> temp = new ArrayList<>();
        Scanner logReader = null;
        try {
            logReader = new Scanner(storageLog);
        } catch (FileNotFoundException e) {
            logGenerator();
        }
        while (logReader != null && logReader.hasNextLine()) {
            temp.add(logReader.next());
        }
        for (String aTemp : temp) {
            fileLog.add(new String[]{
                    aTemp.substring(0, aTemp.indexOf(',')),
                    aTemp.substring(aTemp.indexOf(',') + 1, aTemp.length())
            });
        }
    }

    private void removeLog() throws IOException {
        String tempData = "";
        Scanner reader = new Scanner(new File(path + "log.csv"));
        deleteFile(reader.nextLine());
        while (reader.hasNextLine()) {
            tempData += reader.nextLine();
            if (reader.hasNextLine()) {
                tempData += "\n";
            }
        }
        reader.close();
        File tempFile = new File(path + "log.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, false));
        writer.write(tempData);
        writer.close();
    }

    private void deleteFile(String fileName) {
        File targetFile = new File(path + fileName.substring(0, fileName.indexOf(',')));
        if (targetFile.exists()) targetFile.delete();
        System.out.println(fileName);
    }

    private void logGenerator() {
        File fileList[] = folder.listFiles();
        String logStore = "";
        for (int i = 0; i < (fileList != null ? fileList.length : 0); i++) {
            if (fileList[i].getName().equals("log.csv")) continue;
            logStore += fileList[i].getName() + ",null";
            if (i != fileList.length - 1) logStore += "\n";
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path + "log.csv", true));
            writer.write(logStore);
        } catch (IOException ignored) {
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException ignored) {
            }
        }
    }

    private boolean isExcess() {
        return fileLog.size() > 5;
    }

    public void addFileToLog(String fileName) {
        getLogFromFile();
        if (isExcess()) try {
            removeLog();
        } catch (IOException ignored) {
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date dateObj = new Date();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path + "log.csv", true));
            writer.write("\n" + fileName + "," + format.format(dateObj));
        } catch (IOException ignored) {
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static void main(String[] args) {
        CheckExcessFile temp = new CheckExcessFile("159991");
//        temp.addFileToLog("test07.txt");
    }
}
