package com.micka;

import com.micka.dto.Account;
import com.micka.dto.User;
import com.micka.entity.AccountEntity;
import com.micka.entity.UserEntity;

public class ObjectProvider {

    public static Object provideObjectByClass(Class<?> klass) {
        if (klass == User.class) {
            return User.builder()
                    .withId(1)
                    .withFirstName("foo")
                    .withLastName("bar")
                    .withEmail("foo@bar")
                    .withPassword("123")
                    .build();
        }else if(klass == Account.class){
            return new Account(1, 100, 0.1,1 );
        }else if (klass == AccountEntity.class){
            return new AccountEntity(1, 100, 0.1, new UserEntity(1) );
        }
        return new UserEntity(1, "foo", "bar", "foo@bar", "123");
    }
}
