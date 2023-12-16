package com.example._rentapartmetnproduct.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@ToString
@Table(name = "user_info")
public class UserApplicationProductEntity {

    @Id
    @SequenceGenerator(name = "user_infoSequence", sequenceName = "user_info_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_infoSequence")
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    /**
     * переменная логин являеться почтовым адресом клиента
     */
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "parent_city")
    private String parentCity;
    @Column(name = "date_time_registration")
    private LocalDateTime dateTimeRegistration;

    @Column(name = "booking_count")
    private Integer bookingCount;

    @Column(name = "commercial")
    private Boolean commercial;

    @Column(name = "token")
    private String token;

}
