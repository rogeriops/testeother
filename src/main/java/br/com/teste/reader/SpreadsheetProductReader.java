package br.com.teste.reader;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import br.com.teste.domain.Category;
import br.com.teste.domain.Product;

@Component
public class SpreadsheetProductReader {
	
	private ExcelTemplate template;
	private File file;
	
		
	public void init(String fileName){
		this.file = new File(fileName);
		this.template = new ExcelTemplate(file);
		
	}
	
	public Category read() throws Exception {
		
		Category category = 
				template.onOneEachRow(new ExcelRowCallback<Category>() {
					public Category mapRow(Row row) {
						Double cell = row.getCell(1).getNumericCellValue();
						return new Category(cell.longValue());
					}
				});
		
		List<Product> products = 
				template.onEachRow(new ExcelRowCallback<Product>() {
					public Product mapRow(Row row) {
						return new Product(
								((Double)row.getCell(0).getNumericCellValue()).longValue(),
								         row.getCell(1).getStringCellValue(),
								((Double)row.getCell(2).getNumericCellValue()).intValue(),
								         row.getCell(3).getStringCellValue(),
								Double.parseDouble(row.getCell(4).getStringCellValue()),
								category
								);
					}
				}, 2);
 
		
		category.setProducts(products);		
		
		return category;
	}
}
