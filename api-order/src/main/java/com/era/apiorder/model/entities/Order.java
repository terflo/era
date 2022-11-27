package com.era.apiorder.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    /**
     * Уникальный индификатор записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Тип доставки
     */
    @ManyToOne
    private Delivery delivery;

    /**
     * Тип оплаты
     */
    @ManyToOne
    private PayType payType;

    /**
     * Товары указанные в заказе
     */
    @OneToMany(mappedBy = "order")
    private List<OrdersProduct> ordersProducts;

    /**
     * Дата создания заказа
     */
    @CreatedDate
    @Column(name = "date", nullable = false)
    private Date date;

}
