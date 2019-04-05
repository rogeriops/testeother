package br.com.teste.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author Rogerio
 * Permite vincular automaticamente as propriedades definidas no arquivo application.properties 
 * a uma classe POJO. Liga todas as propriedades com prefixo 'file' aos campos correspondentes 
 * da classe POJO.
 *
 */

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}
