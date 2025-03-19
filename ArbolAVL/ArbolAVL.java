package ArbolAVL;
import java.lang.Math;

public class ArbolAVL {
    NodoAVL raiz;

    public ArbolAVL() {
        raiz = null;
    }

    int obtenerAltura(NodoAVL nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    int obtenerFactorEquilibrio(NodoAVL nodo) {
        return (nodo == null) ? 0 : obtenerAltura(nodo.getHijoIzquierdo()) - obtenerAltura(nodo.getHijoDerecho());
    }

    NodoAVL rotacionDerecha(NodoAVL nodo) {
        NodoAVL nuevoPadre = nodo.getHijoIzquierdo();
        nodo.setHijoIzquierdo(nuevoPadre.getHijoDerecho());
        nuevoPadre.setHijoDerecho(nodo);

        // Actualizar altura
        nodo.setAltura(Math.max(obtenerAltura(nodo.getHijoIzquierdo()), obtenerAltura(nodo.getHijoDerecho())) + 1);
        nuevoPadre.setAltura(Math.max(obtenerAltura(nuevoPadre.getHijoIzquierdo()), obtenerAltura(nuevoPadre.getHijoDerecho())) + 1);

        // Actualizar factor de equilibrio
        nodo.setFactorEquilibrio(obtenerFactorEquilibrio(nodo));
        nuevoPadre.setFactorEquilibrio(obtenerFactorEquilibrio(nuevoPadre));

        return nuevoPadre;
    }

    NodoAVL rotacionIzquierda(NodoAVL nodo) {
        NodoAVL nuevoPadre = nodo.getHijoDerecho();
        nodo.setHijoDerecho(nuevoPadre.getHijoIzquierdo());
        nuevoPadre.setHijoIzquierdo(nodo);

        // Actualizar altura
        nodo.setAltura(Math.max(obtenerAltura(nodo.getHijoIzquierdo()), obtenerAltura(nodo.getHijoDerecho())) + 1);
        nuevoPadre.setAltura(Math.max(obtenerAltura(nuevoPadre.getHijoIzquierdo()), obtenerAltura(nuevoPadre.getHijoDerecho())) + 1);

        // Actualizar factor de equilibrio
        nodo.setFactorEquilibrio(obtenerFactorEquilibrio(nodo));
        nuevoPadre.setFactorEquilibrio(obtenerFactorEquilibrio(nuevoPadre));

        return nuevoPadre;
    }

    NodoAVL rotacionIzquierdaDerecha(NodoAVL nodo) {
    	nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
    	return rotacionDerecha(nodo);
    }
    
    NodoAVL rotacionDerechaIzquierda(NodoAVL nodo) {
    	nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
    	return rotacionIzquierda(nodo);
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public boolean esHoja(NodoAVL nodo) {
        return nodo.izq == null && nodo.der == null;
    }
}
