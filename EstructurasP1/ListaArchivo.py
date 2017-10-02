from NodoArchivo import NodoArchivo

class ListaCircularDoblementeEnlazadaArchivo:
    def __init__(self):
        self.primero=None
        self.Ultimo=None

    def vacia(self):
        if self.primero==None:
            return True
        else:
            return False

    def agregar_inicio(self,usuario,contrasenia):
        if self.vacia():
            self.primero = self.Ultimo=NodoArchivo(usuario,contrasenia)
        else:
            aux=NodoArchivo(usuario,contrasenia)
            aux.siguiente=self.primero
            self.primero.anterior=aux
            self.primero=aux
        self.__unirNodos()

    def __unirNodos(self):
        if self.primero != None:
            self.primero.anterior=self.Ultimo
            self.Ultimo.siguiente=self.primero

    def recorrer_inicio_fin(self):
        aux=self.primero
        while aux:
            print(str(aux.usuario)+" "+str(aux.contrasenia))
            aux=aux.siguiente
            if aux==self.primero:
                break
        print(str(self.primero.usuario)+" "+str(self.primero.contrasenia)) 

    def recorrer_fin_inicio(self):
        aux=self.Ultimo
        while aux:
            print(str(aux.usuario)+" "+str(aux.contrasenia))
            aux=aux.anterior
            if aux==self.Ultimo:
                break
        print(str(self.Ultimo.usuario)+" "+str(self.Ultimo.contrasenia)) 

    def eliminar_inicio(self):
        if self.vacia():
            print ("estructura vacia")
        elif self.primero==self.Ultimo:
            self.primero=self.Ultimo=None
        else:
            self.primero=self.primero.siguiente
        self.__unirNodos()

    def buscar(self,dato):
        aux =self.primero
        while aux:
            if aux.usuario==dato:
                return True
            else:
                aux=aux.siguiente
                if aux==self.primero:
                    return False    

    def buscarPosNodo(self,dato,nodo):
        aux =nodo
        while aux:
            if aux.usuario==dato:
                return aux
            else:
                aux=aux.siguiente
                if aux==nodo:
                    return None 