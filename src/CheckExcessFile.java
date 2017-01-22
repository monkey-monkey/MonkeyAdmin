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

    /**
     * Constructor of class CheckExcessFile
     * @param path destination path of student
     */
    public CheckExcessFile(String path) {
        this.path = path;
        folder = new File(path);
        listFile();
        getLogFromFile();
        listFileName();
    }

    /**
     * Getter of FileLog
     * @return ArrayList contain array of string FileLog
     */
    public ArrayList<String[]> getFileLog() {
        return fileLog;
    }

    /**
     * Add list of file array to fileList array list
     */
    private void listFile() {
        File temp[] = folder.listFiles();
        if (temp != null) {
            Collections.addAll(fileList, temp);
        }
        System.out.println("CheckExcessFile.listFile() >>> CheckExcessFile.fileList: ArrayList<File> -> CheckExcessFile.fileList.size() = " + fileList.size());
    }

    /**
     * Get name from fileList store in fileNameList
     */
    private void listFileName() {
        for (File aFileList : fileList) {
            fileNameList.add(aFileList.getName());
        }
        System.out.println("CheckExcessFile.listFileName() >>> CheckExcessFile.fileNameList: ArrayList<String> -> CheckExcessFile.fileNameList.size() = " + fileNameList.size());
    }

    /**
     * Read file log from file
     */
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
        System.out.println("CheckExcessFile.getLogFromFile() >>> CheckExcessFile.fileLog: ArrayList<String[]> -> CheckExcessFile.fileLog.size() = " + fileLog.size());
    }

    /**
     * Delete file log that excess capacity of folder
     * @throws IOException cause from log file not found or unable to write log to file
     */
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
        System.out.println("CheckExcessFile.removeLog() >>> Data write on file log:\n" + tempData);
    }

    /**
     * Delete target file from folder
     * @param fileLog log of file to be delete read from file log
     */
    private void deleteFile(String fileLog) {
        File targetFile = new File(path + fileLog.substring(0, fileLog.indexOf(',')));
        if (targetFile.exists()) targetFile.delete();
        System.out.println("CheckExcessFile.deleteFile() >>> File to delete = " + fileLog.substring(0, fileLog.indexOf(',')));
    }

    /**
     * Called when log file is not exist generate log from current file in folder
     */
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
        System.out.println("CheckExcessFile.logGenerator() >>> Generated log:\n" + logStore);
    }

    /**
     * Check if the file in folder is excess the capacity
     * @return boolean show status of file in folder
     */
    private boolean isExcess() {
        System.out.println("CheckExcessFile.isExcess() -> " + (fileLog.size() > 5));
        return fileLog.size() > 5;
    }

    /**
     * Add file to file log
     * @param fileName file name of file to be add
     */
    public void addFileToLog(String fileName) {
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
        System.out.println("CheckExcessFile.addFileToLog() >>> Write data:\n" + fileName + "," + format.format(dateObj));
    }

    public static void main(String[] args) {
        String destinationPath = "\\\\192.168.1.150\\vdo\\159991\\MJ-BB01VDO.mp4";
        System.out.println(destinationPath.substring(0, destinationPath.lastIndexOf('\\')) + "\\");
        System.out.println(destinationPath.substring(destinationPath.lastIndexOf('\\') + 1));
        CheckExcessFile addFileLog = new CheckExcessFile(destinationPath.substring(0, destinationPath.lastIndexOf('\\')) + "\\");
        addFileLog.addFileToLog(destinationPath.substring(destinationPath.lastIndexOf('\\') + 1));
    }
}
