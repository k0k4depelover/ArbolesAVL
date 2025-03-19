package avl;

import java.util.Scanner;

// Clase Nodo
class Nodo {
    int dato;
    int FE; // Factor de equilibrio
    Nodo derecho;
    Nodo izquierdo;
    Nodo padre;

    // Constructor del nodo
    public Nodo(int dato) {
        this.dato = dato;
        this.FE = 0;
        this.derecho = null;
        this.izquierdo = null;
        this.padre = null;
    }
}

// Clase ArbolAVL
class ArbolAVL {
    Nodo raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    /* Poda: borrar todos los nodos a partir de uno, incluido */
    public void podar(Nodo nodo) {
        if (nodo != null) {
            podar(nodo.izquierdo); // Podar izquierdo
            podar(nodo.derecho);   // Podar derecho
            nodo.izquierdo = null;
            nodo.derecho = null;
        }
    }

    // Método para eliminar todo el árbol
    public void podarArbol() {
        podar(raiz);
        raiz = null; // Eliminar la referencia al árbol
    }
    
    /* Insertar un dato en el árbol ABB */
    public void Insertar(int dat)
    {
       Nodo padre = null;
       Nodo actual = raiz;

       /* Buscar el dato en el árbol, manteniendo un puntero al nodo padre */
       while(actual != null && dat != actual.dato) {
          padre = actual;
          if(dat < actual.dato) {
              actual = actual.izquierdo;
          } else {
                actual = actual.izquierdo;
       }
    }
        /* Si se ha encontrado el elemento, regresar sin insertar */
        if(actual != null) return;
        /* Si padre es NULL, entonces el árbol estaba vacío, el nuevo nodo será
           el nodo raiz */
        if(padre == null) {
           raiz = new Nodo(dat);
        }
        /* Si el dato es menor que el que contiene el nodo padre, lo insertamos
           en la rama izquierda */
        else if(dat < padre.dato) {

           padre.izquierdo = actual;
           actual.dato = dat;
           actual.izquierdo = actual.derecho = null;
           actual.padre = padre;
           actual.FE = 0;
           equilibrar(padre,-1, true);
        }
        /* Si el dato es mayor que el que contiene el nodo padre, lo insertamos
           en la rama derecha */
        else if(dat > padre.dato) {

           padre.derecho = actual;
           actual.dato = dat;
           actual.izquierdo = actual.derecho = null;
           actual.padre = padre;
           actual.FE = 0;
           equilibrar(padre,-1, false);
       }
     }
    public void equilibrar(Nodo nodo, int balance, boolean eliminar) {
    boolean salir = false;

    while (nodo != null && !salir) {
        nodo.FE += balance; // Usar el valor pasado para modificar el FE

        if (nodo.FE == 0) {
            salir = true; // No hay desbalance
        } else if (nodo.FE == -2) { // Desbalanceo a la izquierda
            if (nodo.izquierdo.FE == 1) {
                rotacionDobleDerecha(nodo);
            } else {
                rotacionSimpleDerecha(nodo);
            }
            salir = true;
        } else if (nodo.FE == 2) { // Desbalanceo a la derecha
            if (nodo.derecho.FE == -1) {
                rotacionDobleIzquierda(nodo);
            } else {
                rotacionSimpleIzquierda(nodo);
            }
            salir = true;
        }

        // Seguir subiendo en el árbol
        if (nodo.padre != null) {
            balance = (nodo.padre.izquierdo == nodo) ? -1 : 1;
        }
        nodo = nodo.padre;
    }
}

        /* Rotación doble a derechas */
        void rotacionDobleDerecha(Nodo nodo)
        {
           Nodo Padre = nodo.padre;
           Nodo P = nodo;
           Nodo Q = P.izquierdo;
           Nodo R = Q.derecho;
           Nodo B = R.izquierdo;
           Nodo C = R.derecho;

           if(Padre != null)
             if(Padre.derecho == nodo) Padre.derecho = R;
             else Padre.izquierdo = R;
           else this.raiz = R;

           /* Reconstruir árbol: */
           Q.derecho = B;
           P.izquierdo = C;
           R.izquierdo = Q;
           R.derecho = P;

           /* Reasignar padres: */
           R.padre = Padre;
           P.padre = Q.padre = R;
           if(B != null) B.padre = Q;
           if(C != null) C.padre = P;

           /* Ajustar valores de FE: */
           switch(R.FE) {
              case -1: Q.FE = 0; P.FE = 1; break;
              case 0:  Q.FE = 0; P.FE = 0; break;
              case 1:  Q.FE = -1; P.FE = 0; break;
           }
           R.FE = 0;
        }
        /* Rotación doble a izquierdas */
        void rotacionDobleIzquierda(Nodo nodo)
        {
           Nodo Padre = nodo.padre;
           Nodo P = nodo;
           Nodo Q = P.derecho;
           Nodo R = Q.izquierdo;
           Nodo B = R.izquierdo;
           Nodo C = R.derecho;

           if(Padre != null)
             if(Padre.derecho == nodo) Padre.derecho = R;
             else Padre.izquierdo = R;
           else this.raiz = R;

           /* Reconstruir árbol: */
           P.derecho = B;
           Q.izquierdo = C;
           R.izquierdo = P;
           R.derecho = Q;

           /* Reasignar padres: */
           R.padre = Padre;
           P.padre = Q.padre = R;
           if(B != null) B.padre = P;
           if(C != null) C.padre = Q;

           /* Ajustar valores de FE: */
           switch(R.FE) {
              case -1: P.FE = 0; Q.FE = 1; break;
              case 0:  P.FE = 0; Q.FE = 0; break;
              case 1:  P.FE = -1; Q.FE = 0; break;
           }
           R.FE = 0;
        }
        
        /* Rotación simple a derechas */
        void rotacionSimpleDerecha(Nodo nodo)
        {
           Nodo Padre = nodo.padre;
           Nodo P = nodo;
           Nodo Q = P.izquierdo;
           Nodo B = Q.derecho;

           if(Padre != null)
             if(Padre.derecho == P) Padre.derecho = Q;
             else Padre.izquierdo = Q;
           else this.raiz = Q;

           /* Reconstruir árbol: */
           P.izquierdo = B;
           Q.derecho = P;

           /* Reasignar padres: */
           P.padre = Q;
           if(B != null) B.padre = P;
           Q.padre = Padre;

           /* Ajustar valores de FE: */
           P.FE = 0;
           Q.FE = 0;
        }
        /* Rotación simple a izquierdas */
        void rotacionSimpleIzquierda(Nodo nodo)
        {
           Nodo Padre = nodo.padre;
           Nodo P = nodo;
           Nodo Q = P.derecho;
           Nodo B = Q.izquierdo;

           if(Padre != null)
             if(Padre.derecho == P) Padre.derecho = Q;
             else Padre.izquierdo = Q;
           else this.raiz = Q;

           /* Reconstruir árbol: */
           P.derecho = B;
           Q.izquierdo = P;

           /* Reasignar padres: */
           P.padre = Q;
           if(B != null) B.padre = P;
           Q.padre = Padre;

           /* Ajustar valores de FE: */
           P.FE = 0;
           Q.FE = 0;
        }
        /* Eliminar un elemento de un árbol ABB */
       public void borrar(int dat) {
    Nodo padre = null;
    Nodo actual = this.raiz;
    Nodo nodo;
    int aux;

    // Mientras el nodo no sea nulo
    while (actual != null) {
        if (dat == actual.dato) { // Si encontramos el nodo a eliminar
            // Caso 1: Nodo hoja
            if (actual.izquierdo == null && actual.derecho == null) {
                if (padre != null) {
                    if (padre.izquierdo == actual) padre.izquierdo = null;
                    else padre.derecho = null;
                } else {
                    this.raiz = null; // Si es la raíz y único nodo, el árbol queda vacío
                }
                actual = null;
            }
            // Caso 2: Nodo con dos hijos
            else if (actual.izquierdo != null && actual.derecho != null) {
                padre = actual;
                nodo = actual.derecho;
                while (nodo.izquierdo != null) { // Buscar el menor de la derecha
                    padre = nodo;
                    nodo = nodo.izquierdo;
                }
                // Intercambiar los valores y seguir eliminando el nodo hoja
                aux = actual.dato;
                actual.dato = nodo.dato;
                nodo.dato = aux;
                actual = nodo; // Ahora seguimos con la eliminación del nodo hoja
            }
            // Caso 3: Nodo con un solo hijo
            else {
                nodo = (actual.izquierdo != null) ? actual.izquierdo : actual.derecho;
                if (padre == null) {
                    this.raiz = nodo;
                } else {
                    if (padre.izquierdo == actual) padre.izquierdo = nodo;
                    else padre.derecho = nodo;
                }
                if (nodo != null) nodo.padre = padre;
            }

            // Ajuste de balance después de la eliminación
            if (padre != null) {
                if (padre.derecho == actual) {
                    equilibrar(padre, 1, false);
                } else {
                    equilibrar(padre, -1, false);
                }
            }
            return;
        } else { // Seguir buscando el nodo
            padre = actual;
            if (dat > actual.dato) actual = actual.derecho;
            else actual = actual.izquierdo;
        }
    }
}

      }




// Clase principal
public class Main {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        // Aquí puedes agregar nodos y probar la función podar
    }
}
