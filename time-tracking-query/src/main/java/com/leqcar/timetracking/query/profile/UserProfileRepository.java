package com.leqcar.timetracking.query.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserProfileRepository extends JpaRepository<UserProfileEntry, String> {

}
