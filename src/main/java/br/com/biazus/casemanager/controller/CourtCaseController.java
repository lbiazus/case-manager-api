package br.com.biazus.casemanager.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biazus.casemanager.domain.model.entity.CourtCaseDTO;
import br.com.biazus.casemanager.domain.model.service.CourtCaseService;
import br.com.biazus.casemanager.exception.CourtCaseNotFoundException;
import br.com.biazus.casemanager.exception.InvalidAccessTypeException;
import br.com.biazus.casemanager.exception.InvalidCourtCaseException;

@RestController
@RequestMapping(value="/api/v1/case")
public class CourtCaseController {
	
	private final Log LOG = LogFactory.getLog(CourtCaseController.class);
	
	private static final String SUCCESS_MESSAGE_FORMAT = "Case %s Successfully %s";
	private static final String SUCCESS_BATCH_MESSAGE_FORMAT = "%s Cases Successfully %s";
	
	@Autowired
	private CourtCaseService service;
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertCourtCase(@RequestBody CourtCaseDTO caseDTO) {
		try {
			CourtCaseDTO courtCase = service.saveCourtCase(caseDTO);
			return new ResponseEntity<>(String.format(SUCCESS_MESSAGE_FORMAT, courtCase.getId(), "Inserted"), HttpStatus.OK);
		} catch (InvalidCourtCaseException | InvalidAccessTypeException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
		}
	}
	
	@PostMapping(value="/batch", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertBatchCourtCase(@RequestBody List<CourtCaseDTO> courtCases) {
		try {
			List<CourtCaseDTO> cases = service.saveListCourtCase(courtCases);
			return new ResponseEntity<>(String.format(SUCCESS_BATCH_MESSAGE_FORMAT, cases.size(), "Inserted"), HttpStatus.OK);
		} catch (InvalidCourtCaseException | InvalidAccessTypeException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
		}
	}
	
	@PutMapping(consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCourtCase(@RequestBody CourtCaseDTO caseDTO) {
		try {
			CourtCaseDTO courtCase = service.saveCourtCase(caseDTO);
			return new ResponseEntity<>(String.format(SUCCESS_MESSAGE_FORMAT, courtCase.getId(), "Updated"), HttpStatus.OK);
		} catch (InvalidCourtCaseException | InvalidAccessTypeException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
		}
	}
	
	@GetMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CourtCaseDTO> getCourtCase(@PathVariable Long id) {
		return new ResponseEntity<>(service.findCourtCaseById(id), HttpStatus.OK);
	}
	
	@GetMapping(value="/list", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CourtCaseDTO>> getAllCourtCase() {
		return new ResponseEntity<>(service.findAllCourtCase(), HttpStatus.OK);
	}
	
	@PostMapping(value="/search", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CourtCaseDTO>> getAllCourtCaseByFilter(@RequestBody CourtCaseDTO courtCase) throws Exception {
		if (isFilterBlank(courtCase)) {
			return getAllCourtCase();
		}
		
		return new ResponseEntity<>(service.findCourtCaseByExample(courtCase), HttpStatus.OK);
	}
	
	private Boolean isFilterBlank(CourtCaseDTO courtCase) {
		return courtCase == null || StringUtils.isEmpty(courtCase.getClient()) || StringUtils.isEmpty(courtCase.getFolder()) ||
				StringUtils.isEmpty(courtCase.getAccess()) || courtCase.getTags() == null || courtCase.getTags().isEmpty();
	}
	
	@DeleteMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCourtCase(@PathVariable Long id) {
		try {
			service.deleteCourtCase(id);
			return new ResponseEntity<>(String.format(SUCCESS_MESSAGE_FORMAT, id, "Deleted"), HttpStatus.OK);
		} catch (CourtCaseNotFoundException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
		}
	}
}
