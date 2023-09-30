package com.example._rent_apartment.model.entity;

import com.example._rent_apartment.model.Security.UserApplicationEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "booking_history")
public class BookingHistoryEntity {

    @Id
    @SequenceGenerator(name = "booking_historySequence", sequenceName = "booking_history_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_historySequence")
    private Long id;
    @Column(name = "booking_start_date")
    private LocalDateTime bookingStartDate;
    @Column(name = "booking_end_date")
    private LocalDateTime bookingEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_apartment")
    private ApartmentInfoEntity apartmentInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_info")
    private UserApplicationEntity userApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product_info")
    private ProductInfoEntity productInfo;

}
