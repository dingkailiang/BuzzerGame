package ca.dingkaiualberta.buzzergame.Module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by dingkai on 10/4/15.
 */
public class Recorder {
    private static final String FILENAME = "BuzzerStats";
    public static Recorder instance;
    private ArrayList<Double> singleRecords;
    private ArrayList<Player> playerList;

    public static Recorder getInstance(){
        if (instance == null) instance = new Recorder();
        return instance;
    }

    private Recorder(){
        singleRecords = new ArrayList<Double>();
        playerList = new ArrayList<Player>();
        playerList.add(new Player(3));
        playerList.add(new Player(3));
        playerList.add(new Player(2));
        playerList.add(new Player(1));
    }

    public void clear(Context context){
        context.deleteFile(FILENAME);
        instance = new Recorder();
    }

    public void add(double recTime) {singleRecords.add(recTime);}

    public void add(int playerIndex,int mode) {playerList.get(playerIndex).win(mode);}

    public String toString(){
        String result = "";
        result += "Reaction Time Statistics: \n";
        result += "(Times)|All|Last10|Last100\n";
        result += "(Minimum)|";
        result += str(getMin(singleRecords.size())) + "|";
        result += str(getMin(10)) + "| ";
        result += str(getMin(100)) + "\n";
        result += "(Maximum)|";
        result += str(getMax(singleRecords.size())) + "|";
        result += str(getMax(10)) + "|";
        result += str(getMax(100)) + "\n";
        result += "(Average)|";
        result += str(getAvg(singleRecords.size())) + "|";
        result += str(getAvg(10)) + "|";
        result += str(getAvg(100)) + "\n";
        result += "(Median)|";
        result += str(getMed(singleRecords.size())) + "|";
        result += str(getMed(10)) + "|";
        result += str(getMed(100)) + "\n\n\n";
        result +=  "Buzzer Counts: \n";
        result += "(Mode)|2Player|3Player|4Player\n";
        for (int i = 0; i < 4; ++i){
            result += "(Player" + str(i+1) + ")|";
            for (int j = 2; j < 5; ++j) {
                result += str(playerList.get(i).getRecord(j)) + "|";
            }
            result += "\n";
        }
        return result;
    }

    private String str(double num){
        if (num == -1) return "None";
        return String.format("%.2f", num);
    }

    private String str(int num){
        if (num == -1) return "None";
        return String.format("%d",num);
    }
    private double getMin(int num){
        if (singleRecords.size() == 0) {return -1;}
        if (num > singleRecords.size()) num = singleRecords.size();
        double min = Float.POSITIVE_INFINITY;
        for (double record:singleRecords){
            if (record < min) min = record;
        }
        return min;
    }

    private double getMax(int num){
        if (singleRecords.size() == 0) {return -1;}
        if (num > singleRecords.size()) num = singleRecords.size();
        double max = Float.NEGATIVE_INFINITY;
        for (double record:singleRecords){
            if (record> max) max = record;
        }
        return max;
    }

    private double getAvg(int num){
        if (singleRecords.size() == 0) {return -1;}
        if (num > singleRecords.size()) num = singleRecords.size();
        double count = 0;
        for (double record:singleRecords) count += record;
        return count/num;
    }

    private double getMed(int num){
        if (singleRecords.size() == 0) {return -1;}
        if (num > singleRecords.size()) num = singleRecords.size();
        ArrayList<Double> newList = new ArrayList<Double>(singleRecords);
        Collections.sort(newList);
        int len = newList.size();
        if (len % 2 == 1) return newList.get((len - 1)/2);
        return (newList.get(len / 2 - 1) + newList.get(len / 2)) / 2.0;
    }

    // Following line based on https://github.com/joshua2ua/lonelyTwitter/tree/f15monday retrieved 2015-10-04
    public void save(Context context) {
        try {
            FileOutputStream out = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            Gson gson = new Gson();
            gson.toJson(instance, writer);
            writer.flush();
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    // Following line based on https://github.com/joshua2ua/lonelyTwitter/tree/f15monday retrieved 2015-10-04
    public void load(Context context){
        try {
            FileInputStream in = context.openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Gson gson = new Gson();
            Type recorderType = new TypeToken<Recorder>(){}.getType();
            instance = gson.fromJson(reader, recorderType);
        } catch (FileNotFoundException e) {
            // do nothing
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private class Player{
        private ArrayList<Integer> multiRecords;

        public Player(int num){
            multiRecords = new ArrayList<>();
            for (int i = 0; i < num; ++i) multiRecords.add(0);
        }

        public int getRecord(int mode){
            if((4 - mode) >= multiRecords.size()) return -1;
            return multiRecords.get(4 - mode);
        }

        public void win(int mode){
            multiRecords.set(4 - mode,multiRecords.get(4 - mode) + 1);
        }
    }
}



