import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Filer {
    File file = new File("textFiler.txt");
    String fileName = "textFiler.txt";
    BufferedWriter writer;

    public Filer () {
        try {
            if (file.exists()) {
                //System.out.println("File already exists - " + file.getName());
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("File created");
            }

             writer = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void writeToFile (String[] tasks){

        //System.out.println(Arrays.toString(tasks));
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));
            if (writer != null) {
                for (int i = 0; i < tasks.length; i++) {
                    writer.write(tasks[i]);
                    writer.newLine();
                }
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile()
    {

        try{

            FileWriter fw = new FileWriter(fileName, false);
            PrintWriter pw = new PrintWriter(fw, false);

            pw.flush();
            pw.close();
            fw.close();

        }catch(Exception exception){
            exception.printStackTrace();

        }

    }


    public String[] readFromFile () {
        ArrayList<String> infoRead = new ArrayList<String>();
        int indexOfComma;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
               indexOfComma = line.indexOf(",");
               line = line.substring(indexOfComma+1);
               infoRead.add(line);

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        String[] info = new String[infoRead.size()];
        for (int i = 0; i < infoRead.size(); i++) {
            info[i] = infoRead.get(i);
        }
        return info;
    }

    public boolean[] getDoneTasks () {
        ArrayList<Boolean> infoRead = new ArrayList<Boolean>();
        int indexOfComma;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                indexOfComma = line.indexOf(",");
                line = line.substring(0, indexOfComma+1);

                if (line.equals("true,")) {
                    infoRead.add(true);
                } else {
                    infoRead.add(false);
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        boolean[] info = new boolean[infoRead.size()];
        for (int i = 0; i < infoRead.size(); i++) {

            info[i] = infoRead.get(i);
        }
        return info;
    }



}
