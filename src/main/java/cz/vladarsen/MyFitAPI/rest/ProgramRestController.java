package cz.vladarsen.MyFitAPI.rest;

import cz.vladarsen.MyFitAPI.DTO.ProgramDTO;
import cz.vladarsen.MyFitAPI.entity.Program;
import cz.vladarsen.MyFitAPI.request.CreateProgramRequest;
import cz.vladarsen.MyFitAPI.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProgramRestController {
    private final ProgramService programService;
    // Endpoint to get all programs for a user
    @GetMapping("/programs")
    public ResponseEntity<List<ProgramDTO>> getPrograms(Authentication authentication) {
        String username = authentication.getName();
        List<Program> programs = programService.getProgramsByUsername(username);
        log.info("IN getPrograms - programs for user with username: {} successfully found. {}",username,programs);
        List<ProgramDTO> listPrograms = programs.stream()
                .map(ProgramDTO::from)
                .collect(Collectors.toList());
        log.info("IN getPrograms - programs for user with username: {} successfully converted to DTOs. {}",username,listPrograms);
        return ResponseEntity.ok(listPrograms);
    }

    @PostMapping("/create_program")
    public ResponseEntity<ProgramDTO> createProgram(@RequestBody CreateProgramRequest createProgramRequest, Authentication authentication) {
        String username = authentication.getName();
        Program program = programService.createProgram(username, createProgramRequest);
        log.info("IN createProgram - program for user with username: {} successfully created. {}", username, program);
        ProgramDTO programDTO = ProgramDTO.from(program);
        log.info("IN createProgram - program for user with username: {} successfully converted to DTO. {}",username,programDTO);
        return ResponseEntity.ok(programDTO);
    }
    @DeleteMapping("/delete_program/{programId}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long programId) {
        programService.deleteProgram(programId);
        log.info("IN deleteProgram - program with id: {} successfully deleted.", programId);
        return ResponseEntity.ok().build();
    }
}
