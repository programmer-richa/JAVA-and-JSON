package json_example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.Constant;

/**
 * Print Entries of JSON File
 * @author Richa
 */
public class PrintAllEntriesJSON {

    /**
     * @param args the command line arguments
     * @throws FileNotFoundException file not found
     * @throws IOException I/O exception
     * @throws ParseException JSON parse exception
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        FileReader reader = new FileReader(Constant.JSON_FILE_PATH);
        JSONParser parser = new JSONParser();
        Object jsonObj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject) jsonObj;
        Set entries = jsonObject.entrySet();

        Iterator itr = entries.iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            String key = (String) entry.getKey();
            Object obj = entry.getValue();
            System.out.println(key + " :   " + obj + "\t" + (obj == null ? "Null" : obj.getClass()));
        }
    }

}
