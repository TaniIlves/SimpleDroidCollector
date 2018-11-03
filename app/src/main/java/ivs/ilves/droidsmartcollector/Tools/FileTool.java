package ivs.ilves.droidsmartcollector.Tools;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;

import ivs.ilves.droidsmartcollector.MainActivity;

import static android.content.Context.MODE_PRIVATE;


public class FileTool {

    private String fileName;
    //private String fileData;
    private File dir = new File(".");


    public static void readFile(Context context, String fileName){



        try {

            //
            // OPEN: Stream for reading file
            //
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(fileName)));

            String fileData = "";

            //
            // Output: File content (FOR DEBUG ONLY)
            //
            while ((fileData = br.readLine()) != null) {
                Log.d("MyLOG.DEBUG", "File content: " + fileData);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return fileData;
    }

    public static void writeFile(Context context, String fileName, String fileData) {

        try {

            // OPEN: Stream for writing file
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(fileName, MODE_PRIVATE)));

            // WRITE: File data
            bw.write(fileData);

            // CLOSE: Stream
            bw.close();

            Log.d("MyLOG:DEBUG", "Файл записан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
