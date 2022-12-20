package by.bsuir.service;

public abstract class GenericService<T> implements CrudOperations<T> {
    @Override
    public T findById() {
        return null;
    }
}
