package br.com.teste.exception;

import org.apache.poi.ss.usermodel.Row;


public class DefaultExcelTemplateErrorHandler<T> implements ExcelTemplateErrorHandler<T> {

	public T handleException(Row row, RuntimeException e) {
		return null;
	}

}
