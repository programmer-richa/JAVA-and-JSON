package json_example;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.JSONHandler;

/**
 * Write Json data to a file
 *
 * @author Richa
 */
public class JsonSimpleWriter {

    /**
     * @param args the command line arguments
     * @throws FileNotFoundException file not found
     * @throws IOException I/O exception
     * @throws ParseException JSON parse exception
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        JSONObject obj = new JSONObject();

        obj.put("name", "Pankaj Kumar");
        obj.put("age", new Integer(32));

        JSONArray cities = new JSONArray();
        cities.add("New York");
        cities.add("Bangalore");
        cities.add("San Francisco");

        obj.put("cities", cities);

        JSONHandler.writeDataToFile("src//json_example//data.json", obj);
//        try {
//
//            FileWriter file = new FileWriter("src//json_example//data.json");
//            file.write(obj.toJSONString());
//            file.flush();
//            file.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.print(obj.toJSONString());
    }

}
