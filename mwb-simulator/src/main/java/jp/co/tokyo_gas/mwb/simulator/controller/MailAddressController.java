package jp.co.tokyo_gas.mwb.simulator.controller;

import java.util.ArrayList;
import java.util.List;

import jp.co.tokyo_gas.mwb.simulator.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import jp.co.tokyo_gas.mwb.simulator.service.TestDataService;
import jp.co.tokyo_gas.mwb.simulator.util.JacksonUtil;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MailAddressController {
    @Autowired
    private TestDataService testDataService;

    @GetMapping("/user/mwb-sites")
    public void getTenkolist(@RequestBody String dashData, HttpServletResponse response) {
        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @GetMapping("/user/mwb-site-contact/{loginId}")
    public void getMailAddressList(@PathVariable("loginId") final String loginId, HttpServletResponse response) {
        Object jsonData = null;
        if ("102".equals(loginId)) {
            jsonData = testDataService.GetJsonData("getMailAddressList_maxlength");
        } else {
            jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        }
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @PostMapping("/user/mwb-site-mailaddress")
    public void insertMail(@RequestBody String dashData, HttpServletResponse response) {
        String mailAddress = JacksonUtil.parseString(dashData, "mail-address");
        Object jsonData = testDataService.GetJsonData("getMailAddressList");
        //Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        JSONObject jsonObject = JSONObject.parseObject(jsonData.toString());
        List<String> mailList = JSONObject.parseArray(jsonObject.get("mail-address-list").toString(), String.class);
        mailList.add(mailAddress);
        JSONObject setJsonObject = new JSONObject();
        setJsonObject.put("mail-address-list", mailList);
        testDataService.SetJsonData("getMailAddressList", JacksonUtil.toJson(setJsonObject));
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @DeleteMapping("/user/mwb-site-mailaddress")
    public void deleteMail(@RequestBody String dashData, HttpServletResponse response) {
        String mailAddress = JacksonUtil.parseString(dashData, "mail-address");
        Object jsonData = testDataService.GetJsonData("getMailAddressList");
        JSONObject jsonObject = JSONObject.parseObject(jsonData.toString());
        List<String> mailList = JSONObject.parseArray(jsonObject.get("mail-address-list").toString(), String.class);
        List<String> newMailList = new ArrayList<String>();
        if (mailList != null && mailList.size() != 0) {
            for (String mail : mailList) {
                if (!mail.equals(mailAddress)) {
                    newMailList.add(mail);
                }
            }
        }
        JSONObject setJsonObject = new JSONObject();
        setJsonObject.put("mail-address-list", newMailList);
        testDataService.SetJsonData("getMailAddressList", JacksonUtil.toJson(setJsonObject));
        //Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @GetMapping("mailaddress-getbukenlist")
    public void getBukenlist(@RequestBody String dashData, HttpServletResponse response) {
        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

}
