package utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFWorkbook wb = null;
	private static XSSFSheet sheet = null;
	private static String excellPath;

	public static boolean setExcell(String path) {
		if (wb != null) {
			try {
				wb.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				return false;
			}
		}
		File f = new File(path);
		try {
			FileInputStream fis = new FileInputStream(f);

			wb = new XSSFWorkbook(fis);
			excellPath = path;
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Bad file path!");
			return false;
		}
	}

	public static boolean setWorkSheet(int index) {
		try {
			sheet = wb.getSheetAt(index);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("There has been a mistake with the opening of the worksheet!");
			return false;
		}
	}

	public static String getDataAt(int row, int column) {
		try {
			XSSFRow r = sheet.getRow(row);
			XSSFCell celija = r.getCell(column);
			return celija.toString();
		} catch (NullPointerException e) {
			System.out.println(e.toString());
			System.out.println("Something is null!");
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("There has been a mistake!");
		}
		return "";
	}
	
	
	public static String getDataAtNum(int row, int column) {
		try {
			XSSFRow r = sheet.getRow(row);
			XSSFCell celija = r.getCell(column);
			double br = celija.getNumericCellValue();
			String rez= "";
			rez = rez + (int)br;
			return rez;
		} catch (NullPointerException e) {
			System.out.println(e.toString());
			System.out.println("Something is null!");
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("There has been a mistake!");
		}
		return "";
	}


	public static boolean setDataAt(int row, int column, String data) {
		try {
			XSSFRow r = sheet.getRow(row);
			if (r == null) {
				r = sheet.createRow(row);
			}
			XSSFCell celija = r.getCell(column);
			if (celija == null) {
				celija = r.createCell(column);
			}
			celija.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(excellPath);
			wb.write(fos);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("There has been a mistake!");
			return false;
		}
	}
	
	

	public static int getRowNumber() {
		try {
			
			return sheet.getLastRowNum() + 1;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("There has been a mistake!");
			return -1;
		}
	}

	public static boolean closeExcell() {
		if (wb != null) {
			try {
				wb.close();
				wb = null;
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				wb = null;
				return false;
			}
		}
		return true;
	}

	public static void setRandomUserId() {
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String newId= UUID.randomUUID().toString();
			String id = newId.substring(0, 8);
			ExcelUtils.setDataAt(i, 0, id);
		}
		
	}
}
