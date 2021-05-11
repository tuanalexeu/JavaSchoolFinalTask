package com.alekseytyan.logiweb.entity.auth;

import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.util.date.ExpiryDate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "PASSWORD_RESET_TOKEN")
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "PasswordResetToken.findByToken",
                query = "SELECT pt FROM PasswordResetToken pt where pt.token = :token")
})
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String token;
 
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "USER_ID")
    private User user;

    private Date expiryDate = ExpiryDate.calculateExpiryDate();


}