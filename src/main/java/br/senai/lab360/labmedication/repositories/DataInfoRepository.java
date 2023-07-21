package br.senai.lab360.labmedication.repositories;

import br.senai.lab360.labmedication.models.personmodels.patientmodels.PatientDataInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataInfoRepository extends JpaRepository<PatientDataInfoResponse, Long> {

    @Query(
            value =
                    "SELECT P.ID as PATIENT_ID, COUNT(M.ID) AS TOTAL_MEDICATION " +
                            "FROM PATIENTS P " +
                            "LEFT JOIN MEDICATIONS M " +
                            "ON M.PATIENT_ID = P.ID " +
                            "GROUP BY P.ID",
            nativeQuery = true
    )
    List<PatientDataInfoResponse> getDataInfo();
}
