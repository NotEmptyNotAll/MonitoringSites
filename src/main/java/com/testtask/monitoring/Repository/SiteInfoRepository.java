package com.testtask.monitoring.Repository;

import com.testtask.monitoring.Entity.SiteInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface SiteInfoRepository extends CrudRepository<SiteInfo, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE site_info  SET status = :status WHERE  id = :id", nativeQuery = true)
    int update(@Param("id") Long id, @Param("status") String status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE site_info  SET monitoring_active = :monitoringActive WHERE  id = :id", nativeQuery = true)
    int updateMonitoringActive(@Param("id") Long id, @Param("monitoringActive") boolean active);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE site_info  SET monitoring_active = :monitoringActive, min = :min, max = :max, seconds=:seconds   WHERE  id = :id", nativeQuery = true)
    int updateSiteInfo(@Param("id") Long id, @Param("max") float max,
                       @Param("min") float min, @Param("monitoringActive") boolean active,
                       @Param("seconds") int seconds);
}
