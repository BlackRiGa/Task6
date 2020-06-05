package main.java.ru.vsu.cs.course1.hash.demo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import main.java.ru.vsu.cs.course1.hash.Direction;

public class Tariffs {
    
    private HashMap<String, Direction> hashMap;
    
    public void Tariffs(){
    }
    
    public void load(String data) {
        HashMap hashM = new HashMap(100);
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
                hashM.put(key, el);
            }
        }
        this.hashMap = hashM;
    }

    public String withdraw() {
        if (hashMap.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Map.Entry<String, Direction> firstEntry = hashMap.entrySet().iterator().next();
        Map.Entry<String, Direction>[] entries = hashMap.entrySet().stream().toArray(
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

    public int calculateCost(String number, int seconds) {
        if (seconds < 7) {
            return 0;
        }
        int length = 0;
        Direction direction = null;
        for (Map.Entry<String, Direction> pair : hashMap.entrySet()) {
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
        if (!hashMap.containsKey(key)) {
            Direction el = new Direction();
            el.setName(name);
            el.setCostPerMinute(cost);
            hashMap.put(key, el);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean remove(String key){
        if (hashMap.containsKey(key)) {
            for (Map.Entry<String, Direction> pair : hashMap.entrySet()) {
                String check = pair.getKey();
                if (key.equals(check)) {
                    hashMap.remove(check);
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean change(String key, String name, int cost){
        if (hashMap.containsKey(key)) {
            remove(key);
            add(key, name, cost);
            return true;
        }else{
            return false;
        }
    }
}
