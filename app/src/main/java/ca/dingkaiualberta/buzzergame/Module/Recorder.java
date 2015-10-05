package ca.dingkaiualberta.buzzergame.Module;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by dingkai on 10/4/15.
 */
public class Recorder {
    private static final String FILENAME = "BuzzerStats";
    static private ArrayList<Double> singleRecord = new ArrayList<Double>();
    static private int[][] multiRecord ={
            {0,0},
            {0,0,0},
            {0,0,0,0}
    };

    static private String report(){
        return null;
    }
    static public void add(double recTime){
        singleRecord.add(recTime);
        if (singleRecord.size()>100){
            singleRecord.remove(0);
        }
    }

    static public void save(Context context) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(context.openFileOutput(FILENAME, context.MODE_PRIVATE));
            output.writeObject(report());
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
