package br.com.teste.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.teste.exception.FileNotFoundException;
import br.com.teste.exception.FileStorageException;
import br.com.teste.listener.Sender;
import br.com.teste.payload.UploadFileResponse;
import br.com.teste.property.FileStorageProperties;

@Service
public class FileStorageService {
	
	private static Logger log = LoggerFactory.getLogger(FileStorageService.class);

	private final Path fileStorageLocation;
	
	@Autowired 
	private Sender sender;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		
	    this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
	      try {
	        Files.createDirectories(this.fileStorageLocation);
	    } catch (Exception ex) {
	        throw new FileStorageException("Não foi possível criar o diretório onde os arquivos enviados serão armazenados.", ex);
	    }
	}

	public ResponseEntity<UploadFileResponse> storeFile(MultipartFile file) {
		
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    
	    try {

	    	//valida o tipo de arquivo
	        if(!fileName.endsWith("xlsx")) {
	            throw new FileStorageException("O tipo de arquivo não corresponde a planilha. Arquivo enviado= " + fileName);
	        }
	
	        //Copie o arquivo para o local de destino (substituindo o arquivo existente pelo mesmo nome)
	        Path targetLocation = this.fileStorageLocation.resolve(fileName);
	        
	        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
	        
	        sender.send(targetLocation.toAbsolutePath().toString());
	        
	        UploadFileResponse response = new UploadFileResponse(fileName, file.getContentType(), file.getSize());
	        
	        return new ResponseEntity<UploadFileResponse>(response,  HttpStatus.CREATED);
	    
	    }catch(FileStorageException e) {
	    	return new ResponseEntity(e.getMessage(),  HttpStatus.BAD_REQUEST);
	    } catch (IOException ex) {
	    	String msg ="Não foi possível armazenar o arquivo " + fileName + ". Tente novamente! " +  ex;
	    	return new ResponseEntity(msg,  HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
	    	return new ResponseEntity(ex.getMessage(),  HttpStatus.BAD_REQUEST);
		}
	}
	
	

	
}

