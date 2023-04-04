package modelo;

public class Categoria {

    private String descipcion;

    public Categoria() {
    }
   
    public Categoria(Object[] descipcion) {
        this.descipcion = descipcion[0].toString();
    }

    public Object[] InfoCategoria() {
        Object[] cat = {descipcion};
        return cat;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

}
