package json_example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.Constant;
import util.JSONHandler;

/**
 * Print all values of JSON values
 * @author Richa
 */
public class JsonSimpleReader {

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(Constant.JSON_FILE_PATH);

        Object jsonObj = parser.parse(reader);

        JSONObject jsonObject = (JSONObject) jsonObj;
        Set entries = jsonObject.entrySet();
//
        Iterator itr = entries.iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            String key = (String) entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof java.lang.String) {
                System.out.println("String : " + obj);
            } else if (obj instanceof java.lang.Long) {
                System.out.println("Long : " + obj);
            } else if (obj instanceof org.json.simple.JSONObject) {
                JSONObject cast = (org.json.simple.JSONObject) obj;
                Set entries1 = cast.entrySet();
                Iterator itr1 = entries1.iterator();
                while (itr1.hasNext()) {
                    Map.Entry entry1 = (Map.Entry) itr1.next();
                    String key1 = (String) entry1.getKey();
                    Object obj1 = entry1.getValue();
                    System.out.println(key + " : " + key1 + " --- " + obj1);
                }

            } else if (obj instanceof org.json.simple.JSONArray) {
                JSONArray cast = (org.json.simple.JSONArray) obj;
                Iterator itr1 = cast.iterator();
                System.out.println("Array [ ");

                while (itr1.hasNext()) {
                    Object str = itr1.next();
                    if (str instanceof org.json.simple.JSONObject) {
                        JSONObject cast2 = (org.json.simple.JSONObject) str;
                        Set entries1 = cast2.entrySet();
                        Iterator itr2 = entries1.iterator();
                        while (itr2.hasNext()) {
                            Map.Entry entry1 = (Map.Entry) itr2.next();
                            String key1 = (String) entry1.getKey();
                            Object obj1 = entry1.getValue();
                            System.out.println(key + " : " + key1 + " --- " + obj1);
                        }
                    } else {
                        System.out.println(str + " , ");
                    }
                }
                System.out.println(" ] ");
            }
//            System.out.println(entry.getKey() + " " + entry.getValue().getClass());

        }
        String name = (String) jsonObject.get("name");
        System.out.println("Name = " + name);

        long age = (Long) jsonObject.get("age");
        System.out.println("Age = " + age);

        JSONArray cities = (JSONArray) jsonObject.get("cities");

        @SuppressWarnings("unchecked")
        Iterator<String> it = cities.iterator();
        while (it.hasNext()) {
            System.out.println("City = " + it.next());
        }
        reader.close();
    }

}
