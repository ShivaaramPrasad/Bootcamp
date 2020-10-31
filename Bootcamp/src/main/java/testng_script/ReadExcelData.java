package testng_script;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	public String[][] readData(String fileName) throws IOException {
		//To give the location of my workbook
		XSSFWorkbook ws  = new XSSFWorkbook("./data/"+fileName+".xlsx");

		//to get into the worksheet using sheet name
		XSSFSheet sheet = ws.getSheet("Sheet1");

		//to get into the worksheet using index
		//ws.getSheetAt(0);

		//to get the number of rows excluding the first row
		int rowCount = sheet.getLastRowNum();

		//to get the number of Cells
		int cellsCount = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[rowCount][cellsCount];		

		for (int i = 1; i <= rowCount; i++) {

			for (int j = 0; j < cellsCount; j++) {

				String cellValue = sheet.getRow(i).getCell(j).getStringCellValue();

				//	System.out.println(cellValue);

				data[i-1][j] = cellValue;

			}

			System.out.println();

		}


		//to close the workbook
		ws.close();

		return data;

	}

}


