package com.example.microservice.crypto;

import com.example.microservice.helper.KeyServer;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Service
@Import(KeyServer.class)
public class EncryptedValue extends ImmutableType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[] { Types.VARCHAR  };
    }

    @Override
    public Class<?> returnedClass() {
        return String.class;
    }


    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        final int key = 10;
        //Owner is the class form which we try to get the field 'id'
//        Long l= 0L;
//        try {
//            Field f = owner.getClass().getField("id");
//            l = (Long) f.get(owner);
//            System.out.println(l);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
        final Object o = rs.getObject(names[0]);
        try {
            int i = Integer.parseInt((String)o );
            return Encryption.decrypt(i, key);
        } catch (Exception ignored) {

        }
        return Encryption.decrypt((String) o, key);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        final int key = 10;
        if (value instanceof String) {
                final String returnValue = Encryption.encrypt((String) value, key);
                st.setString(index, returnValue);
        } else {
            if (value == null) {
                st.setNull(index, Types.VARCHAR);
            }
            else {
                final Integer returnValue = Encryption.encrypt((int) value, key);
                st.setInt(index, returnValue);
            }
        }
    }

}
