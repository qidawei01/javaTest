package jp.co.tokyo_gas.mwb.simulator.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jp.co.tokyo_gas.mwb.simulator.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestDataService {

    @Value(("${file.data-path}"))
    private String jsonFilePath;



    public Object GetJsonData(String methodName) {

        if (StringUtils.isEmpty(jsonFilePath)) {
            return "";
        }
        JSONParser parser = new JSONParser();

        Path currentPath = Paths.get(jsonFilePath, methodName + ".json");
        log.debug(currentPath.toString());
        Object result;
        try {
            result = parser.parse(new FileReader(currentPath.toString()));
            log.debug(JacksonUtil.toJson(result));
        } catch (IOException | ParseException e) {
            log.error(e.getMessage());
            return "";
        }
        return result;
    }
    public void SetJsonData(String methodName,String sets) {
    	FileWriter fw;
    	try {
    		fw = new FileWriter(jsonFilePath +"/" + methodName + ".json");
    		PrintWriter out = new PrintWriter(fw);
    		out.write(sets);
            out.println();
            fw.close();
            out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
