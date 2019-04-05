package br.com.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.domain.MQStatus;

@Repository
public interface MQStatusRepository extends JpaRepository<MQStatus, Long> {

	MQStatus findByFileName(String fileName);

	

	

}
