package com.scen.basedao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的mapper
 *
 * @author Scen
 * @date 2018/4/28 9:56
 */
public interface BaseDao<T> extends Mapper<T>, MySqlMapper<T> {
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
