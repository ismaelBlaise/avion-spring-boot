package com.s6.avion.pk;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConfVolId implements Serializable {
    private Integer vol;
    private Integer classe;
    private Integer categorieAge;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfVolId)) return false;
        ConfVolId that = (ConfVolId) o;
        return Objects.equals(vol, that.vol) &&
               Objects.equals(classe, that.classe) &&
               Objects.equals(categorieAge, that.categorieAge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vol, classe, categorieAge);
    }
}
