package com.rotbot.billa.stocks;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MainStockID implements Serializable{
	@NotNull
	private String timeStamp;
	@NotNull
	private String stockName;
	

}
