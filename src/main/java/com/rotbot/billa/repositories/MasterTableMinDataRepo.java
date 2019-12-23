package com.rotbot.billa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rotbot.billa.stocks.MainStockID;
import com.rotbot.billa.stocks.MasterStockTableMinData;
@Component
@Repository
public interface MasterTableMinDataRepo extends JpaRepository<MasterStockTableMinData	,MainStockID>{
 
	List<MasterStockTableMinData> findByIdLike(MainStockID id);

	List<MasterStockTableMinData> findByIdStockName(String upperCase);
	List<MasterStockTableMinData> findByIdStockNameAndIdTimeStampLike(String idStockName,String idTimeStamp);
}
