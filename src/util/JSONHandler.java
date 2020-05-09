package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Util Class to handle JSON data
 *
 * @author Richa
 */
public class JSONHandler {

    private static final String LINE_SEPARATOR = "\n";
    private static final String KEY_SEPARATOR = " : ";
    private static final String ARRAY_SEPARATOR = " \t\t ";

    private JSONHandler() {
    }

    /**
     * @param filePath string path of the json file
     * @return JSONObject representing json data
     *      @throws FileNotFoundException file not found
     * @throws IOException I/O exception
     * @throws ParseException JSON parse exception
     */
    public static JSONObject readDataFromFile(String filePath) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(filePath);
        Object jsonObj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject) jsonObj;
        reader.close();
        return jsonObject;
    }

    /**
     * @param filePath string path of the json file
     * @param obj representing json data
     * @return true if data is written to file
     * @throws FileNotFoundException file not found
     * @throws IOException I/O exception
     * @throws ParseException JSON parse exception
     */
    public static boolean writeDataToFile(String filePath, JSONObject obj) throws FileNotFoundException, IOException, ParseException {
        FileWriter file = new FileWriter(filePath);
        file.write(obj.toJSONString());
        file.flush();
        file.close();
        return true;
    }

    /**
     * @return HashMap key value pair of json data
     * @param jsonObject representing json data
     */
    public static HashMap getKeyValuePair(JSONObject jsonObject) {
        Set entries = jsonObject.entrySet();
        HashMap map = new HashMap();
        Iterator itr = entries.iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    /**
     * @param str string value of json property
     * @return String value
     */
    public static String parseString(String str) {
        return str;
    }

    /**
     * @param lng numeric value from json
     * @return String representation of long value
     */
    public static String parseLong(Long lng) {
        return String.valueOf(lng);
    }

    /**
     * @param jsonObj JsonObject
     * @return String value
     */
    public static String parseObject(JSONObject jsonObj) {
        String str = "";
        Set entries = jsonObj.entrySet();
        Iterator itr = entries.iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            String key = (String) entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof JSONObject) {
                str += LINE_SEPARATOR + key + KEY_SEPARATOR + obj + LINE_SEPARATOR;

            } else {
                str += LINE_SEPARATOR + ARRAY_SEPARATOR + key + KEY_SEPARATOR + parseJSONEntry(obj);
            }
        }
        return str;
    }

    /**
     * @param jsonObj array of values from json data
     * @return String value
     */
    public static String parseArray(JSONArray jsonObj) {
        String str = "";
        Iterator itr = jsonObj.iterator();
        while (itr.hasNext()) {
            Object obj = itr.next();
            if (obj instanceof JSONArray) {
                str += parseArray((JSONArray) obj);

            } else {
                str += parseJSONEntry(obj) + ARRAY_SEPARATOR;
            }
        }
        return str;
    }

    /**
     * @param obj json element to parse
     * @return String value
     */
    public static String parseJSONEntry(Object obj) {
        String str = "";
        if (obj == null) {
            str += null;
        } else if (obj instanceof java.lang.String) {
            str += parseString((java.lang.String) obj);
        } else if (obj instanceof java.lang.Long) {
            str += parseLong((java.lang.Long) obj);
        } else if (obj instanceof JSONObject) {
            str += parseObject((JSONObject) obj);
        } else if (obj instanceof JSONArray) {
            str += parseArray((JSONArray) obj);
        }
        return str;

    }

    /**
     * @param jsonObject json data object to be parsed
     * @return String value
     */
    public static String parseJSON(JSONObject jsonObject) {
        String str = "";
        Set entries = jsonObject.entrySet();

        Iterator itr = entries.iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            String key = (String) entry.getKey();
            Object obj = entry.getValue();
            str += key + KEY_SEPARATOR + parseJSONEntry(obj) + LINE_SEPARATOR;
        }

        return str;

    }

}
