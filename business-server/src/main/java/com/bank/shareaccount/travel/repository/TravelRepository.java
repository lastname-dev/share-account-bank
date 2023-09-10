package com.bank.shareaccount.travel.repository;

import com.bank.shareaccount.group.entity.Access;
import com.bank.shareaccount.group.entity.Group;
import com.bank.shareaccount.travel.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelRepository extends JpaRepository<Travel,Long> {

    Optional<Travel> findByGroup(Group group);
}
