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
 * @Description:java����poi����excel  
 * @author: chenjianlong    
 * @date:   2021��4��29�� ����9:10:12   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class ExcelUtil {
	/**
	 * ��ʼ��
	 */
	private String fileName;
	private static Logger logger = Logger.getLogger(ExcelUtil.class);
	
	/**
	 * 
	 * @Title:  ExcelUtil   
	 * @Description: ���췽��
	 * @param:  @param fileName  
	 * @throws
	 */
	public ExcelUtil(String fileName) {
		this.fileName = fileName;
		logger.info("ʵ�����࣬���fileName=" + fileName);
	}
	
	/**
	 * 
	 * @Title: getWb   
	 * @Description: �õ�ָ��excel�ļ�XSSFWork�����
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: XSSFWorkbook      
	 * @throws
	 */
	public XSSFWorkbook getWb() throws Exception{
		XSSFWorkbook wb = new XSSFWorkbook(this.fileName);
		logger.info("�ɹ��õ�wb����");
		return wb;
	}
	
	/**
	 * 
	 * @Title: getSheet   
	 * @Description:��sheetIndex�õ�ָ����excelĳ�ļ���ĳ��sheet
	 * @param: @param sheetIndex
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: XSSFSheet      
	 * @throws
	 */
	public XSSFSheet getSheet(int sheetIndex) throws Exception {
		XSSFWorkbook wb = getWb();
		XSSFSheet sheet = wb.getSheetAt(sheetIndex);
		logger.info("�ɹ��õ�sheet����");
		return sheet;
	}
	
	/**
	 * 
	 * @Title: getCellValue   
	 * @Description: ��sheetIndex,rowIndex,cellIndex�õ�ָ����cellֵ
	 * @param: @param sheetIndex
	 * @param: @param rowIndex
	 * @param: @param cellIndex
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Object      
	 * @throws
	 */
	public Object getCellValue(int sheetIndex,int rowIndex,int cellIndex) throws Exception{
		//��ʼ��
		Object value = null;
		
		XSSFRow row = getSheet(sheetIndex).getRow(rowIndex);
		XSSFCell cell = row.getCell(cellIndex);
		value=fromCellTypeGetCellValue(cell);
		logger.info("���sheetIndex=" + sheetIndex + "rowINdex=" + rowIndex + "celIndex=" + cellIndex + "��Ԫ��ֵ=" + value);
		return value;
	}
	
	/**
	 * 
	 * @Title: fromCellTypeGetCellValue   
	 * @Description: �ݵ�Ԫ������жϲ���Ӧ��ȡֵ
	 * @param: @param cell
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Object      
	 * @throws
	 */
	public Object fromCellTypeGetCellValue(XSSFCell cell)throws Exception{
		//��ʼ��
		Object value=null;
		
		//�õ���Ԫ���ֵ--�ݵ�Ԫ������ѡ����ʵķ���
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
		logger.info("��Ԫ������=" + cellType + "ֵ=" + value.toString());
		return value;
		
	}
	
	/**
	 * 
	 * @Title: getArrayCellValue   
	 * @Description: һ���԰�excel�ڵĲ������ݶ�ȡ����ά��������
	 * @param: @param sheetIndex
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Object[][]      
	 * @throws
	 */
	public Object[][] getArrayCellValue(int sheetIndex) throws Exception {
		//��ʼ��
		Object[][] testCaseData = null; 
		XSSFSheet sheet = getSheet(sheetIndex);
		int lastRowNum = sheet.getLastRowNum();
		testCaseData = new Object[lastRowNum][10];
		
		//�������б���excel
		for(int rowIndex=1;rowIndex <= lastRowNum;rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			if(row == null) {continue;}
			
			for(short cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
				XSSFCell cell = row.getCell(cellIndex);
				if(cell == null) {continue;}
				//���ϵ�ÿһ�е�Ԫ���ֵ�洢����ά����
				testCaseData[rowIndex-1][cellIndex] = fromCellTypeGetCellValue(cell);
			}
		}
		logger.info("excel�ڲ����������ѳɹ������ά����testCaseData��");
		return testCaseData;
	}
	
	public static void main(String[] args) throws Exception{
		/*
		ExcelUtil8 excelUtil6 = new ExcelUtil8("D:\\data\\001.xlsx");
		Object object = excelUtil6.getCellValue(0, 1, 2);
		System.out.println(object.toString());
		*/
		
		//��Ч����-һ���԰�excel���ݶ�ȡ����
		ExcelUtil excelUtil = new ExcelUtil("D:\\data\\001.xlsx");
		Object[][] value = excelUtil.getArrayCellValue(0);
		System.out.println(value[0][0]);
		System.out.println(value[0][1]);
		System.out.println(value[0][2]);
		System.out.println(value[0][3]);
	}

}
