package jp.co.tokyo_gas.mwb.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *　オーナーリスト
 *
 * @author DXC
 */
@Data
@NoArgsConstructor
public class OwnerSelectFromApiModel {
	
	/**
	 * index
	 */
    int index;
	
	/**
	 * オーナーID
	 */
	@JsonProperty("usr-site-user")
    String ownerId;
    
    /**
	 * 名前
	 */
	@JsonProperty("landlord")
    String ownerName;
}
