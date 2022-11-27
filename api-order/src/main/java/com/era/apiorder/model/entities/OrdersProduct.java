package com.era.apiorder.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Класс для хранения информации о товаре в заказе, только нужной информации для этого сервиса
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordersProduct")
public class OrdersProduct {

    /**
     * Уникальный индификатор записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Уникальный индификатор товара из сервиса ApiProduct
     */
    @Column(name = "productId", nullable = false)
    private Long productId;

    /**
     * Кол-во товара
     */
    @Column(name = "count", nullable = false)
    private Long count;

    /**
     * Заказ которому принадлежит данный продукт
     */
    @ManyToOne
    private Order order;

}
