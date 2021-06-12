package com.alekseytyan.client.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "USER_LOGIWEB")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class User {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

}
