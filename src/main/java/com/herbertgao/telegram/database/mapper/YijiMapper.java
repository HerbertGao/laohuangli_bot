package com.herbertgao.telegram.database.mapper;

import com.herbertgao.telegram.database.entity.Yiji;
import com.herbertgao.telegram.database.entity.YijiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YijiMapper {
    long countByExample(YijiExample example);

    int deleteByExample(YijiExample example);

    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Yiji record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Yiji record);

    List<Yiji> selectByExample(YijiExample example);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Yiji selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Yiji record, @Param("example") YijiExample example);

    int updateByExample(@Param("record") Yiji record, @Param("example") YijiExample example);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Yiji record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Yiji record);

    int batchInsert(@Param("list") List<Yiji> list);
}