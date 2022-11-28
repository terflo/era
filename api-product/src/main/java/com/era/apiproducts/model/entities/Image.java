package com.era.apiproducts.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Base64;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image {

    /**
     * Уникальный индификатор изображения
     */
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Байт-код изображения
     */
    @Lob
    @Column(name = "bytes", nullable = false)
    private byte[] bytes;

    /**
     * Тип изображения
     */
    @Column(name = "type", nullable = false)
    private String type;

    /**
     * К какому товару относится изображение
     */
    @ManyToOne
    private Product product;

    /**
     * Метод получения base64 хэш из байт-кода изображения
     * @return строка base64
     */
    public String getBase64ImageWithType() {
        return "data:" + this.type + ";base64," + Base64.getEncoder().encodeToString(this.bytes);
    }
}
