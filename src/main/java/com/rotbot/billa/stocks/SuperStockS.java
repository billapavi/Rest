package com.rotbot.billa.stocks;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class SuperStockS {

	@GeneratedValue
	private int id;
	
	private String tokenID;
	private String stockName;
	@Id
	private String timestamp;
	private Double open ;
	private Double high ;
	private Double low ;
	private Double close ;
	private Long volueme ;
}
