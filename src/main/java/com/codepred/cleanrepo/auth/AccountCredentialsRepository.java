package com.codepred.cleanrepo.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface AccountCredentialsRepository extends JpaRepository<AccountCredentials, Integer> {

    Optional<AccountCredentials> findByEmail_Email(String email);
}




