package ArbolAVL;

public class ArbolAVL {
	NodoAVL raiz;
	public ArbolAVL() {
		raiz=null;
	}
	int obtenerAltura(NodoAVL nodo){
            return (nodo==null)? 0: nodo.getAltura();
        }
	int obtenerFactorEquilibrio(NodoAVL nodo){
            return (nodo==null)? 0: obtenerAltura(nodo.getIzquierda())- obtenerAltura(nodo.getDerecha());
        }
        
        
	public boolean esVacio() {
		return raiz==null;
	}
	public boolean esHoja(NodoAVL nodo) {
		return nodo.izq== null && nodo.der==null;
	}
}
