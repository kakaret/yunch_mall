package com.study.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//需要作为消息发不出去的对象 他的类型必须是可序列化的 (实现Serializable)
public class Person implements Serializable {
    private Integer id;
    private String name;
    private String nickname;
}
