package br.com.teste.exception;

import org.apache.poi.ss.usermodel.Row;

public interface ExcelTemplateErrorHandler<T> {

	public T handleException(Row row, RuntimeException e);
}
