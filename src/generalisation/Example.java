package generalisation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.Constant;
import util.JSONHandler;

/**
 * Uses JSONHandler to parse JSON
 * @author Richa
 */
public class Example {

    /**
     * @param args the command line arguments
     *     @throws FileNotFoundException file not found
     * @throws IOException I/O exception
     * @throws ParseException JSON parse exception
     */
    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        JSONObject jsonObject = JSONHandler.readDataFromFile(Constant.JSON_FILE_PATH);
        System.out.println(JSONHandler.parseJSON(jsonObject));
     
    }

}
