package com.alekseytyan.logiweb.entity.auth;

import com.alekseytyan.logiweb.util.date.ExpiryDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Table(name = "VERIFICATION")
@NoArgsConstructor
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "VerificationToken.findByToken",
                query = "SELECT vt from VerificationToken vt where vt.token = :token")
})
public class VerificationToken {


    public VerificationToken(User user, String token) {
        this.token = token;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String token;
  
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "USER_EMAIL")
    private User user;
    
    private Date expiryDate = ExpiryDate.calculateExpiryDate();

}