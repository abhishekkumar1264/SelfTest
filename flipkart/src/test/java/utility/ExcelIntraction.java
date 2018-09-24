package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import bsh.classpath.BshClassPath.GeneratedClassSource;

public class ExcelIntraction {

	private static XSSFWorkbook workBook;
	private static XSSFSheet wSheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	// private static FileInputStream fis;
	
/*	public ExcelIntraction(String filePath) throws IOException {
		
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}*/
	
	/*public static void main(String[] args) throws IOException {
		
		Object[][] xy = excelAllData("D:/Workspace_s/flipkart/src/test/java/testData/Test_Data.xlsx", "testdata");
	}*/
	
	public static Object[][] excelAllData(String filePath,String sheetName) throws IOException{
		
		String[][] excelData=null;
		/*int startRow=1;
		int startCol=1;*/
		int ci,cj;
		XSSFWorkbook wb = null;
		System.out.println(filePath);
		
		try {
			
			FileInputStream fis = new FileInputStream(filePath);
			
			wb = new XSSFWorkbook(fis);
			 
			XSSFSheet wSheet = wb.getSheet(sheetName);
			
			XSSFRow row=wSheet.getRow(1);

			int totalRows = wSheet.getLastRowNum();
			
			int totalCol=row.getLastCellNum();

			// you can write a function as well to get Column count
			
			excelData=new String [totalRows][totalCol];
			//System.out.println(excelData.length);
			for(int i=1;i<=totalRows;i++,ci++) {
				ci=0;
				cj=0;
				for(int j=0;j<totalCol;j++,cj++) {
					
					excelData[ci][cj]=wSheet.getRow(i).getCell(j).toString();
					//System.out.println(excelData[ci][cj]);
				}
			}
			wb.close();
			fis.close();
			
		}
		
		catch(FileNotFoundException e) {
			System.out.println("Could not read the excel file");
			e.printStackTrace();
		}
		
		for(int i=0;i<excelData.length;i++) {
			for(int j=0;j<=excelData.length;j++) {
				System.out.println(excelData[i][j]);
			}
		}
		return excelData;

}
		
		
}
	
