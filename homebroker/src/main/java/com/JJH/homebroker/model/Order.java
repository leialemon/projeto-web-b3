package com.JJH.homebroker.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

@Getter @Setter
@Entity
@RequestMapping("api/v1/orders")
public class Order{


}
