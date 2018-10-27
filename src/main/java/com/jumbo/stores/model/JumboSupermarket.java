package com.jumbo.stores.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This Class contains the list of Jumbo Stores {@link Store}
 * 
 * @author Waqas
 *
 */
@Data
@AllArgsConstructor
public class JumboSupermarket {

    private List<Store> stores;

}
