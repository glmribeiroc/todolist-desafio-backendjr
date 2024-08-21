package br.com.guilhermecosta.desafiotodomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guilhermecosta.desafiotodomanager.entity.SubTask;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

}
