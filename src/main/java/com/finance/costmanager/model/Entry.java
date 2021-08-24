package com.finance.costmanager.model;

import com.finance.costmanager.enums.EntryStatus;
import com.finance.costmanager.enums.EntryType;
import lombok.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "entry", schema = "finance")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @ManyToOne // Many entries for one user
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "create_date")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate createDate;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private EntryType type;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private EntryStatus status;
}
