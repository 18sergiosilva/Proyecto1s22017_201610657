from ArbolB import ArbolB
from ArbolAvl import Avl

class Nodo:
    def __init__(self,usuario,contrasenia):
        self.usuario=usuario
        self.contrasenia=contrasenia
        self.siguiente=None
        self.anterior=None
        self.arbolB=ArbolB()
        self.avl=Avl()
        self.keypublic=usuario+"usac"
        self.keyprivate=contrasenia