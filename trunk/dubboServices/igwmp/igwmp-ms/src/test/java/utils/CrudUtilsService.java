package utils;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.base.service.CRUDService;
import com.emin.base.service.UndeleteableService;

import java.util.List;

/**
 * user : shimingliang
 * time : 2017/3/31
 * des :
 */
public interface CrudUtilsService<T extends UndeleteableEntity> extends CRUDService<T>, UndeleteableService<T> {
    void setClazz(Class<T> aClass);

    /**
     * 根据单个实体更新数据库
     * 如果有ID则查询数据更新
     * 无ID则新增一条
     *
     * @param entity
     */
    public void addOrUpdate(T entity) throws Exception;

    /**
     * 批量更新数据库
     * 如果有ID则查询数据更新
     * 无ID则新增一条
     *
     * @param entitys
     */
    public void batchAddUpdate(List<T> entitys) throws Exception;

    /**
     * 通过sql进行分页查询
     *
     * @param pageRequest
     * @param sql
     * @return
     */
    PagedResult queryPageBySql(PageRequest pageRequest, String sql);


    /**
     * 通过sql进行查询
     * @param sql
     * @return list
     */
    public List queryListBySql(String sql);
}
