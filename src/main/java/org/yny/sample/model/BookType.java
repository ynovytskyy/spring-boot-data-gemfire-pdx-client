package org.yny.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //NB needed for PDXSerializer
@AllArgsConstructor
public class BookType {

    private String bookTypeId;
    private String description;

}
