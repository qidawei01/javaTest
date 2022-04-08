package jp.co.tokyo_gas.mwb.simulator.controller;

import jp.co.tokyo_gas.mwb.simulator.common.RespResult;
import jp.co.tokyo_gas.mwb.simulator.common.RespResultWrapUtil;
import jp.co.tokyo_gas.mwb.simulator.service.TestDataService;
import jp.co.tokyo_gas.mwb.simulator.util.JacksonUtil;
import jp.co.tokyo_gas.mwb.simulator.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private TestDataService testDataService;

    private String userName = null;

    @PostMapping("/user/mwb-authentication")
    public void ownerAuth(@RequestBody String loginData, HttpServletResponse response) {
        // loginId: owner001 Status code:200
        String loginId = JacksonUtil.parseString(loginData, "login-id");
        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + loginId);
        userName = loginId;
        // loginId: owner002 Status code:500
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @PostMapping("/user/mwb-login")
    public void webLogin(HttpServletResponse response) {
        Object jsonData = null;
        System.out.println(userName);
        if (!StringUtils.isEmpty(userName)) {
            if ("WWWWWWWWWWWWWWWWWWWWWWWWW".equals(userName)) {
                jsonData = testDataService.GetJsonData("weblogin_maxlength");
            } else {
                jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
            }
        }
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }

        ResponseUtil.ok(response, jsonData);
    }

    @PostMapping("/user/mwb-admin-token")
    public void webAdminToken( HttpServletResponse response) {
        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());

        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }

        ResponseUtil.ok(response, jsonData);
    }
    @PostMapping("/duke/login")
    public void partnerlogin(@RequestBody String loginData, HttpServletResponse response) {
    	String ldapId = JacksonUtil.parseString(loginData, "ldapId");
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("resultCode", 9);
    	switch(ldapId){
    	case "test999":
    		jsonObject.put("resultCode", "1");
    		jsonObject.put("errorCode", "");
    		break;
    	case "test901":
    		jsonObject.put("errorCode", "ERR80000001");
    		break;
    	case "test902":
    		jsonObject.put("errorCode", "ERR80000002");
    		break;
    	case "test903":
    		jsonObject.put("errorCode", "ERR80000003");
    		break;
    	case "test904":
    		jsonObject.put("errorCode", "ERR80000004");
    		break;
    	case "test905":
    		jsonObject.put("errorCode", "ERR80000005");
    		break;
    	case "test906":
    		jsonObject.put("errorCode", "ERR80000006");
    		break;
    	case "test907":
    		jsonObject.put("errorCode", "ERR80000007");
    		break;
    	case "test908":
    		jsonObject.put("errorCode", "ERR80000008");
    		break;
    	case "test909":
    		jsonObject.put("errorCode", "ERR80000009");
    		break;
    	case "test910":
    		jsonObject.put("errorCode", "ERR80000010");
    		break;
    	}
    	jsonObject.put("co", "License00");
    	jsonObject.put("tgAssistantFlag", "1");
    	jsonObject.put("employeeType", "1");
    	ResponseUtil.ok(response, jsonObject);
    	
    	//ResponseUtil.error(response);

    }
}
