package net.nrasoft.candb.model;



//@MappedSuperclass
public class NamedEntity extends BaseEntity {

  //  @Column(name = "name")
  // @NotEmpty
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //@Override
    public String toString() {
        return this.getName();
    }

}