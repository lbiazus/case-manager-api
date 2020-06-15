package br.com.biazus.casemanager.domain.model.service;

import static br.com.biazus.casemanager.domain.model.adapter.CourtCaseAdapter.toCourtCaseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biazus.casemanager.domain.model.adapter.CourtCaseAdapter;
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
		return StreamSupport.stream(repository.findAll().spliterator(), false).map(courtCase -> CourtCaseAdapter.toCourtCaseDTO(courtCase)).collect(Collectors.toList());
	}

	public CourtCaseDTO findCourtCaseById(Long id) {
		return toCourtCaseDTO(repository.findById(id).orElseGet(CourtCase::new));
	}
	
	public CourtCaseDTO saveCourtCase(CourtCaseDTO courtCaseDTO) throws InvalidCourtCaseException, InvalidAccessTypeException {
		if (!courtCaseDTO.validate()) {
			throw new InvalidCourtCaseException();
		}
		return toCourtCaseDTO(repository.save(CourtCaseAdapter.toCourtCase(courtCaseDTO)));
	}
	
	public void deleteCourtCase(Long id) throws CourtCaseNotFoundException {
		if (!repository.existsById(id)) {
			throw new CourtCaseNotFoundException();
		}
		
		repository.deleteById(id);
	}

}
