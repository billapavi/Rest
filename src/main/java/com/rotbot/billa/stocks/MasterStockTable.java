package com.rotbot.billa.stocks;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class MasterStockTable {

	@EmbeddedId
	private MainStockID id;
	
	private Double open ;
	private Double high ;
	private Double low ;
	private Double close ;
	private Long volueme ;
	private String tokenID;
}
