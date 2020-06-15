package br.com.biazus.casemanager.domain.model.repository;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import br.com.biazus.casemanager.domain.model.entity.CourtCase;

public interface CourtCaseRepository extends DatastoreRepository<CourtCase, Long> {

}
