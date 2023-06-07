package com.example.domain.user.model;


import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable //主キーのクラスには@Embeddableをつける
@Data
public class SalaryKey implements Serializable {
	private String userId;
	private String yearMonth;

}

