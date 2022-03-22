package sistemabancario.estructuras;

/* Clase que representa a la cola de solicitudes de cada cliente, las cuales
deben de atenderse en orden. */

public class ColaSolicitudes{
    
    private NodoSolicitud cabeza;
    private int max = 5;
    private int conteo;
        
    public ColaSolicitudes(){
        cabeza = null;
        conteo = 0;
    }
    
    public boolean esVacia(){
        return this.cabeza == null;
    }
    
    public boolean esLlena(){
        return this.conteo == this.max;
    }
    
    public int cantidadNodos(){
        return this.conteo;
    }
    
    public NodoSolicitud vistazo(){
        return this.cabeza;
    }
    
    public void vaciar() {
        this.cabeza = null;
        this.conteo = 0;
    }
    
    public void encolar(char solicitud) {
        if(esLlena()) {
            System.out.println("Cola llena");
        } else {
            NodoSolicitud nodo = new NodoSolicitud(Character.toUpperCase(solicitud));
            if (esVacia()){
                cabeza = nodo;
                conteo++;
            } else {
                NodoSolicitud auxiliar = cabeza;
                while(auxiliar.getSiguiente() != null) {
                    auxiliar = auxiliar.getSiguiente();
                }
                auxiliar.setSiguiente(nodo);
                conteo++;
            }
        }
    }
    
    public NodoSolicitud desencolar(){
        if(esVacia()){
            System.out.println("Cola vacia.");
            return null;
        } else {
            NodoSolicitud retornable = cabeza;
            cabeza = cabeza.siguiente;
            conteo--;
            return retornable;
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if (esVacia()) {
            return "[]";
        } else {
            NodoSolicitud aux = new NodoSolicitud();
            aux = cabeza;
            while(aux.siguiente != null) {
                sb.append(aux.solicitud).append("-");
                aux = aux.getSiguiente();
            }
            sb.append(aux.solicitud);
        }
        return sb.toString();
    }
    
    //Pruebas
    
    public static void main(String[] args) {
       ColaSolicitudes solicitudes = new ColaSolicitudes();
       solicitudes.encolar('A');
       solicitudes.encolar('R');
       solicitudes.encolar('G');
       solicitudes.encolar('J');
       solicitudes.encolar('A');
       System.out.println(solicitudes.toString());
       
    }
    
}
