package basedao.impl;

import basedao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository(value = "baseDao")
public class BaseDaoImpl implements BaseDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session getSession() {    
        return sessionFactory.openSession();
    }

    public List queryBySql(String sql) {
        List<Object[]> list = null;
        Session session = null;
        try {
            session = getSession();
            list = session.createSQLQuery(sql).list();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(session!=null || session.isOpen()) {
                session.close();
                session = null;
            }
        }
        return list;
    }

    public List queryListMapBySql(String sql) {
        List<Map<String,String>> list = null;
        Session session = null;
        try {
            Query query =session.createSQLQuery(sql);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//把结果集转为Map
            list = query.list();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(session!=null || session.isOpen()) {
                session.close();
                session = null;
            }
        }
        return list;
    }

    /**
     * 分页查询
     * @param sql 查询的条件
     * @param offset 开始记录
     * @param length 一次查询几条记录
     * @return 返回查询记录集合
     */
    public List queryPageBySql(String sql,int offset, int length) {
        List<Map<String,String>> list = null;
        Session session = null;
        try {
            session = getSession();
            Query query =session.createSQLQuery(sql);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//把结果集转为Map
            query.setFirstResult(offset);
            query.setMaxResults(length);
            list = query.list();
//            query.
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(session!=null || session.isOpen()) {
                session.close();
                session = null;
            }
        }
        return list;
    }
	public void execSql(String sql) {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			session.createSQLQuery(sql).executeUpdate();
			session.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(session!=null || session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}

    public Integer getCount(String sql){
        List list = queryBySql(sql);
        if(list.size()>0){
            return Integer.parseInt(list.get(0).toString());
        }else {
            return 0;
        }
    }

}
