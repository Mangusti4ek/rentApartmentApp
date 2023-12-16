package com.example._rentapartmetnproduct.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "booking_history")
public class BookingHistoryProductEntity {
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
    private ApartmentInfoProductEntity apartmentInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_info")
    private UserApplicationProductEntity userApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product_info")
    private ProductInfoProductEntity productInfo;

}
