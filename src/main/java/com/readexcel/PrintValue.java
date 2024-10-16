package com.readexcel;

import java.io.IOException;
import java.util.Map;



public class PrintValue {

	public static void main(String[] args) throws IOException {

		Map<String, String> input = ReadExcel.getExcelData("Escalation Matrix", "Critical_L1_TEAM");
		
		String Escalation_TimesFrame = input.get("Escalation_TimesFrame");
		
		System.out.println(Escalation_TimesFrame);


	}

}
