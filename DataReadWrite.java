import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReadWrite implements Medium {
    File file = new File("baza.txt");

    void backup() {
        try {
            Backup.saveWithBackup("baza.txt");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void write(String user, long dataId, String data) throws Exception {
        String newLine = dataId + "|" + user + "|" + data;
        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(newLine);
            this.backup();
        }
    }

    @Override
    public String read(String user, long dataId) throws Exception {
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (Long.valueOf(line.split("|")[1]) == dataId) {
                return line;
            }
        }
        sc.close();
        return null;
    }

    @Override
    public List<String> read(String user) throws Exception {
        List<String> transactions = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] op = line.split("|");
            if(op[0].equals(user)){
                transactions.add(op[2]);
            }
        }

        return transactions;
    }
}
