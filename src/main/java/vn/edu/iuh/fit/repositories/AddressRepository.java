package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> , JpaSpecificationExecutor<Address> {
  }