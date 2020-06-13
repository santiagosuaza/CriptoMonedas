package com.cripto.criptoMpnedas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Currency;
import java.util.Date;

@Entity
@Table(name ="tbl_quote")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("currency_name")
    @NotBlank(message = "El name no puede ser vacio")
    @Column(name = "currency_name", unique = true)
    private String name;
    @NotEmpty(message = "El symbol no puede ser vacio")
    private  String symbol;
    @Positive(message = "El price debe ser positivo")
    @Column(name = "quote_price")
    private  Double prices;
    private Date lastUpate;

    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="currency_id")
    private CriptoCurrency criptocurrency;

}
