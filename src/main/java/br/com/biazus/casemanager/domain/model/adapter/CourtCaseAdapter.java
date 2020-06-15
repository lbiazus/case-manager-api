package br.com.biazus.casemanager.domain.model.adapter;

import java.time.LocalDateTime;

import br.com.biazus.casemanager.domain.model.entity.AccessType;
import br.com.biazus.casemanager.domain.model.entity.CourtCase;
import br.com.biazus.casemanager.domain.model.entity.CourtCaseDTO;
import br.com.biazus.casemanager.exception.InvalidAccessTypeException;

public class CourtCaseAdapter {
	
	private CourtCaseAdapter() {}
	
	public static CourtCase toCourtCase(CourtCaseDTO dto) throws InvalidAccessTypeException {
		CourtCase courtCase = new CourtCase();
		courtCase.setId(dto.getId());
		courtCase.setFolder(dto.getFolder());
		courtCase.setClient(dto.getClient());
		courtCase.setTitle(dto.getTitle());
		courtCase.setDescription(dto.getDescription());
		courtCase.setNotes(dto.getNotes());
		courtCase.setResponsible(dto.getResponsible());
		courtCase.setAccess(AccessType.findByName(dto.getAccess()));
		courtCase.setCreationDate(dto.getId() == null && dto.getCreationDate() == null ? LocalDateTime.now() : dto.getCreationDate());
		courtCase.setTags(dto.getTags());
		return courtCase;
	}
	
	public static CourtCaseDTO toCourtCaseDTO(CourtCase courtCase) {
		CourtCaseDTO courtCaseDTO = new CourtCaseDTO();
		courtCaseDTO.setId(courtCase.getId());
		courtCaseDTO.setFolder(courtCase.getFolder());
		courtCaseDTO.setClient(courtCase.getClient());
		courtCaseDTO.setTitle(courtCase.getTitle());
		courtCaseDTO.setDescription(courtCase.getDescription());
		courtCaseDTO.setNotes(courtCase.getNotes());
		courtCaseDTO.setResponsible(courtCase.getResponsible());
		courtCaseDTO.setAccess(courtCase.getAccess() != null ? courtCase.getAccess().name() : null);
		courtCaseDTO.setCreationDate(courtCase.getCreationDate());
		courtCaseDTO.setTags(courtCase.getTags());
		return courtCaseDTO;
	}

}
