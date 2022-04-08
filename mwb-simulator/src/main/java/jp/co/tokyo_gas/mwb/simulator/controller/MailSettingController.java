package jp.co.tokyo_gas.mwb.simulator.controller;

import java.util.List;

import jp.co.tokyo_gas.mwb.simulator.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import jp.co.tokyo_gas.mwb.simulator.common.RespResultWrapUtil;
import jp.co.tokyo_gas.mwb.simulator.model.MailSettingModel;
import jp.co.tokyo_gas.mwb.simulator.service.TestDataService;
import jp.co.tokyo_gas.mwb.simulator.util.JacksonUtil;

import javax.servlet.http.HttpServletResponse;


@RestController
public class MailSettingController {

    @Autowired
    private TestDataService testDataService;

    @GetMapping("/services/mwb-site-services")
    public void getMailList(HttpServletResponse response) {
        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @PostMapping("/services/mwb-site-service")
    public void updateMailList(@RequestBody String dashData, HttpServletResponse response) {
        String timeZone = JacksonUtil.parseString(dashData, "mail-send-interval");
        JSONObject jsonObject1 = JSONObject.parseObject(dashData);
        List<MailSettingModel> updateList = JacksonUtil.getListFromJson(jsonObject1.get("site-list").toString(), MailSettingModel.class);
        Object jsonData = testDataService.GetJsonData("updateMailList");
        JSONObject jsonObject = JSONObject.parseObject(jsonData.toString());
        List<MailSettingModel> list = JacksonUtil.getListFromJson(jsonObject.get("site-list").toString(),
                MailSettingModel.class);
        for (MailSettingModel oldModel : list) {
            for (MailSettingModel newModel : updateList) {
                if (newModel.getHenyaNo().equals(oldModel.getHenyaNo())) {
                    oldModel.setComment(newModel.getComment());
                    oldModel.setMailSendObj(newModel.getMailSendObj());
                }
            }
        }
        jsonObject.put("mail-send-interval", timeZone);
        jsonObject.put("site-list", JacksonUtil.toJson(list));
        testDataService.SetJsonData("updateMailList", JacksonUtil.toJson(jsonObject));
        //Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }

    @PostMapping("/services/mwb-mail-interval")
    public void updateTimezone(@RequestBody String dashData, HttpServletResponse response) {

        Object jsonData = testDataService.GetJsonData(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (StringUtils.isEmpty(jsonData)) {
            ResponseUtil.error(response);
            return;
        }
        ResponseUtil.ok(response, jsonData);
    }
}
