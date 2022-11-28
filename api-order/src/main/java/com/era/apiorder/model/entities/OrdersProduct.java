package com.era.apiorder.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Класс для хранения информации о товаре в заказе, только нужной информации для этого сервиса
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "orders_product")
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
    @Column(name = "product_id", nullable = false)
    private Long productId;

    /**
     * Кол-во товара
     */
    @Min(value = 0, message = "Количество товара не может быть меньше 0")
    @Column(name = "count", nullable = false)
    private Long count;

    /**
     * Заказ которому принадлежит данный продукт
     */
    @ManyToOne
    private Order order;

}
