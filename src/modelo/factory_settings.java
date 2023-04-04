package modelo;

public class factory_settings {

    private String CodigoEmpleado, name_user, password_user, type_of_user, company_name, registered_by, registration_date,
            registration_time;
    private int initial_setup;

    // Constructor
    public factory_settings() {
    }

    public factory_settings(Object[] factorySettings) {
        this.CodigoEmpleado = factorySettings[0].toString();
        this.name_user = factorySettings[1].toString();
        this.password_user = factorySettings[2].toString();
        this.type_of_user = factorySettings[3].toString();
        this.company_name = factorySettings[4].toString();
        this.registered_by = factorySettings[5].toString();
        this.registration_date = factorySettings[6].toString();
        this.registration_time = factorySettings[7].toString();
    }

    // obtener y colocar
    public String getCodigoEmpleado() {
        return CodigoEmpleado;
    }

    public void setCodigoEmpleado(String CodigoEmpleado) {
        this.CodigoEmpleado = CodigoEmpleado;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getType_of_user() {
        return type_of_user;
    }

    public void setType_of_user(String type_of_user) {
        this.type_of_user = type_of_user;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getInitial_setup() {
        return initial_setup;
    }

    public void setInitial_setup(int initial_setup) {
        this.initial_setup = initial_setup;
    }

    public String getRegistered_by() {
        return registered_by;
    }

    public void setRegistered_by(String registered_by) {
        this.registered_by = registered_by;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(String registration_time) {
        this.registration_time = registration_time;
    }

}
