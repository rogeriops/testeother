package br.com.teste.service;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.teste.domain.Category;
import br.com.teste.domain.MQStatus;
import br.com.teste.reader.SpreadsheetProductReader;
import br.com.teste.repository.CategoryRepository;
import br.com.teste.repository.MQStatusRepository;

@Component
public class SpreadsheetProductFacade {
	
	public static Logger LOGGER = Logger.getLogger(SpreadsheetProductFacade.class.getName());
	
	private SpreadsheetProductReader reader;
	
	public CategoryRepository categoryRepository;
	
	public MQStatusRepository mQStatusRepository;
	
	@Autowired
	public SpreadsheetProductFacade(CategoryRepository categoryRepository, SpreadsheetProductReader reader, MQStatusRepository mQStatusRepository) {
		this.categoryRepository=categoryRepository;
		this.reader=reader;
		this.mQStatusRepository=mQStatusRepository;
	}
	
	public void execute(String fileName, int jmsId) {
		
		MQStatus mq = null;
		
		try {
			String file = StringUtils.cleanPath(new File(fileName).getName());
			
			mq = new MQStatus(jmsId, file, new GregorianCalendar(), "EM PROCESSAMENTO");
			
			
			mQStatusRepository.delete(mQStatusRepository.findByFileName(file));
			mQStatusRepository.saveAndFlush(mq);
			
			this.reader.init(fileName);
			
			Category category = reader.read();
			
			categoryRepository.save(category);
			
			mq.setDateFinal(new GregorianCalendar());
			
			mq.setStatus("SUCESSO NO PROCESSAMENTO");
			
			mQStatusRepository.saveAndFlush(mq);
			
		} catch (Exception e) {
			LOGGER.severe("Ocorreu um erro no processamento do arquivo " + e.getMessage());
			mq.setStatus("FALHOU NO PROCESSAMENTO");
			mQStatusRepository.saveAndFlush(mq);
		}
		
		
	}

	
}
