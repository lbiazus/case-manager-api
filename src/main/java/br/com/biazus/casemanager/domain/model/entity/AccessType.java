package br.com.biazus.casemanager.domain.model.entity;

import java.util.Arrays;

import br.com.biazus.casemanager.exception.InvalidAccessTypeException;

public enum AccessType {
	PUBLIC, PRIVATE;

	public static boolean isValid(String accessType) {
		return Arrays.stream(values()).anyMatch(type -> type.name().equals(accessType));
	}
	
	public static AccessType findByName(String name) throws InvalidAccessTypeException {
		return Arrays.stream(values()).filter(type -> type.name().equals(name)).findFirst().orElseThrow(InvalidAccessTypeException::new);
	}
}
