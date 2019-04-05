package br.com.teste.reader;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.teste.exception.DefaultExcelTemplateErrorHandler;
import br.com.teste.exception.ExcelTemplateErrorHandler;


public class ExcelTemplate {


	private File file;
	private boolean skipFirstRowDefault;

	public ExcelTemplate(File file) {
		this.file = file;
	}

//	public <T> List<T> onEachRow(ExcelRowCallback<T> excelCallback, boolean onlyFirstRow, int numberSkipRow) {
//		return onEachRow(excelCallback, numberSkipRow, new DefaultExcelTemplateErrorHandler<T>(), onlyFirstRow);
//	}
//
//	public <T> List<T> onEachRow(String worksheetName, ExcelRowCallback<T> excelCallback, ExcelTemplateErrorHandler<T> errorHandler, boolean onlyFirstRow, int numberSkipRow) {
//		return onEachRow(excelCallback, numberSkipRow, errorHandler, onlyFirstRow);
//	}


	public <T>  T onOneEachRow(ExcelRowCallback<T> excelCallback) {
		InputStream inp = null;
		
		try {
			inp = new FileInputStream(file);
			XSSFWorkbook  wb = new XSSFWorkbook(inp);
			List<T> results = new ArrayList<T>();

			for (Row row : wb.getSheetAt(0)) {
				processRow(excelCallback, results, row);
				break;
			}
				
			
			return results.get(0);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				inp.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	
	public <T> List<T> onEachRow(ExcelRowCallback<T> excelCallback, int numberSkipRow) {
	
		InputStream inp = null;
	
		try {
			inp = new FileInputStream(file);
			XSSFWorkbook  wb = new XSSFWorkbook(inp);
			List<T> results = new ArrayList<T>();
				
			if (numberSkipRow != 0) {
				for (Row row : wb.getSheetAt(0)) {
					if (row.getRowNum() <= numberSkipRow) {
						continue;
					}

					processRow(excelCallback, results, row);
				}
			} else {
				for (Row row : wb.getSheetAt(0)) {
					processRow(excelCallback, results, row);
				}
			}
			
			return results;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				inp.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	private <T> void processRow(ExcelRowCallback<T> excelCallback,List<T> results, Row row) {
		
	    ExcelTemplateErrorHandler<T> errorHandler =  new DefaultExcelTemplateErrorHandler<T>();
		
	    T rowResult = null;
		try {
			rowResult = excelCallback.mapRow(row);
		} catch (RuntimeException e) {
			rowResult = errorHandler.handleException(row, e);
		}
		if (rowResult != null) {
			results.add(rowResult);
		}
	}

	public boolean isSkipFirstRowDefault() {
		return skipFirstRowDefault;
	}

	public void setSkipFirstRowDefault(boolean skipFirstRowDefault) {
		this.skipFirstRowDefault = skipFirstRowDefault;
	}
	
	

}
