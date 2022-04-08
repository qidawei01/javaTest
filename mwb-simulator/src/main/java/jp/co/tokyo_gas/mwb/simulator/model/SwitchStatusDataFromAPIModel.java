package jp.co.tokyo_gas.mwb.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *　開閉状態確認画面のAPIデータ
 *
 * @author DXC
 */
@Data
@NoArgsConstructor
public class SwitchStatusDataFromAPIModel {

    /**
     * サービス状態
     */
    @JsonProperty("service-status")
    public String status;
    
    /**
     * 利用地点名称
     */
    @JsonProperty("service-site-name")
    public String tanakoMei;
    
    /**
     * 利用地点ID
     */
    @JsonProperty("service-site-id")
    public String tanakoId;
    
    /**
     * イベント発生日時YYYYMMDDhhmmss
     */
    @JsonProperty("event-date")
    public String eventDate;
    
    /**
     * コメント
     */
    @JsonProperty("comment")
    public String comment;

}
