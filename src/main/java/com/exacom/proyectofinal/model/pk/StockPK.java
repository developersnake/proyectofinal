package com.exacom.proyectofinal.model.pk;

import com.exacom.proyectofinal.model.Autor;
import com.exacom.proyectofinal.model.Libro;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockPK {
    @ManyToOne
    @JoinColumn(name = "autor", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_stock_autor_id"))
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "libro", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_stock_libro_id"))
    private Libro libro;
}
