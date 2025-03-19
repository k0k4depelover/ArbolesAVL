package ArbolAVL;

public class ArbolAVL {
    NodoAVL raiz;

    public ArbolAVL() {
        raiz = null;
    }

    int obtenerAltura(NodoAVL nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    int obtenerFactorEquilibrio(NodoAVL nodo) {
        return (nodo == null) ? 0 : obtenerAltura(nodo.getIzquierda()) - obtenerAltura(nodo.getDerecha());
    }

    NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.getIzquierda();
        NodoAVL T2 = x.getDerecho();
        x.setDerecho(y);
        y.setIzquierdo(T2);
        y.altura = Math.max(obtenerAltura(y.getIzquierda()), obtenerAltura(y.getDerecha())) + 1;
        x.altura = Math.max(obtenerAltura(x.getIzquierda()), obtenerAltura(x.getDerecho())) + 1;
        y.FE = obtenerFactorEquilibrio(y);
        x.FE = obtenerFactorEquilibrio(x);
        return x;
    }

    NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.getDerecho();
        NodoAVL T2 = y.getIzquierda();
        y.setIzquierdo(x);
        x.setDerecho(T2);
        x.altura = Math.max(obtenerAltura(x.getIzquierda()), obtenerAltura(x.getDerecho())) + 1;
        y.altura = Math.max(obtenerAltura(y.getIzquierda()), obtenerAltura(y.getDerecho())) + 1;
        x.FE = obtenerFactorEquilibrio(x);
        y.FE = obtenerFactorEquilibrio(y);
        return y;
    }

    NodoAVL rotarIzquierdaDerecha(NodoAVL nodo) {
        nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierda()));
        return rotarDerecha(nodo);
    }

    NodoAVL rotarDerechaIzquierda(NodoAVL nodo) {
        nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
        return rotarIzquierda(nodo);
    }

    NodoAVL balancear(NodoAVL nodo) {
        int factorEquilibrio = obtenerFactorEquilibrio(nodo);
        if (factorEquilibrio > 1) {
            if (obtenerFactorEquilibrio(nodo.getIzquierda()) >= 0) {
                return rotarDerecha(nodo);
            } else {
                return rotarIzquierdaDerecha(nodo);
            }
        }
        if (factorEquilibrio < -1) {
            if (obtenerFactorEquilibrio(nodo.getDerecho()) <= 0) {
                return rotarIzquierda(nodo);
            } else {
                return rotarDerechaIzquierda(nodo);
            }
        }
        return nodo;
    }

    public NodoAVL insertar(NodoAVL nodo, int valor) {
        if (nodo == null) {
            return new NodoAVL(valor);
        }
        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(insertar(nodo.getIzquierda(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecho(insertar(nodo.getDerecho(), valor));
        } else {
            return nodo;
        }
        nodo.altura = Math.max(obtenerAltura(nodo.getIzquierda()), obtenerAltura(nodo.getDerecho())) + 1;
        nodo.FE = obtenerFactorEquilibrio(nodo);
        return balancear(nodo);
    }

    public NodoAVL eliminar(NodoAVL raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }
        if (valor < raiz.getValor()) {
            raiz.setIzquierdo(eliminar(raiz.getIzquierda(), valor));
        } else if (valor > raiz.getValor()) {
            raiz.setDerecho(eliminar(raiz.getDerecho(), valor));
        } else {
            if (raiz.getIzquierdo() == null || raiz.getDerecho() == null) {
                NodoAVL temp = null;
                if (temp == raiz.getIzquierdo()) {
                    temp = raiz.getDerecho();
                } else {
                    temp = raiz.getIzquierdo();
                }
                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else {
                    raiz = temp;
                }
            } else {
                NodoAVL temp = encontrarMinimo(raiz.getDerecho());
                raiz.setValor(temp.getValor());
                raiz.setDerecho(eliminar(raiz.getDerecho(), temp.getValor()));
            }
        }
        if (raiz == null) {
            return raiz;
        }
        raiz.altura = Math.max(obtenerAltura(raiz.getIzquierda()), obtenerAltura(raiz.getDerecho())) + 1;
        raiz.FE = obtenerFactorEquilibrio(raiz);
        return balancear(raiz);
    }

    public NodoAVL encontrarMinimo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.getIzquierda() != null) {
            actual = actual.getIzquierda();
        }
        return actual;
    }

    public void imprimirArbol(NodoAVL nodo) {
        if (nodo != null) {
            imprimirArbol(nodo.getIzquierda());
            System.out.print(nodo.getValor() + " ");
            imprimirArbol(nodo.getDerecho());
        }
    }

    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        arbol.raiz = arbol.insertar(arbol.raiz, 20);
        arbol.raiz = arbol.insertar(arbol.raiz, 10);
        arbol.raiz = arbol.insertar(arbol.raiz, 30);
        arbol.raiz = arbol.insertar(arbol.raiz, 25);
        arbol.raiz = arbol.insertar(arbol.raiz, 5);
        System.out.println("Árbol después de inserciones:");
        arbol.imprimirArbol(arbol.raiz);
        System.out.println();
        arbol.raiz = arbol.eliminar(arbol.raiz, 10);
        System.out.println("Árbol después de eliminar 10:");
        arbol.imprimirArbol(arbol.raiz);
    }
}
