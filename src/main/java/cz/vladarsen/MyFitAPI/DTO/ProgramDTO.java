package cz.vladarsen.MyFitAPI.DTO;

import cz.vladarsen.MyFitAPI.entity.Program;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgramDTO {
    private Long id;
    private String programName;
    private Date created;


    public static ProgramDTO from(Program program) {
        return ProgramDTO.builder()
                .id(program.getId())
                .programName(program.getProgramName())
                .created(program.getCreated())
                .build();
    }
}
