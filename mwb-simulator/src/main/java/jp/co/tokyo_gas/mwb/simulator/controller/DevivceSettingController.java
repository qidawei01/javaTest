package jp.co.tokyo_gas.mwb.simulator.controller;

import jp.co.tokyo_gas.mwb.simulator.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.tokyo_gas.mwb.simulator.service.TestDataService;
import jp.co.tokyo_gas.mwb.simulator.util.JacksonUtil;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DevivceSettingController {

    @Autowired
    private TestDataService testDataService;

    @GetMapping("/devices/mwb-devices")
    public void getDevList(@RequestBody String dashData, HttpServletResponse response) {

        String contractorId = JacksonUtil.parseString(dashData, "contractor-id");
        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if ("000001".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getDevList_200");
        }
        // 異常ケース
        if ("000003".equals(contractorId)) {
            ResponseUtil.error(response);
            return;
        }
        if ("000002".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getDevList_zero");
        }
        if ("000004".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getDevList_15");
        }
        if ("000005".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getDevList_20");
        }
        if ("000006".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getDevList_25");
        }
        if ("000007".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("getDevList_100");
        }
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }

        ResponseUtil.ok(response, jsonData);
    }

    @PutMapping("/devices/mwb-firmware")
    public void updateCurrentWare(@RequestBody String dashData, HttpServletResponse response) {

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @PostMapping("/devices/hgw/update-request")
    public void hgwUpdateReq(HttpServletResponse response) {

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @PostMapping("/devices/mwb-batch-firmware")
    public void updateAllWare(@RequestBody String dashData, HttpServletResponse response) {

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @GetMapping("devicesetting-refreshStatus")
    public void refreshStatus(@RequestBody String dashData, HttpServletResponse response) {

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }
}
