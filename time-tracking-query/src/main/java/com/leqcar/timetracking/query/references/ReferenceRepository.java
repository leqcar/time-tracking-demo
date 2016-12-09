package com.leqcar.timetracking.query.references;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository<T extends ReferenceEntry> extends JpaRepository<T, String>{

}
