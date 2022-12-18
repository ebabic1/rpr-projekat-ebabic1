package ba.unsa.etf.rpr.domain;

/**
 * Interface that makes sure that classes have an id field
 */
public interface IDable {
    void setId(int id);
    int getId();
}
