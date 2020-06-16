package br.com.biazus.casemanager.domain.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourtCaseDTO implements CaseSpecification, Serializable {
	
	private static final long serialVersionUID = 4929545270370785503L;
	
	private Long id;
	private String folder;
	private String client;
	private String title;
	private String description;
	private String notes;
	private String responsible;
	private String access;
	private LocalDateTime creationDate;
	private List<String> tags;
	
	@Override
	@JsonIgnore
	public String getAccessDescription() {
		return getAccess();
	}
}
