package br.com.biazus.casemanager.domain.model.service;

import static br.com.biazus.casemanager.domain.model.adapter.CourtCaseAdapter.toCourtCase;
import static br.com.biazus.casemanager.domain.model.adapter.CourtCaseAdapter.toCourtCaseDTO;
import static br.com.biazus.casemanager.domain.model.adapter.CourtCaseAdapter.toCourtCaseDTOList;
import static br.com.biazus.casemanager.domain.model.adapter.CourtCaseAdapter.toCourtCaseList;
import static br.com.biazus.casemanager.domain.model.validator.CourtCaseValidator.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.biazus.casemanager.domain.model.entity.CourtCase;
import br.com.biazus.casemanager.domain.model.entity.CourtCaseDTO;
import br.com.biazus.casemanager.domain.model.repository.CourtCaseRepository;
import br.com.biazus.casemanager.exception.CourtCaseNotFoundException;
import br.com.biazus.casemanager.exception.InvalidAccessTypeException;
import br.com.biazus.casemanager.exception.InvalidCourtCaseException;

@Service
public class CourtCaseService {
	
	@Autowired
	private CourtCaseRepository repository;
	
	public List<CourtCaseDTO> findAllCourtCase() {
		return toCourtCaseDTOList(repository.findAll());
	}

	public CourtCaseDTO findCourtCaseById(Long id) {
		return toCourtCaseDTO(repository.findById(id).orElseGet(CourtCase::new));
	}
	
	public List<CourtCaseDTO> findCourtCaseByExample(CourtCaseDTO courtCaseDTO) throws InvalidAccessTypeException {
		return toCourtCaseDTOList(repository.findAll(Example.of(toCourtCase(courtCaseDTO))));
	}
	
	public CourtCaseDTO saveCourtCase(CourtCaseDTO courtCaseDTO) throws InvalidCourtCaseException, InvalidAccessTypeException {
		if (!validate(courtCaseDTO)) {
			throw new InvalidCourtCaseException();
		}
		return toCourtCaseDTO(repository.save(toCourtCase(courtCaseDTO)));
	}
	
	public List<CourtCaseDTO> saveListCourtCase(List<CourtCaseDTO> cases) throws InvalidCourtCaseException, InvalidAccessTypeException {
		if (!validate(cases)) {
			throw new InvalidCourtCaseException();
		}
		
		return toCourtCaseDTOList(repository.saveAll(toCourtCaseList(cases)));
	}
	
	public void deleteCourtCase(Long id) throws CourtCaseNotFoundException {
		if (!repository.existsById(id)) {
			throw new CourtCaseNotFoundException();
		}
		
		repository.deleteById(id);
	}
}
