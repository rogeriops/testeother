package br.com.teste.domain;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MQStatus {
	
	@Id
	private long id;

	private String fileName;
	
	private GregorianCalendar startDate;
	
	private GregorianCalendar dateFinal;
	
	private String status;
	
	public MQStatus() {
		super();
	}
	
	

	public MQStatus(long id, String fileName, GregorianCalendar startDate, String status) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.startDate = startDate;
		this.status = status;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	public GregorianCalendar getDateFinal() {
		return dateFinal;
	}

	public void setDateFinal(GregorianCalendar dateFinal) {
		this.dateFinal = dateFinal;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	


}

