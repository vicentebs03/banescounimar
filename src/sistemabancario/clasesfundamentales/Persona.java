package sistemabancario.clasesfundamentales;


public class Persona {
    String cedula;
    String primerNombre;
    String segundoNombre;
    String primerApellido;
    String segundoApellido;
    int edad;
    
   
    //Métodos accesores (getters).
    
    public String getCedula(){
        return this.cedula;
    }
    
    public String getPrimerNombre(){
        return this.primerNombre;
    }
    
    public String getSegundoNombre(){
        return this.segundoNombre;
    }
    
    public String getPrimerApellido(){
        return this.primerApellido;
    }
    
    public String getSegundoApellido(){
        return this.segundoApellido;
    }
    
    public int getEdad(){
        return this.edad;
    }
    
    //Métodos mutadores (setters).
    
    public void setCedula(String cedula_identidad){
        this.cedula = cedula_identidad;
    };
    
    public void setPrimerNombre(String p_nombre){
        this.primerNombre = p_nombre;
    };
    
    public void setSegundoNombre(String s_nombre){
        this.segundoNombre = s_nombre;
    };
    
    public void setPrimerApellido(String p_apellido){
        this.primerApellido = p_apellido;
    };
    
    public void setSegundoApellido(String s_apellido){
        this.segundoApellido = s_apellido;
    };
    
    public void setEdad(int edad_años){
        this.edad = edad_años;
    };
    
    //Impresión en cadena de caractéres
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Cedula de Identidad: ").append(this.cedula).append(".\n");
        builder.append("Nombre: ").append(this.primerNombre).append(" ").append(this.segundoNombre).append(".\n");
        builder.append("Apellidos: ").append(this.primerApellido).append(" ").append(this.segundoApellido).append(".\n");
        builder.append("Edad: ").append(this.edad).append(" años.");
        return builder.toString();
    }
    
    
}
