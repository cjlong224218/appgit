package com.youceedu.interf.util;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @Title:  ExcelUtil.java   
 * @Package com.youceedu.interf.util   
 * @Description:java基于poi操作excel  
 * @author: chenjianlong    
 * @date:   2021年4月29日 下午9:10:12   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */
public class ExcelUtil {
	/**
	 * 初始化
	 */
	private String fileName;
	private static Logger logger = Logger.getLogger(ExcelUtil.class);
	
	/**
	 * 
	 * @Title:  ExcelUtil   
	 * @Description: 构造方法
	 * @param:  @param fileName  
	 * @throws
	 */
	public ExcelUtil(String fileName) {
		this.fileName = fileName;
		logger.info("实例化类，入参fileName=" + fileName);
	}
	
	/**
	 * 
	 * @Title: getWb   
	 * @Description: 得到指定excel文件XSSFWork类对象
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: XSSFWorkbook      
	 * @throws
	 */
	public XSSFWorkbook getWb() throws Exception{
		XSSFWorkbook wb = new XSSFWorkbook(this.fileName);
		logger.info("成功得到wb对象");
		return wb;
	}
	
	/**
	 * 
	 * @Title: getSheet   
	 * @Description:据sheetIndex得到指定的excel某文件的某个sheet
	 * @param: @param sheetIndex
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: XSSFSheet      
	 * @throws
	 */
	public XSSFSheet getSheet(int sheetIndex) throws Exception {
		XSSFWorkbook wb = getWb();
		XSSFSheet sheet = wb.getSheetAt(sheetIndex);
		logger.info("成功得到sheet对象");
		return sheet;
	}
	
	/**
	 * 
	 * @Title: getCellValue   
	 * @Description: 据sheetIndex,rowIndex,cellIndex得到指定的cell值
	 * @param: @param sheetIndex
	 * @param: @param rowIndex
	 * @param: @param cellIndex
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Object      
	 * @throws
	 */
	public Object getCellValue(int sheetIndex,int rowIndex,int cellIndex) throws Exception{
		//初始化
		Object value = null;
		
		XSSFRow row = getSheet(sheetIndex).getRow(rowIndex);
		XSSFCell cell = row.getCell(cellIndex);
		value=fromCellTypeGetCellValue(cell);
		logger.info("入参sheetIndex=" + sheetIndex + "rowINdex=" + rowIndex + "celIndex=" + cellIndex + "单元格值=" + value);
		return value;
	}
	
	/**
	 * 
	 * @Title: fromCellTypeGetCellValue   
	 * @Description: 据单元格对象判断并对应的取值
	 * @param: @param cell
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Object      
	 * @throws
	 */
	public Object fromCellTypeGetCellValue(XSSFCell cell)throws Exception{
		//初始化
		Object value=null;
		
		//得到单元格的值--据单元格类型选择合适的方法
		CellType cellType=cell.getCellTypeEnum();
		switch(cellType){
			case _NONE:
				value = "";
				break;
			case NUMERIC:
				value = cell.getNumericCellValue();
				break;
			case STRING:
				value = cell.getStringCellValue();
				break;
			case FORMULA:
				value = cell.getCellFormula();
				break;
			case BLANK:
				value = "";
				break;
			case BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			case ERROR:
				value = cell.getErrorCellString();
				break;
			default:
				value = cell.getDateCellValue();
				break;
		}
		logger.info("单元格类型=" + cellType + "值=" + value.toString());
		return value;
		
	}
	
	/**
	 * 
	 * @Title: getArrayCellValue   
	 * @Description: 一次性把excel内的测试数据读取到二维数组里面
	 * @param: @param sheetIndex
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Object[][]      
	 * @throws
	 */
	public Object[][] getArrayCellValue(int sheetIndex) throws Exception {
		//初始化
		Object[][] testCaseData = null; 
		XSSFSheet sheet = getSheet(sheetIndex);
		int lastRowNum = sheet.getLastRowNum();
		testCaseData = new Object[lastRowNum][10];
		
		//基于行列遍历excel
		for(int rowIndex=1;rowIndex <= lastRowNum;rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			if(row == null) {continue;}
			
			for(short cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
				XSSFCell cell = row.getCell(cellIndex);
				if(cell == null) {continue;}
				//行上的每一列单元格的值存储到二维数组
				testCaseData[rowIndex-1][cellIndex] = fromCellTypeGetCellValue(cell);
			}
		}
		logger.info("excel内测试用例都已成功存入二维数组testCaseData中");
		return testCaseData;
	}
	
	public static void main(String[] args) throws Exception{
		/*
		ExcelUtil8 excelUtil6 = new ExcelUtil8("D:\\data\\001.xlsx");
		Object object = excelUtil6.getCellValue(0, 1, 2);
		System.out.println(object.toString());
		*/
		
		//高效方法-一次性把excel数据读取出来
		ExcelUtil excelUtil = new ExcelUtil("D:\\data\\001.xlsx");
		Object[][] value = excelUtil.getArrayCellValue(0);
		System.out.println(value[0][0]);
		System.out.println(value[0][1]);
		System.out.println(value[0][2]);
		System.out.println(value[0][3]);
	}

}
