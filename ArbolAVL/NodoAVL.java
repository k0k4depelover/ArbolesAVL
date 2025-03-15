package ArbolAVL;

import java.util.ArrayList;
public class NodoAVL {
	int dato, FE;
	NodoAVL izq, der;
	
	public NodoAVL(int dato) {
		this.dato=dato;
		this.FE=0;
		this.izq= this.der =null;
	}
	
}
