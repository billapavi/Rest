package com.rotbot.billa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rotbot.billa.stocks.Stocks;
import com.rotbot.billa.stocks.SuperStock;
@Component
@Repository
public interface StockRepository extends JpaRepository<SuperStock,String>{
 
}
