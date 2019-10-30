package com.dallab.cattoy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.NumberFormat;

// JPA가 관리하는 클래스로 설정
// 기본생성자 필수
// final 필드 불가
@Entity
// 테이블을 해당 name으로 생성해준다 설저 안할 시 클래스명으로 테이블 생성
@Table(name="dugi")
//
@Builder
// Jpa 사용을 위해서는 기본생성자가 필요하다고 함.
// 기본생성자 생성
@NoArgsConstructor
// 모든 필드 기본생성자 생성
@AllArgsConstructor
public class Product {

    // 기본키 반드시 하나의 Entity에는 한개이상 존재해야 한다.
    // 기본키는 Long 타입으로 한다.
    @Id
    // pk 타입설정 : 더 알아보자
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    // 해당 name을 컬럼으로 만들고 안할 시 필드명으로 생성
    @Column(name="NAME", length =  10, nullable = false)
    private String name;

    @Getter
    private String maker;
    private Integer price;
    private String imagUrl;

    public String getPriceWithComma() {
        return NumberFormat.getInstance().format(price);
    }

    public String getImageUrl() {
        return imagUrl == null ? "" : imagUrl;
    }

    public void changeImageUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }
}
