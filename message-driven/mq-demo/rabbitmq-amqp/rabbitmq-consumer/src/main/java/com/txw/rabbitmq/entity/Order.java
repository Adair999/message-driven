package com.txw.rabbitmq.entity;

import lombok.Data;
import java.io.Serializable;
/**
 * TODO {@link Order}
 */
@Data
public class Order implements Serializable {
    private String id;
    private String name;
    private String content;
    public Order() {
    }
    public Order(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}