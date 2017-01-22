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

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PrintUtil {
    private String path;

    PrintUtil(String path) {
        this.path = path;
    }

    public void print(){
        String key = "Samsung SCX-8123 8128 Series";
        FileInputStream targetFile = null;
        try {
            targetFile = new FileInputStream(path);
        } catch (FileNotFoundException ignored) {
        }
        if (targetFile == null) return;

        DocFlavor format = DocFlavor.INPUT_STREAM.AUTOSENSE;

        Doc doc = new SimpleDoc(targetFile, format, null);

        PrintRequestAttributeSet asset = new HashPrintRequestAttributeSet();
        asset.add(Sides.DUPLEX);

        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, asset);

        System.out.println("PrintUtil.print() >>> Printer list:");
        int indexKey = -1;
        for (int i = 0; i < services.length; i++){
            System.out.println("\t" + services[i].getName());
            if (services[i].getName().equals(key)) indexKey = i;
        }
        System.out.println("-----------------------------------------------------------");

        System.out.println("Printer: " + key + " >>> index = " + indexKey);
        if (indexKey != -1){
            DocPrintJob job = services[indexKey].createPrintJob();
            System.out.println("Job Created");
            System.out.print("Print status: ");
            try {
                job.print(doc, asset);
                System.out.println("Print success");
            } catch (PrintException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PrintUtil temp = new PrintUtil("X:\\MATH_DB\\MJ\\MJ-B_NUMBERTHEORY\\MJ-BB03(REV1_0)\\MJ-BB03COVER.pdf");
        temp.print();
    }
}
