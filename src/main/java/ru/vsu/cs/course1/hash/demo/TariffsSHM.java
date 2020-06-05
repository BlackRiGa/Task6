package main.java.ru.vsu.cs.course1.hash.demo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import main.java.ru.vsu.cs.course1.hash.SimpleHashMap;
import main.java.ru.vsu.cs.course1.hash.Direction;

public class TariffsSHM {

    public static final String ERROR_NO_DATA = "Error. No data.\r\n";
    public static final String ERROR_NOT_SAVE = "Error. Unable to save file.\r\n";
    public static final String EMPTY = "HashMap is empty";
    public static final String ERROR_NO_PREFIX = "No suitable prefix";
    public static final String ERROR_IN_SECONDS = "The field 'duration of the conversation' must contain a digit";
    public static final String PARAGRAPH = "\r\n";
    public static final String FINAL_MESSAGE = "Success. File saved.";

    private SimpleHashMap<String, Direction> simpleHashMap;
    
    public void TariffsSHM(){
    }

    public void loadUsingSimpleHashMap(String data) {
        SimpleHashMap simpleHM = new SimpleHashMap(100);
        Scanner scanner = new Scanner(data);
        Direction el;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner scannerLine = new Scanner(line);
            StringBuilder brr = new StringBuilder();
            int check = 0;
            while (scannerLine.hasNext()) {
                check++;
                brr.append(scannerLine.next());
            }
            if (check == 3) {
                scannerLine = new Scanner(line);
                String key = scannerLine.next();
                el = new Direction();
                el.setName(scannerLine.next());
                if (scannerLine.hasNextInt()) {
                    el.setCostPerMinute(scannerLine.nextInt());
                } else {
                    el.setCostPerMinute(0);
                }
                simpleHM.put(key, el);
            }
        }
        this.simpleHashMap = simpleHM;
    }

    public String withdrawUsingSimpleHashMap() {
        if (simpleHashMap.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Map.Entry<String, Direction> firstEntry = simpleHashMap.entrySet().iterator().next();
        Map.Entry<String, Direction>[] entries = simpleHashMap.entrySet().stream().toArray(
                size -> (Map.Entry<String, Direction>[]) Array.newInstance(firstEntry.getClass(), size)
        );
        if (firstEntry.getKey() instanceof Comparable) {
            Arrays.sort(entries, (a, b) -> ((Comparable) a.getKey()).compareTo(b.getKey()));
        }
        for (Map.Entry<String, Direction> kv : entries) {
            if (sb.length() > 0) {
                sb.append("\r\n");
            }
            sb.append(kv.getKey()).append(" ").append(kv.getValue().getName()).append(" ").append(kv.getValue().getCostPerMinute());
        }
        return sb.toString();
    }

    public int calculateCostUsingSimpleHashMap(String number, int seconds) {
        if (seconds < 7) {
            return 0;
        }
        int length = 0;
        Direction direction = null;
        for (Map.Entry<String, Direction> pair : simpleHashMap.entrySet()) {
            String prefix = pair.getKey();
            if (number.indexOf(prefix) == 0 && prefix.length() > length) {
                length = prefix.length();
                direction = pair.getValue();
            }
        }
        if (direction == null) {
            return 0;
        }
        return (int) (Math.ceil((double) seconds / 60)) * direction.getCostPerMinute();
    }
   
    public boolean add(String key, String name, int cost) {
        if (!simpleHashMap.containsKey(key)) {
            Direction el = new Direction();
            el.setName(name);
            el.setCostPerMinute(cost);
            simpleHashMap.put(key, el);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean remove(String key){
        if (simpleHashMap.containsKey(key)) {
            for (Map.Entry<String, Direction> pair : simpleHashMap.entrySet()) {
                String check = pair.getKey();
                if (key.equals(check)) {
                    simpleHashMap.remove(check);
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean change(String key, String name, int cost){
        if (simpleHashMap.containsKey(key)) {
            remove(key);
            add(key, name, cost);
            return true;
        }else{
            return false;
        }
    }

    
}
