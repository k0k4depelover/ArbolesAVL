package ArbolAVL;

public class ArbolAVL {
	NodoAVL raiz;
	public ArbolAVL() {
		raiz=null;
	}
	
	
	public boolean esVacio() {
		return raiz==null;
	}
	public boolean esHoja(NodoAVL nodo) {
		return nodo.izq== null && nodo.der==null;
	}
}
