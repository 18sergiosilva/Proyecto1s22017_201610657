from NodoABB import NodoABB
from graphviz import Digraph

g = Digraph('g', filename='ArbolUsuarios.gv')
g.node_attr.update(color='lightseagreen', style='filled')
g2 = Digraph('g2', filename='ArbolUsuarios2.gv')
class ArbolBB:
    def __init__(self):
        self.raiz=None
        self.nivel=0
        self.altura=0
        self.nivelizq=0
        self.nivelder=0
        self.hojas=0
        self.ramas=0

    def vacio(self):
        if self.raiz==None:
            return True
        else:
            return False

    def insertar(self,valor,contrasenia):
        if self.vacio():
            self.raiz=NodoABB(valor=valor,contrasenia=contrasenia, es_raiz=True)
        else:
            nodo = self.__get_lugar(valor)
            if valor <= nodo.valor:
                nodo.izquierda=NodoABB(valor=valor,contrasenia=contrasenia, padre=nodo,es_izquierda=True)

            else:
                nodo.derecha=NodoABB(valor=valor,contrasenia=contrasenia, padre=nodo,es_derecha=True)

    def insertar1(self,valor,contrasenia):
        if self.vacio():
            self.raiz=NodoABB(valor=valor,contrasenia=contrasenia, es_raiz=True)
            self.nivel=0
            self.altura+=1
            print "saber2"
            g.node(str(self.raiz),str(self.raiz.valor)+"\\n"+self.raiz.contrasenia)
        else:
            nodo = self.__get_lugar(valor)
            #self.nivelizq=0
            #self.nivelder=0
            if valor <= nodo.valor:
                nodo.izquierda=NodoABB(valor=valor,contrasenia=contrasenia, padre=nodo,es_izquierda=True)
                g.node(nodo.valor,nodo.valor+"\\n"+nodo.contrasenia)
                g.node(nodo.izquierda.valor,nodo.izquierda.valor+"\\n"+nodo.izquierda.contrasenia)
                g.edge(nodo.valor,nodo.izquierda.valor)
                self.nivelizq+=1
                print "saber2"
                #g.node(nodo.lista,nodo.lista.dato)
                #g.edge(nodo.izquierda.valor,nodo.lista)
            else:
                nodo.derecha=NodoABB(valor=valor,contrasenia=contrasenia, padre=nodo,es_derecha=True)
                g.node(nodo.valor,nodo.valor+"\\n"+nodo.contrasenia)
                g.node(nodo.derecha.valor,nodo.derecha.valor+"\\n"+nodo.derecha.contrasenia)
                g.edge(nodo.valor,nodo.derecha.valor)
                self.nivelder+=1
                #g.node(nodo.lista,nodo.lista.dato)
                #g.edge(nodo.derecha.valor,nodo.lista)
            if self.nivelder>self.nivelizq:
                self.nivel=self.nivelder
            elif self.nivelizq>self.nivelder:
                self.nivel=self.nivelizq
            elif self.nivelizq==self.nivelder:
                self.nivel=self.nivelizq
            self.altura=self.nivel
            self.nivel=self.nivel-1
            #print "nivel: "+str(self.nivel)+" altura: "+str(self.altura)
            g.node("n","nivel: "+str(self.nivel)+" altura: "+str(self.altura))
            print "saber"



    def graficarABB(self):
        g.view()
        print "graficado"
        return True
    
            
    def __get_lugar(self,valor):
        aux=self.raiz
        while aux:
            temp=aux
            if valor<=aux.valor:
                aux=aux.izquierda
            else:
                aux=aux.derecha
        return temp               

    def grafica2ABB(self,nodo):
        if nodo:
            #print "*******************"
            self.grafica2ABB(nodo.izquierda)
            self.grafica2ABB(nodo.derecha)
            g2.node(str(nodo.padre),str(nodo.valor))
            g2.node(str(nodo.valor),str(nodo.valor))
            g2.edge(str(nodo.padre),str(nodo.valor))
        g2.view()
    
    def mostrarEnOrdenABB(self, nodo):
        if nodo:
            self.mostrarEnOrdenABB(nodo.izquierda)
            #izquierda-raiz-derecha
            print nodo.valor+" "+nodo.contrasenia           
            self.mostrarEnOrdenABB(nodo.derecha)

    def graf2(self):
        g2.view()
        return True

    def mostrarEnPreordenABB(self,nodo):
    	#raiz-izquierda-derecha
    	if nodo:
    		print nodo.valor
    		self.mostrarEnPreordenABB(nodo.izquierda)
    		self.mostrarEnPreordenABB(nodo.derecha)

    def mostrarEnPosOden(self,nodo):
    	#izquierda-derecha-raiz
    	if nodo:
    		self.mostrarEnPosOden(nodo.izquierda)
    		self.mostrarEnPosOden(nodo.derecha)
    		print nodo.valor


    def buscar(self, nodo, valor):
    	if nodo == None:
    		#print "No se encuentra en el arbol"
            return False
    	else:
    		if nodo.valor==valor:
    			return True
    		elif valor <= nodo.valor:
    			return self.buscar(nodo.izquierda,valor)
    		else:
    			return self.buscar(nodo.derecha, valor)

    def buscar1(self, nodo, valor):
        if nodo == None:
            #print "No se encuentra en el arbol"
            return None
        else:
            if nodo.valor==valor:
                return nodo
            elif valor <= nodo.valor:
                return self.buscar1(nodo.izquierda,valor)
            else:
                return self.buscar1(nodo.derecha, valor)
    
    def modificar(self,nodo,valor,valorNuevo):
      if nodo == None:
            #print "No se encuentra en el arbol"
            return False
      else:
            if nodo.valor==valor :
                nodo.valor=valorNuevo
            elif valor <= nodo.valor:
                return self.modificar(nodo.izquierda)
            else:
                return self.modificar(nodo.derecha)

    def eliminar(self,valor):
        nodoAux=self.raiz
        nodoPAdre=self.raiz
        esHijoIzquierdo=True
        while nodoAux.valor!=valor:
            nodoPAdre=nodoAux
            if valor<nodoAux.valor:
                esHijoIzquierdo=True
                nodoAux=nodoAux.izquierda
            else:
                esHijoIzquierdo=False
                nodoAux=nodoAux.derecha
            if nodoAux==None:
                    return False
        if nodoAux.izquierda==None and nodoAux.derecha==None:
            if nodoAux==self.raiz:
                self.raiz=None
            elif esHijoIzquierdo:
                nodoPAdre.izquierda=None
            else:
                nodoPAdre.derecha=None
        elif nodoAux.derecha==None:
            if nodoAux==self.raiz:
                self.raiz=nodoAux.izquierda
            elif esHijoIzquierdo:
                nodoPAdre.izquierda=nodoAux.izquierda
            else:
                nodoPAdre.derecha=nodoAux.izquierda
        elif nodoAux.izquierda==None:
            if nodoAux==self.raiz:
                self.raiz=nodoAux.derecha
            elif esHijoIzquierdo:
                nodoPAdre.izquierda=nodoAux.derecha
            else:
                nodoPAdre.derecha=nodoAux.derecha
        else:
            reemplazo =self.obtenerRemplazo(nodoAux)
            if nodoAux==self.raiz:
                self.raiz=reemplazo
            elif esHijoIzquierdo:
                nodoPAdre.izquierda=reemplazo
            else:
                padre.derecha=reemplazo
            reemplazo.izquierda=nodoAux.izquierda
        return True
    def obtenerRemplazo(self, nodoReemplazo):
        reemplazarPadre=nodoReemplazo
        reemplazo=nodoReemplazo
        auxiliar=nodoReemplazo.derecha
        while auxiliar!=None:
            reemplazarPadre=reemplazo
            reemplazo=auxiliar
            auxiliar=auxiliar.izquierda
        if reemplazo!=nodoReemplazo.derecha:
            reemplazarPadre.izquierda=reemplazo.derecha
            reemplazo.derecha=nodoReemplazo.derecha
        print "el nodo reemplazo es :"+ reemplazo.valor
        return reemplazo    










    		
    		

    





    