package cz.vladarsen.MyFitAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "programs")
public class Program extends BaseEntity{

    @Column(name = "program_name")
    private String programName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
