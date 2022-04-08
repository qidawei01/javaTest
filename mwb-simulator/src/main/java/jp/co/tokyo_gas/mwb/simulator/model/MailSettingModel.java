package jp.co.tokyo_gas.mwb.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailSettingModel {
	private String id;
    
    /**
     * 部屋番号
     */
    @JsonProperty("service-site-id")
    private String henyaNo;

    /**
     * 番号
     */
    @JsonProperty("service-site-name")
    private String henyaName;
    
    /**
     * メール送付対象
     */
    @JsonProperty("mail-send-flag")
    private String mailSendObj;
    
    /**
     * コメント
     */
    private String comment;
}
