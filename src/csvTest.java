import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class csvTest {
    public static void main(String[] args) throws IOException {

//***Удаление лишних файлов из папки
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please write pach to result of OpenFOAM\n");
        String pathOpenFoam = reader.readLine()+"/";
        System.out.print("Please write pach to result\n");
        String pathResult = reader.readLine()+"/";

        //**
        File file = new File(pathResult);
        // Выяснянем, папка ли это.
        if (file.isDirectory()) {
            // Получаем все файлы и папки.
            String[] s = file.list();
            for (int i = 0; i < s.length; i++) {
                String patch = file.getCanonicalPath();
                File listFile = new File(patch + "/" + s[i]);
                listFile.delete();
            }

            File openFoamResult = new File(pathOpenFoam);
            String[] q = openFoamResult.list();
            for (int a = 0; a < q.length; a++) {
                String openFoamResultPatch = openFoamResult.getCanonicalPath();

//*** Чтение файлов по очередно
                try {


                        CsvReader products = new CsvReader(pathOpenFoam + q[a]);
                        products.readHeaders();
                        //PrintWriter out = new PrintWriter(new FileWriter("/home/asadula/result/res20.csv"));
                        while (products.readRecord()) {
                            String productID = products.get("Points:0");
                            String productName = products.get("Points:1");
                            //System.out.println(productID + ":" + productName);
                            //String outputFile = "/home/asadula/result/point" + i + ".csv";
                            String outputFile = pathResult + q[a];
                            // before we open the file check to see if it already exists
                            boolean alreadyExists = new File(outputFile).exists();
                            try {
                                // use FileWriter constructor that specifies open for appending
                                CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
                                // if the file didn't already exist then we need to write out the header line
                                if (!alreadyExists) {

                                }
                                // else assume that the file already has the correct header line
                                // write out a few records
                                csvOutput.write(productID);
                                csvOutput.write(productName);
                                csvOutput.endRecord();


                                //**********************************
                                csvOutput.close();

                            } catch (IOException e) {

                                e.printStackTrace();
                            }

                        }

                        products.close();


//                ***************************

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
       }

    }

    }
