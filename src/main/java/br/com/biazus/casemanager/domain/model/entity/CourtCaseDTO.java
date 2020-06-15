package br.com.biazus.casemanager.domain.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourtCaseDTO implements Serializable {
	
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
	
	public Boolean validate() {
		if (StringUtils.isEmpty(client)) {
			return false;
		}
		
		if (StringUtils.isEmpty(title)) {
			return false;
		}
		
		if (StringUtils.isEmpty(responsible)) {
			return false;
		}
		
		if (!StringUtils.isEmpty(folder) && folder.length() > 40) {
			return false;
		}
		
		if (!AccessType.isValid(access)) {
			return false;
		}
		
		return true;
	}
}
