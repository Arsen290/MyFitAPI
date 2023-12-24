package cz.vladarsen.MyFitAPI.service;

import cz.vladarsen.MyFitAPI.DTO.ProgramDTO;
import cz.vladarsen.MyFitAPI.entity.Program;
import cz.vladarsen.MyFitAPI.entity.User;
import cz.vladarsen.MyFitAPI.repository.ProgramRepository;
import cz.vladarsen.MyFitAPI.repository.UserRepository;
import cz.vladarsen.MyFitAPI.request.CreateProgramRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramService {
    private final ProgramRepository programRepository;
    private final UserRepository userRepository;

    //Get all programs
    public List<Program> getProgramsByUsername(String username) {
        return programRepository.findByUser_Username(username);
    }

    //Create program
    public Program createProgram(String username, CreateProgramRequest request) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));;

        //Build object program
        Program program = Program.builder()
                .programName(request.getProgramName())
                .user(user)
                .build();
        //Add date when add entity in db
        LocalDateTime localDateTime = LocalDateTime.now();
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        program.setCreated(currentDate);
        program.setUpdated(currentDate);

        programRepository.save(program);
        log.info("IN createProgram - program {} for user {} successfully created. ",program,username);
        return program;
    }
    //Delete program
    public void deleteProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found"));
        programRepository.delete(program);
        log.info("IN deleteProgram - program {} successfully deleted.", program.getProgramName());
    }


}
