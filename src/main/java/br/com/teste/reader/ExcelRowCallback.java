package br.com.teste.reader;

import org.apache.poi.ss.usermodel.Row;

public interface ExcelRowCallback<T> {

	public T mapRow(Row row);

}
