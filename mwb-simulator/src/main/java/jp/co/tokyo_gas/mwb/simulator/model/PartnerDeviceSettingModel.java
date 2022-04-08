package jp.co.tokyo_gas.mwb.simulator.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartnerDeviceSettingModel {

    /**
     * 利用地点ID(非表示)
     */
    @JsonProperty("service-site-id")
    private String siteId;
    
    /**
     * 部屋番号
     */
    @JsonProperty("service-site-name")
    private String heyaNo;
    
    /**
     * サービス利用番号
     */
    @JsonProperty("service-id")
    private String serviceId;
    
    /**
     *     ゲートウェイ登録
     */
    @JsonProperty("hgw-serial-no")
    private String waretoyroku;
    
    /**
     *　通信状態
     */
    @JsonProperty("hgw-com-status")
    private String tusinStatus;
    
    /**
     * ドングル シリアル番号
     */
    @JsonProperty("dongle-serial-no") 
    private String donguruSiriaruNo;
    
    /**
     * ドングル通信状態
     */
    @JsonProperty("dongle-com-status")
    private String donguruTusinnStatus;
    
    /**
     * センサー詳細
     */
    @JsonProperty("sensor-list")
    private List<PartnerDeviceSettingSersonModel> sensors;
}
