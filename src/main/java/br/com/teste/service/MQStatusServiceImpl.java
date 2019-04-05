package br.com.teste.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.domain.MQStatus;
import br.com.teste.repository.MQStatusRepository;


@Service
public class MQStatusServiceImpl implements MQStatusService {
	
	public static Logger LOGGER = Logger.getLogger(MQStatusServiceImpl.class.getName());
	
	public MQStatusRepository repository;
		
	
	@Autowired
	public MQStatusServiceImpl( MQStatusRepository repository) {
		this.repository=repository;
	}
	
	
	
	@Override
	public ResponseEntity<?> findAllStatus(String fileName)  {
		
	    try {
	    	
	    	MQStatus mQStatus = repository.findByFileName(fileName);

	    	if(mQStatus == null) {
	    		return new ResponseEntity<>("Arquivo não econtrado na fila",HttpStatus.NOT_FOUND);
	    	}
	    	return new ResponseEntity<>(mQStatus,  HttpStatus.OK);
	  
		} catch (Exception ex) {
			LOGGER.severe(ex.getMessage());
	    	return new ResponseEntity<>(ex.getMessage(),  HttpStatus.BAD_REQUEST);
		}
	}





	
	
}
