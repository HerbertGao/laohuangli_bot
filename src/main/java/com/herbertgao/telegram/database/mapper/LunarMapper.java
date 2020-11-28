package com.herbertgao.telegram.database.mapper;

import com.herbertgao.telegram.database.entity.Lunar;
import com.herbertgao.telegram.database.entity.LunarExample;
import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LunarMapper {
    long countByExample(LunarExample example);

    int deleteByExample(LunarExample example);

    /**
     * delete by primary key
     * @param gregoriandatetime primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(LocalDate gregoriandatetime);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Lunar record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Lunar record);

    List<Lunar> selectByExample(LunarExample example);

    /**
     * select by primary key
     * @param gregoriandatetime primary key
     * @return object by primary key
     */
    Lunar selectByPrimaryKey(LocalDate gregoriandatetime);

    int updateByExampleSelective(@Param("record") Lunar record, @Param("example") LunarExample example);

    int updateByExample(@Param("record") Lunar record, @Param("example") LunarExample example);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Lunar record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Lunar record);

    int batchInsert(@Param("list") List<Lunar> list);
}