package com.crevan.manager.util;

import com.crevan.manager.model.AbstractBaseEntity;
import com.crevan.manager.util.exception.NotFoundException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(final T object, final int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(final boolean found, final int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(final T object, final String message) {
        checkNotFound(object != null, message);
        return object;
    }

    public static void checkNotFound(final boolean found, final String message) {
        if (!found) {
            throw new NotFoundException("Not found Entity with " + message);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(final AbstractBaseEntity entity, final int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}
