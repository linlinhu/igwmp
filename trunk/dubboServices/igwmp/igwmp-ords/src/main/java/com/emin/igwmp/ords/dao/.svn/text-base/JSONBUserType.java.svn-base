package com.emin.igwmp.ords.dao;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.SerializationException;
import org.hibernate.usertype.UserType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

public class JSONBUserType implements UserType {


	private static final ObjectMapper Mapper = new ObjectMapper();

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }

    @Override
    public Class<JSONObject> returnedClass() {
        return JSONObject.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if ((x == null) || (y == null)) {
            return false;
        }
        return x.equals(y);
    }

    /**
     * @throws HibernateException NPE if null
     */
    @Override
    public int hashCode(Object object) throws HibernateException {
        return Objects.requireNonNull(object).hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        final String cellContent = rs.getString(names[0]);
        if (cellContent == null) {
            return null;
        }
        try {
          
            return JSONObject.fromObject(cellContent);
        } catch (Exception ex) {
            throw new HibernateException(ex);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement ps, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            ps.setNull(index, Types.OTHER);
            return;
        }
        try {
            // Map from JsonNode to String
            final StringWriter w = new StringWriter();
            Mapper.writeValue(w, value);
            w.flush();
            ps.setObject(index, w.toString(), Types.OTHER);
        } catch (Exception ex) {
            throw new HibernateException(ex);
        }
    }

    /**
     * Deep copy JsonNode by serializing to bytes with jackson then back to JsonNode
     *
     * @param value object
     * @return Deep Copy
     * @throws HibernateException IOException from mapper read tree
     */
    @Override
    public Object deepCopy(Object value) throws HibernateException {
        try {
            byte[] bytes = Mapper.writeValueAsBytes(value);
            return JSONObject.fromObject(Mapper.readTree(bytes));
        } catch (IOException ex) {
            throw new HibernateException(ex);
        }
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        Object deepCopy = deepCopy(value);

        if (!(deepCopy instanceof Serializable)) {
            throw new SerializationException(
                    String.format("deepCopy of %s is not serializable", value), null);
        }

        return (Serializable) deepCopy;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }
}
