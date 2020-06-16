package br.com.biazus.casemanager.domain.model.validator;

import java.util.List;

import org.springframework.util.StringUtils;

import br.com.biazus.casemanager.domain.model.entity.AccessType;
import br.com.biazus.casemanager.domain.model.entity.CaseSpecification;

public class CourtCaseValidator {
	
	public static <E extends CaseSpecification> Boolean validate(List<E> specifications) {
		return specifications.stream().anyMatch(specification -> validate(specification));
	}
	
	public static Boolean validate(CaseSpecification specification) {
		if (StringUtils.isEmpty(specification.getClient())) {
			return false;
		}
		
		if (StringUtils.isEmpty(specification.getTitle())) {
			return false;
		}
		
		if (StringUtils.isEmpty(specification.getResponsible())) {
			return false;
		}
		
		if (!StringUtils.isEmpty(specification.getFolder()) && specification.getFolder().length() > 40) {
			return false;
		}
		
		if (!AccessType.isValid(specification.getAccessDescription())) {
			return false;
		}
		
		return true;
	}
}
