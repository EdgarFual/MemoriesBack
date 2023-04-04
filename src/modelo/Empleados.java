package modelo;

public class Empleados {

    //tipo Documento, nro documento, fecha de nacimiento, nombres, Apellidos, sexo, estado civil,
    //domicilio, celular, operador, email, ocupacion,cargo
    private String Cod_Interno, Tipo_Documento, Nro_Documento, Fecha_Nacimiento, Nombres, Apellidos, Sexo, Estado_Civil,
            domicilio, Operador_Movil, email, Ocupacion, Cargo, Registrador_Usuario, Fecha_Registro,
            Hora_Registro, Status;
    private int Celular;

    public Empleados() {
    }

    public Empleados(Object[] empleados) {
        this.Cod_Interno = empleados[0].toString();
        this.Tipo_Documento = empleados[1].toString();
        this.Nro_Documento = empleados[2].toString();
        this.Fecha_Nacimiento = empleados[3].toString();
        this.Nombres = empleados[4].toString();
        this.Apellidos = empleados[5].toString();
        this.Sexo = empleados[6].toString();
        this.Estado_Civil = empleados[7].toString();
        this.domicilio = empleados[8].toString();
        this.Celular = Integer.parseInt(empleados[9].toString());
        this.Operador_Movil = empleados[10].toString();
        this.email = empleados[11].toString();
        this.Ocupacion = empleados[12].toString();
        this.Cargo = empleados[13].toString();
        this.Status = empleados[14].toString();
        this.Registrador_Usuario = empleados[15].toString();
        this.Fecha_Registro = empleados[16].toString();
        this.Hora_Registro = empleados[17].toString();
    }

    public Object[] InformacionEmpleados() {
        Object[] Registro = {getCod_Interno(), getTipo_Documento(), getNro_Documento(), getNombres(),
            getApellidos(), getSexo(), getCelular(), getEmail(), getCargo()};
        return Registro;
    }

    public String getCod_Interno() {
        return Cod_Interno;
    }

    public void setCod_Interno(String Cod_Interno) {
        this.Cod_Interno = Cod_Interno;
    }

    public String getTipo_Documento() {
        return Tipo_Documento;
    }

    public void setTipo_Documento(String Tipo_Documento) {
        this.Tipo_Documento = Tipo_Documento;
    }

    public String getNro_Documento() {
        return Nro_Documento;
    }

    public void setNro_Documento(String Nro_Documento) {
        this.Nro_Documento = Nro_Documento;
    }

    public String getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(String Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
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

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getEstado_Civil() {
        return Estado_Civil;
    }

    public void setEstado_Civil(String Estado_Civil) {
        this.Estado_Civil = Estado_Civil;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getOperador_Movil() {
        return Operador_Movil;
    }

    public void setOperador_Movil(String Operador_Movil) {
        this.Operador_Movil = Operador_Movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(String Ocupacion) {
        this.Ocupacion = Ocupacion;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getRegistrador_Usuario() {
        return Registrador_Usuario;
    }

    public void setRegistrador_Usuario(String Registrador_Usuario) {
        this.Registrador_Usuario = Registrador_Usuario;
    }

    public String getFecha_Registro() {
        return Fecha_Registro;
    }

    public void setFecha_Registro(String Fecha_Registro) {
        this.Fecha_Registro = Fecha_Registro;
    }

    public String getHora_Registro() {
        return Hora_Registro;
    }

    public void setHora_Registro(String Hora_Registro) {
        this.Hora_Registro = Hora_Registro;
    }

    public int getCelular() {
        return Celular;
    }

    public void setCelular(int Celular) {
        this.Celular = Celular;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }   
    
}
