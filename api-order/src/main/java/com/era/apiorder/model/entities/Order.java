package com.era.apiorder.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "orders")
public class Order {

    /**
     * Уникальный индификатор записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Pattern(regexp = "\\\\d{10}|(?:\\\\d{3}-){2}\\\\d{4}|\\\\(\\\\d{3}\\\\)\\\\d{3}-?\\\\d{4}")
    @Column(name = "tel_number", nullable = false)
    private String telNumber;

    /**
     * Тип доставки
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Delivery delivery;

    /**
     * Тип оплаты
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private PayType payType;

    /**
     * Товары указанные в заказе
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrdersProduct> ordersProducts;

    /**
     * Дата создания заказа
     */
    @CreatedDate
    @Column(name = "date", nullable = false)
    private Date date;

}
