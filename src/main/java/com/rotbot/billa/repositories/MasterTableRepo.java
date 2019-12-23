package com.rotbot.billa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rotbot.billa.stocks.MainStockID;
import com.rotbot.billa.stocks.MasterStockTable;
@Component
@Repository
public interface MasterTableRepo extends JpaRepository<MasterStockTable	,MainStockID>{
	List<MasterStockTable> findByIdStockNameAndIdTimeStampLike(String stockName,String timeStamp);
}
