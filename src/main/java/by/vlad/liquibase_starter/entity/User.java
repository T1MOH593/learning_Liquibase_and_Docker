package by.vlad.liquibase_starter.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company"})
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id") // company_id
    private Company company;

}












