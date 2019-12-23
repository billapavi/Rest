package com.rotbot.billa.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicStatisSticks {

	private String date;
	private String gapUPorDown;
	private String green;
	private Candle data;
}
