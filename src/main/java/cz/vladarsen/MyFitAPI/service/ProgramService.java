package cz.vladarsen.MyFitAPI.service;

import cz.vladarsen.MyFitAPI.DTO.ProgramDTO;
import cz.vladarsen.MyFitAPI.entity.Program;
import cz.vladarsen.MyFitAPI.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramService {
    private final ProgramRepository programRepository;

    public List<Program> getProgramsByUsername(String username) {
        return programRepository.findByUser_Username(username);
    }


}
