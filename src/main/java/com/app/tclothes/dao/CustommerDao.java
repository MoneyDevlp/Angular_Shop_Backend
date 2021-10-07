package com.app.tclothes.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.tclothes.entity.Custommer;
import com.app.tclothes.entity.Order;
import com.app.tclothes.request.SignupRequest;

@Repository
public interface CustommerDao extends JpaRepository<Custommer, Long>{
	
	Optional<Custommer> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query("SELECT c FROM Custommer c WHERE c.username=?1")
	Custommer findUsername(String username);
	
	@Query(nativeQuery = true,value = "SELECT * FROM custommers "
			+ "INNER JOIN login_roles ON custommers.id = login_roles.user_id WHERE login_roles.role_id=3 AND custommers.delete_flag=0 ORDER BY custommers.id DESC")
	Page<Custommer> findAllCustommer(Pageable pageable);
	
	@Query(nativeQuery = true,value = "SELECT * FROM custommers "
			+ "INNER JOIN login_roles ON custommers.id = login_roles.user_id WHERE login_roles.role_id=3 AND custommers.delete_flag=0 AND custommers.fullname LIKE %?1% ORDER BY custommers.id DESC")
	Page<Custommer> findCustommerByNameAndPage(String fullname, Pageable pageable);
	
	@Query(nativeQuery = true,value = "SELECT * FROM custommers "
			+ "INNER JOIN login_roles ON custommers.id = login_roles.user_id WHERE login_roles.role_id=3 AND custommers.delete_flag=0 AND custommers.id=?1")
	Custommer findCustommersByIdAndFlag(Long id);
	
	@Query(nativeQuery = true,value = "SELECT COUNT(*) FROM custommers "
			+ "INNER JOIN login_roles ON custommers.id = login_roles.user_id WHERE login_roles.role_id=3 AND custommers.delete_flag=0")
	long findCustommerSize();
	
	@Query(nativeQuery = true,value = "SELECT * FROM custommers "
			+ "INNER JOIN login_roles ON custommers.id = login_roles.user_id WHERE login_roles.role_id=3 AND custommers.delete_flag=0 AND custommers.id=?1")
	SignupRequest findCustommerByIdAndFlag(Long id);
	
	@Query(value = "SELECT o.orderTrackingNumber, o.totalQuantity, o.totalPrice, o.adress, o.dateCreated,o.status, o.orderId FROM Order o INNER JOIN Custommer c ON o.custommer.id = c.id "
			+ "WHERE c.username=?1 ORDER BY o.orderId DESC")
	List<Object[]> findOrderByCustommer(String username);
}
