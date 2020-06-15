package br.com.biazus.casemanager.domain.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "case")
public class CourtCase implements Serializable {
	
	private static final long serialVersionUID = 4947422174891821179L;

	@Id
	private Long id;
	private String folder;
	private String client;
	private String title;
	private String description;
	private String notes;
	private String responsible;
	private AccessType access;
	@CreatedDate
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
		
		return true;
	}
}
