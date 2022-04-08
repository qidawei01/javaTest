package jp.co.tokyo_gas.mwb.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartnerDeviceSettingSersonModel {

    /**
     * 利用機器ID(非表示)
     */
    @JsonProperty("device-sensor-id")
    private String deviceId;
    
    /**
     * センサーシリアル番号
     */
    @JsonProperty("device-serial-no")
    private String sensorSiriaruNo;
    
    /**
     * センサー電文 送信時刻
     */
    @JsonProperty("device-com-date")
    private String sensorTelTime;

}
