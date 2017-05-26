package utils.impl;

import basedao.BaseDao;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.SortKey;
import com.emin.base.domain.Entity;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.base.service.UndeleteableServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.CrudUtilsService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * user : shimingliang
 * time : 2017/3/31
 * des :
 */
@Service("crudUtilsService")
public class CrudUtilsServiceImpl<T extends UndeleteableEntity> extends UndeleteableServiceImpl<T> implements CrudUtilsService<T> {

    @Autowired
    private BaseDao baseDao;

    public CrudUtilsServiceImpl() {
    }

    public void addOrUpdate(T entity) throws Exception {

        if (entity == null) {
            throw new RuntimeException("更新失败，传入参数为null . entity is " + entity);
        }
        Class<?> c = entity.getClass();
        Field idField = null;
        Entity ent = null;

        idField = c.getSuperclass().getDeclaredField("id");
        idField.setAccessible(true); //设置些属性是可以访问的
        /**
         * 存在ID 更新操作
         */
        if (idField.get(entity) != null && !StringUtils.isBlank(idField.get(entity).toString())) {
            String id = idField.get(entity).toString();
            ent = this.findById(Long.valueOf(id));
            if (ent == null) {
                throw new RuntimeException("更新失败,根据ID未能查询到相关对象。 entity is " + entity);
            }
            setFeildValues(entity, (T) ent);

            this.saveOrUpdate((T) ent);
        } else { //新增操作
            entity.setStatus(1);//逻辑删除位  1  正常 -1 删除
            this.saveOrUpdate(entity);
        }

    }

    public void batchAddUpdate(List<T> entitys) throws Exception {

        if (entitys == null || entitys.size() == 0) {
            throw new RuntimeException("更新失败，传入参数为null . entitys is " + entitys);
        }
        for (T entity : entitys) {
            this.addOrUpdate(entity);
        }
    }

    private void setFeildValues(T oldT, T newT) throws Exception {
        Class<?> oldC = oldT.getClass();
        Class<?> newC = newT.getClass();
        Field[] fields = newC.getDeclaredFields();
        Field[] fieldOld = oldC.getDeclaredFields();
        if (fieldOld.length > 0 && fields.length > 0) {
            Object o = null;
            for (int i = 0; i < fields.length; i++) {
                fieldOld[i].setAccessible(true);
                fields[i].setAccessible(true);
                o = fieldOld[i].get(oldT);
                if ("serialVersionUID".equals(fieldOld[i].getName())) {
                    continue;
                }
                if (o != null) {
                    fields[i].set(newT, o);
                }
            }
        }
    }

    public PagedResult queryPageBySql(PageRequest pageRequest, String sql) {
        if (StringUtils.isBlank(sql) || pageRequest == null) {
            throw new RuntimeException("通过sql分页查询失败, sql is " + sql);
        }
        List<Map<String, String>> objectList = baseDao.queryPageBySql(sql, pageRequest.getOffset(), pageRequest.getLimit());
        Integer count = baseDao.getCount(sql.substring(0, 6) + "  count(*)  " + sql.substring(sql.indexOf("from")));
        return new PagedResult(objectList, pageRequest.getOffset(), count);
    }


    public List queryListBySql(String sql) {
        if (StringUtils.isBlank(sql)) {
            throw new RuntimeException("通过sql分页查询失败, sql is " + sql);
        }
        List<Map<String, String>> objectList = baseDao.queryListMapBySql(sql);
        return objectList;
    }


    @Override
    public void save(T entity) {
        super.save(entity);
    }


    @Override
    public void update(T entity) {
        super.update(entity);
    }

    @Override
    public List<T> findAllByStatus(int status, SortKey... sortKeys) {
        return super.findAllByStatus(status, sortKeys);
    }

    @Override
    public List<Long> getIdsByStatus(int status) {
        return super.getIdsByStatus(status);
    }

    @Override
    public int getCountByStatus(int status) {
        return super.getCountByStatus(status);
    }

    @Override
    public PagedResult<T> getPageByStatus(PageRequest pageRequest, int status) {
        return super.getPageByStatus(pageRequest, status);
    }

    @Override
    public void disable(T entity) {
        super.disable(entity);
    }

    @Override
    public List<T> findLimitSizeByBeforeTime(Long time, Integer limit) {
        return super.findLimitSizeByBeforeTime(time, limit);
    }

    @Override
    public List<T> findLimitSizeByAfterTime(Long time) {
        return super.findLimitSizeByAfterTime(time);
    }

    @Override
    public void deleteById(Long entityId) {
        super.deleteById(entityId);
    }

    @Override
    public void delete(T entity) {
        super.delete(entity);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        super.deleteByIds(ids);
    }

    @Override
    public void enableById(Long id) {
        super.enableById(id);
    }

    @Override
    public void enable(T entity) {
        super.enable(entity);
    }

    @Override
    public void enable(T entity, Boolean checkName) {
        super.enable(entity, checkName);
    }

    @Override
    public void enableByIds(Long[] ids) {
        super.enableByIds(ids);
    }

    @Override
    public PreFilter getStatusFilter() {
        return super.getStatusFilter();
    }

    public void setClazz(Class<T> clazz) {
        super.setClazz(clazz);
    }
}
