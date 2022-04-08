package jp.co.tokyo_gas.mwb.simulator.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jp.co.tokyo_gas.mwb.simulator.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import jp.co.tokyo_gas.mwb.simulator.common.RespResult;
import jp.co.tokyo_gas.mwb.simulator.common.RespResultWrapUtil;
import jp.co.tokyo_gas.mwb.simulator.model.OwnerSelectFromApiModel;
import jp.co.tokyo_gas.mwb.simulator.service.TestDataService;
import jp.co.tokyo_gas.mwb.simulator.util.JacksonUtil;

import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/user")
@RestController
public class OwnerSelectController {

    @Autowired
    private TestDataService testDataService;

    @GetMapping("/mwb-owners")
    public void webOwners(@RequestBody String dashData, HttpServletResponse response) {

        //Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        Object jsonData = null;

        String ownerName = JacksonUtil.parseString(dashData, "contractor-search");
        if (!StringUtils.isEmpty(ownerName)) {
            if (ownerName.equals("A")) {
                jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + ownerName);
            } else if (ownerName.equals("15")) {
                jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName() + "15");
            } else if (ownerName.equals("20")) {
                jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName() + "20");
            } else if (ownerName.equals("25")) {
                jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName() + "25");
            } else if (ownerName.equals("100")) {
                jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName() + "100");
            } else if (ownerName.equals("200")) {
                jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName() + "200");
            } else {
                jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
                JSONObject jsonObject = JSONObject.parseObject(jsonData.toString());

                List<OwnerSelectFromApiModel> list = JacksonUtil.getListFromJson(jsonObject.get("service-site-list").toString(),
                        OwnerSelectFromApiModel.class);
                list = list.stream().filter(s -> s.getOwnerName().contains(ownerName)).collect(Collectors.toList());

                Map<String, List<OwnerSelectFromApiModel>> map = new HashMap<String, List<OwnerSelectFromApiModel>>();
                map.put("service-site-list", list);

                jsonData = map;
            }

        } else {
            jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        }

        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @PutMapping("/mwb-admin-session")
    public void webAdminSession(@RequestBody String dashData, HttpServletResponse response) {

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

}

