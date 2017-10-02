from ArbolB import ArbolB
from ArbolAvl import Avl
class Pagina():
	"""docstring for Nodo"""
	def __init__(self):
		self.n = 0
		self.clave = []
	def insertar(self,id):
		self.n = self.n  + 1 
		self.clave.append(id)
		i = 0 
		while i < len(self.clave):
			j = 0
			while j < len(self.clave)-1:
				if self.clave[j].clave > self.clave[j+1].clave:
					temp = self.clave[j]
					self.clave[j] = self.clave[j+1]
					self.clave[j+1] = temp
				j = j + 1
			i = i + 1
	def corregir(self):
		i = 0 
		while i < len(self.clave)-1:
			if self.clave[i].der != self.clave[i+1].iz:
				self.clave[i+1].iz = self.clave[i].der
			i = i + 1
	def getName(self):
		return hash(self)
	def buscar(self,clave):
		i = 0 
		while i < len(self.clave):
			if self.clave[i].clave == clave:
				return self.clave[i]
			i = i + 1	
		return None								
class Clave():
	def __init__(self,clave):
		self.clave = clave
		self.iz = None
		self.der = None	
		self.x = None
		self.y = None
		self.tiro = None
		self.res = None
		self.emisor = None
		self.receptor = None
		self.fecha = None
		self.tiempo = None
		self.NoTiro	= None
		self.tipo = None
		self.nuevoArbol=ArbolB()
		self.avl=Avl()
class contenedor():
	def __init__(self,nodocalve,paginaiz,paginader):
		self.nodoClave = nodocalve
		self.paginaIz = paginaiz
		self.paginaDer = paginader