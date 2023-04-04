package modelo;

public class Clientes {

    private String Tip_Docu, DNI, Nombres, Apellidos, Sexo, email, direccion, Usuario, Fecha_Reg, Hora_Reg;
    private int Celular;

    // Constructores
    public Clientes() {
    }

    public Clientes(Object[] clientes) {
        this.Tip_Docu = clientes[0].toString();
        this.DNI = clientes[1].toString();
        this.Nombres = clientes[2].toString();
        this.Apellidos = clientes[3].toString();
        this.Sexo = clientes[4].toString();
        this.Celular = Integer.parseInt(clientes[5].toString());
        this.email = clientes[6].toString();
        this.direccion = clientes[7].toString();
        this.Usuario = clientes[8].toString();
        this.Fecha_Reg = clientes[9].toString();
        this.Hora_Reg = clientes[10].toString();
    }

    //Obtener Informacion
    public Object[] getInformacionClientes() {
        Object arreglo[] = {getTip_Docu(), getDNI(), getNombres(), getApellidos(), getSexo(), getCelular(), getEmail(),
            getFecha_Reg(), getUsuario()};
        return arreglo;
    }

    // Metodos Getter y Setters
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getFecha_Reg() {
        return Fecha_Reg;
    }

    public void setFecha_Reg(String Fecha_Reg) {
        this.Fecha_Reg = Fecha_Reg;
    }

    public String getHora_Reg() {
        return Hora_Reg;
    }

    public void setHora_Reg(String Hora_Reg) {
        this.Hora_Reg = Hora_Reg;
    }

    public int getCelular() {
        return Celular;
    }

    public void setCelular(int Celular) {
        this.Celular = Celular;
    }

    public String getTip_Docu() {
        return Tip_Docu;
    }

    public void setTip_Docu(String Tip_Docu) {
        this.Tip_Docu = Tip_Docu;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

}
