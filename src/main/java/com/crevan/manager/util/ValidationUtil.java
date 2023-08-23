package com.crevan.manager.util;

import com.crevan.manager.model.AbstractBaseEntity;
import com.crevan.manager.util.exception.NotFoundException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;

import javax.validation.*;
import java.util.Set;

public class ValidationUtil {

    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(final T object, final int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static <T> void validate(final T bean) {
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
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

    @NonNull
    public static Throwable getRootCause(@NonNull final Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }

}
