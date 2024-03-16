package gov.coateam1.model.signatory;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class SignatoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="position")
    private String position;

    @Column(name="active")
    private boolean active;
}
