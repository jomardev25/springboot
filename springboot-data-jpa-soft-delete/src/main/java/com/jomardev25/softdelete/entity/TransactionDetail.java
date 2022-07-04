package com.jomardev25.softdelete.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jomardev25.softdelete.entity.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "T_TRANSACTION_DETAIL")
@IdClass(TransactionDetail.TransactionDetailId.class)
public class TransactionDetail extends BaseEntity {

    private static final long serialVersionUID = -2700555234966165635L;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionDetailId implements Serializable {
        private static final long serialVersionUID = 2209912596164063361L;
        private Long transaction;
        private Long book;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "price", nullable = false)
    private Integer price;

}
