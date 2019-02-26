package braintrain.model.user;

import java.time.Instant;
import java.util.List;
import java.io.FileWriter;
import java.io.FileReader;

public class Userdata {
    private static String csvFile = "c:\\Users\\progress.csv";
    private String name;
    private List<CardData> CardData;

    public static void ExportUserData() throws java.io.IOException{
        /** TODO : Export CSV File here(What is the file format?)
         *  CSV.writeLine(writer, INSERT_CSVFILEDATAHERE)
         *  writer.flush();
         *  write.close();
         */
        FileWriter writer = new FileWriter(csvFile);

        /**writer.write(CSV FILE TO WRITE HERE)
         *
         */
        writer.flush();
        writer.close();
    }

    public void ImportUserData() throws java.io.IOException {
        /** TODO: Import CSV File here(What is the file format?)
         *
         */
        FileReader reader = new FileReader(csvFile);

    }

    public void GetCardData(List<CardData> cardData) {
        /** TODO: GetCardData from Evan
         *
         */
    }

    public class CardData {
        private int hashCode;
        private int numAttempts;
        private Instant srsDueDate;
    }
}