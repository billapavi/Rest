package com.rotbot.billa.stocks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="instruments")
public class StocksS {

	@Id
	@Column(name="STOCKNAME")
	private String stockName;
	@Column(name = "TOKENID")
	private String tokenID;
}
