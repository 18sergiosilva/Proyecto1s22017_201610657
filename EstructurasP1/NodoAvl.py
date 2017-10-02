class NodoAvl:

    def __init__(self, ramaIzdo, valor, ramaDcho):
        self.dato=valor
        self.apuntador = None
        self.pas = None
        self.izdo=ramaIzdo
        self.dcho=ramaDcho
        self.fe = 0

    def __init__(self, valor):
        # super(Nodo, self).__init__()
        self.dato = valor
        self.apuntador = None
        self.pas = None
        self.izdo=None
        self.dcho=None
        self.fe = 0

    def valorNodo(self):
        return self.dato

    def setValor(self, dato):
        self.dato=dato

    def subarbolIzdo(self):
        return self.izdo

    def subarbolDcho(self):
        return self.dcho

    def nuevoValor(self, d):
        self.dato=d

    def ramaIzdo(self, n):
        self.izdo=n

    def ramaDcho(self, n):
        self.dcho=n

    def visitar(self):
        r= self.dato
        return r