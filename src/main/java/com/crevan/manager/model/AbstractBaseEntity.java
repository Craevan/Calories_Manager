package com.crevan.manager.model;

public class AbstractBaseEntity {

    public static final int START_SEQ = 100000;

    protected Integer id;

    protected AbstractBaseEntity() {

    }

    protected AbstractBaseEntity(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
