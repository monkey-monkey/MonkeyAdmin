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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.*;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.Sides;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;


class PrintUtil {
    private String path;
    private PrinterJob job = PrinterJob.getPrinterJob();

    PrintUtil(String path) {
        this.path = path;
        setPrinter();
    }

    void print(){
        PDDocument doc = null;
        try {
            doc = PDDocument.load(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        job.setPrintable(new PDFPrintable(doc, Scaling.SCALE_TO_FIT));
//        job.setPageable(new PDFPageable(doc));
        PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
        attr.add(Sides.DUPLEX);
        attr.add(MediaSizeName.ISO_A4);
//        try {
//            job.print(attr);
//        } catch (PrinterException e) {
//            e.printStackTrace();
//        }finally {
//            if (doc != null){
//                try {
//                    doc.close();
//                } catch (IOException ignored) {
//                }
//            }
//        }
    }

    private void setPrinter(){
        String printerName[] = {"Samsung K7600 Series", "Brother MFC-7860DW Printer"};
        int index = -1, nameIndex;

//        System.out.println("First condition: " + path.contains("TEST"));
//        System.out.println("Second condition: " + (path.substring(path.indexOf('-', path.indexOf('-') + 1)).charAt(2) == 'T'));
//        System.out.println("Third condition: " + (path.substring(path.indexOf('-', path.indexOf('-') + 1)).charAt(1) != 'X'));
//        if (path.contains("TEST") || ((path.substring(path.indexOf('-', path.indexOf('-') + 1)).charAt(2) == 'T') && (path.substring(path.indexOf('-', path.indexOf('-') + 1)).charAt(1) != 'X'))){
//            nameIndex = 1;
//        }else {
//            nameIndex = 0;
//        }

        if (path.contains("TEST") || (path.charAt(getFirstNumIndexFromPath() - 1) == 'T')){
            nameIndex = 1;
        }else {
            nameIndex = 0;
        }

        PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
        attr.add(Sides.DUPLEX);
        attr.add(MediaSizeName.ISO_A4);

        PrintService[] service = PrintServiceLookup.lookupPrintServices(null, attr);
        for (int i = 0; i < service.length; i++){
            System.out.println("Printer List -> " + service[i].getName());
            if (service[i].getName().equals(printerName[nameIndex])){
                index = i;
            }
        }

        try {
            job.setPrintService(service[index]);
            System.out.println("Destination Printer -> " + job.getPrintService().getName());
        } catch (PrinterException e) {
            System.out.println("Printer not found");
        }
    }
    private int getFirstNumIndexFromPath(){
        String tempPath = path.substring(17);
        int index = tempPath.length();
        for (int i = 0; i < 10; i++) {
            try {
                int temp = tempPath.indexOf((char) (48 + i));
                if (temp == -1) continue;
                if (temp < index) index = temp;
            } catch (Exception ignored) {
            }
        }
        return index + 17;
    }
}
