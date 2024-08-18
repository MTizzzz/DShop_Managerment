package Website2.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "token", length = 1000, nullable = false)
    private String token;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "expiration", nullable = false)
    private Date expiration;
}
