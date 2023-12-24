package cz.vladarsen.MyFitAPI.repository;

import cz.vladarsen.MyFitAPI.entity.Program;
import cz.vladarsen.MyFitAPI.entity.Role;
import cz.vladarsen.MyFitAPI.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    Optional<Program> findByProgramName(String programName);
    List<Program> findByUser_Username(String username);

}
