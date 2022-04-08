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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import jp.co.tokyo_gas.mwb.simulator.common.RespResult;
import jp.co.tokyo_gas.mwb.simulator.common.RespResultWrapUtil;
import jp.co.tokyo_gas.mwb.simulator.model.PartnerDeviceSettingModel;
import jp.co.tokyo_gas.mwb.simulator.model.PartnerDeviceSettingSersonModel;
import jp.co.tokyo_gas.mwb.simulator.service.TestDataService;
import jp.co.tokyo_gas.mwb.simulator.util.JacksonUtil;

import javax.servlet.http.HttpServletResponse;


@RestController
public class PartnerDeviceSettingController {

    @Autowired
    private TestDataService testDataService;

    @GetMapping("/devices/mwb-admin-devices")
    public void getOwnerDevList(@RequestBody String dashData, HttpServletResponse response) {

        String contractorId = JacksonUtil.parseString(dashData, "contractor-id");

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if ("000003".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getOwnerDevList_zero");
        }
        if ("000004".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getOwnerDevList_15");
        }
        if ("000005".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getOwnerDevList_20");
        }
        if ("000002".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getOwnerDevList_25");
        }
        if ("000006".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getOwnerDevList_100");
        }
        if ("000007".equals(contractorId)) {
//            jsonData = testDataService.GetJsonData("getOwnerDevList_200");
        	ResponseUtil.error(response);
            return;
        }
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @PostMapping("/devices/hgw")
    public void loginWare(@RequestBody String dashData, HttpServletResponse response) {

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @PutMapping("/devices/mwb-hgw-serialno")
    public void currentServiceSite(@RequestBody String dashData, HttpServletResponse response) {

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @PostMapping("/devices/mwb-hgw-serialno")
    public void currentServiceSiteLogin(@RequestBody String dashData, HttpServletResponse response) {

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @DeleteMapping("/devices/mwb-device/{deviceId}")
    public void deleteWare(@PathVariable("deviceId") final String deviceId,
                           @RequestParam(value = "service-site-id", required = false) final String contractorId,
                           HttpServletResponse response) {
        Object jsonData = testDataService.GetJsonData("getOwnerDevList");
        JSONObject jsonObject = JSONObject.parseObject(jsonData.toString());
        List<PartnerDeviceSettingModel> list = JacksonUtil.getListFromJson(jsonObject.get("device-list").toString(),
                PartnerDeviceSettingModel.class);
        for (PartnerDeviceSettingModel model : list) {
            if (contractorId.equals(model.getSiteId())) {
                List<PartnerDeviceSettingSersonModel> listSen = model.getSensors();
                List<PartnerDeviceSettingSersonModel> newListSen = new ArrayList<PartnerDeviceSettingSersonModel>();
                for (PartnerDeviceSettingSersonModel serson : listSen) {
                    if (!deviceId.equals(serson.getDeviceId())) {
                        newListSen.add(serson);
                    }
                }
                model.setSensors(newListSen);
            }

        }
        JSONObject setJsonObject = new JSONObject();
        setJsonObject.put("device-list", JacksonUtil.toJson(list));
        testDataService.SetJsonData("getOwnerDevList", JacksonUtil.toJson(setJsonObject));
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }
}
