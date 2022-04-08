package jp.co.tokyo_gas.mwb.simulator.controller;

import jp.co.tokyo_gas.mwb.simulator.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jp.co.tokyo_gas.mwb.simulator.common.RespResult;
import jp.co.tokyo_gas.mwb.simulator.common.RespResultWrapUtil;
import jp.co.tokyo_gas.mwb.simulator.model.SwitchStatusDataFromAPIModel;
import jp.co.tokyo_gas.mwb.simulator.service.TestDataService;
import jp.co.tokyo_gas.mwb.simulator.util.JacksonUtil;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SwitchStatusController {

    @Autowired
    private TestDataService testDataService;

    @GetMapping("/services/mwb-site-status")
    public void switchStatusInit(@RequestParam(value = "contractor-id") final String contractorId,
                                 @RequestParam(value = "search-start-date") final String startDate,
                                 @RequestParam(value = "search-end-date") final String endDate, HttpServletResponse response) {

        Object jsonData = null;
        jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());

        // 正常ケース
        if ("000001".equals(contractorId)) {
            jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());

            JSONObject jsonObject = JSONObject.parseObject(jsonData.toString());
            List<SwitchStatusDataFromAPIModel> list = JacksonUtil.getListFromJson(jsonObject.get("not-openclose-list").toString(),
                    SwitchStatusDataFromAPIModel.class);

            list = list.stream().filter(s -> (Integer.parseInt(startDate.substring(0, 8)) <=
                    Integer.parseInt(s.getEventDate().substring(0, 8)) && Integer.parseInt(endDate.substring(0, 8)) >=
                    Integer.parseInt(s.getEventDate().substring(0, 8)))).collect(Collectors.toList());

            Map<String, List<SwitchStatusDataFromAPIModel>> map = new HashMap<String, List<SwitchStatusDataFromAPIModel>>();

            map.put("not-openclose-list", list);
            jsonData = map;
            if (StringUtils.isEmpty(jsonData)) {
                ResponseUtil.error(response);
                return;
            }
        }

        // 異常ケース 未開閉検知リスト　size = 0
        if ("000002".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("switchStatusInit_listAreZero");
            if (StringUtils.isEmpty(jsonData)) {
                ResponseUtil.error(response);
                return;
            }
        }

        // 未開閉検知リスト　size > 20
        if ("000003".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("switchStatusInit_listMorethanTwenty");
            if (StringUtils.isEmpty(jsonData)) {
                ResponseUtil.error(response);
                return;
            }
        }

        // 未開閉検知リスト　size > 50
        if ("000004".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("switchStatusInit_listMorethanFifty");
            if (StringUtils.isEmpty(jsonData)) {
                ResponseUtil.error(response);
                return;
            }
        }

        // 未開閉検知リスト　size > 100
        if ("000005".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("switchStatusInit_listMorethanOnehundred");
            if (StringUtils.isEmpty(jsonData)) {
                ResponseUtil.error(response);
                return;
            }
        }

        // 異常ケース データ取る失敗
        if ("000006".equals(contractorId)) {
            jsonData = testDataService.GetJsonData("switchStatusInit_listAreZero_____nasi");
            List list = null;
            int i = list.size();
            if (StringUtils.isEmpty(jsonData)) {
                ResponseUtil.error(response);
                return;
            }
        }

        // 3DAY
        if ("000007".equals(contractorId)) {
//            jsonData = testDataService.GetJsonData("switchStatusInit_200");
//            if (StringUtils.isEmpty(jsonData)) {
                ResponseUtil.error(response);
                return;
//            }
        }

        ResponseUtil.ok(response, jsonData);
    }

    @PutMapping("/user/mwb-contractor")
    public void switchStatus(@RequestBody String bukenId, HttpServletResponse response) {
//    	Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
//        if (StringUtils.isEmpty(jsonData))
//            return RespResultWrapUtil.error();
        ResponseUtil.ok(response);
    }
}

