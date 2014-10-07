import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 
public class ClassDemo4 {
    public static void main(String[] args) throws IOException {
//Путь до дерриктории с результатами
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please write pach to result\n");
        String pathResult = reader1.readLine()+"/";
        File openFoamResult = new File(pathResult);

        //Создаётся массив каждый эллемент которого будет имя файла
        String[] q = openFoamResult.list();

        for (int a = 0; a < q.length; a++) {
            String openFoamResultPatch = openFoamResult.getCanonicalPath();




        BufferedReader reader = new BufferedReader(new FileReader(pathResult+q[a]));
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);

        }
        reader.close();
        String[] array = lines.toArray(new String[lines.size()]);

            PrintWriter out = new PrintWriter(new FileWriter(pathResult+"ok.csv",true ));
        for (String s : array) {
            out.println(s);

        }




            out.close();
    }

}
}