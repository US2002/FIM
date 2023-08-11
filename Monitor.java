import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.sql.Timestamp;
import java.util.Date;

public class Monitor {
    public HashMap<String, String> snapshotHashMap = new HashMap<String, String>();
    public HashMap<String, String> currentHashMap = new HashMap<String, String>();
    public String root = "";
    String diff = "";

    public Monitor(String root) {
        this.root = root;
    }

    public static void main(String args[]) {
        Monitor m = new Monitor(""); // set root directory here
        m.check();
    }

    /*
     * helper function that reads data from snapshot file and scans current dir.
     * Writes data to hash maps
     * input: snapshot file
     * output: writes old and current state to hash map
     */

    public void check() {
        snapshotHashMap.clear();
        currentHashMap.clear();

        diff += "Checking with the file 'snapshot'";
        // read snapshot file
        File snapshotFile = new File("snapshot");
        if (snapshotFile.exists()) {
            try (BufferedReader b = new BufferedReader(new FileReader(snapshotFile))) {
                String row;
                while ((row = b.readLine()) != null) {
                    // read line by line and delimit by ,
                    String[] split = row.split(",");
                    snapshotHashMap.put(split[0], split[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // scan current directory
            FileScan s = new FileScan(this.root);
            String newData = s.scan(s.getRoot(), false);
            try (BufferedReader b = new BufferedReader(new StringReader(newData))) {
                String row;
                while ((row = b.readLine()) != null) {
                    String[] split = row.split(",");
                    currentHashMap.put(split[0], split[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("No snapshot file exists");
            diff += timeStamp() + " : ";
            diff += "No snapshot file Found\n";
            output();
        }

        this.compare();

    }

    /*
     * compares the old state of dir with new state by comparing data stored in hash
     * tables
     * input: currentHashMap and snapshotHashMap
     * output: writes report.txt to project directory
     */

    public void compare() {
        diff = "";
        Iterator<String> itr1 = snapshotHashMap.keySet().iterator();
        while (itr1.hasNext()) {
            // get directory/file absolute path
            String s1 = itr1.next();
            if (currentHashMap.containsKey(s1)) {
                // both hash maps contain same file or dir
                if (!snapshotHashMap.get(s1).equals(currentHashMap.get(s1))) {
                    // file has been modified
                    diff += timeStamp() + " : ";
                    diff += "Edited: " + s1 + ", " + snapshotHashMap.get(s1) + " -> " + currentHashMap.get(s1) + "\n";
                }
                // remove matches from both hash maps
                itr1.remove();
                currentHashMap.remove(s1);
            }
        }

        // add remaining items to log
        Iterator<String> itr2 = snapshotHashMap.keySet().iterator();
        while (itr2.hasNext()) {
            String s2 = itr2.next();
            diff += timeStamp() + " : ";
            diff += "Deleted: " + s2 + ", " + snapshotHashMap.get(s2) + "\n";
        }

        Iterator<String> itr3 = currentHashMap.keySet().iterator();
        while (itr3.hasNext()) {
            String s3 = itr3.next();
            diff += timeStamp() + " : ";
            diff += "Added: " + s3 + ", " + currentHashMap.get(s3) + "\n";
        }
        output();

    }

    public void output() {
        File out = new File("report.txt");
        try (FileOutputStream fout = new FileOutputStream(out, true)) {
            // create file if there is none
            if (!out.exists()) {
                out.createNewFile();
            }

            byte[] dataBytes = diff.getBytes();

            fout.write(dataBytes);
            fout.flush();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String timeStamp() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println(formatter.format(ts));
        return formatter.format(ts);
    }

    public void ss() {
        diff += timeStamp() + " : ";
        diff += "SnapShot Created!!";
        output();
        diff = "";
    }
}
