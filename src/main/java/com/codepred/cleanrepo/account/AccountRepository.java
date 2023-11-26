package com.codepred.cleanrepo.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByEmail_Email(String email);

}
