package ArbolAVL;

public class NodoAVL {
    int dato, FE, altura;
    NodoAVL izq, der;
    
    // Constructor
    public NodoAVL(int dato) {
        this.dato = dato;
        this.FE = 0;
        this.altura=1;
        this.izq = this.der = null;
    }
    public int getAltura(){
        return this.altura;
    }

    // Obtener el valor del nodo
    public int getValor() {
        return this.dato;
    }

    // Modificar el valor del nodo
    public void setValor(int valor) {
        this.dato = valor;
    }

    // Obtener el factor de equilibrio
    public int getFactorEquilibrio() {
        return this.FE;
    }

    // Modificar el factor de equilibrio
    public void setFactorEquilibrio(int fe) {
        this.FE = fe;
    }

    // Obtener el hijo izquierdo
    public NodoAVL getHijoIzquierdo() {
        return this.izq;
    }

    // Modificar el hijo izquierdo
    public void setHijoIzquierdo(NodoAVL izq) {
        this.izq = izq;
    }

    // Obtener el hijo derecho
    public NodoAVL getHijoDerecho() {
        return this.der;
    }

    // Modificar el hijo derecho
    public void setHijoDerecho(NodoAVL der) {
        this.der = der;
    }
}
