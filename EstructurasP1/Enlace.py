from flask import Flask,request,Response
from Lista import ListaCircularDoblementeEnlazada
from ArbolB import ArbolB
from ArbolB2 import ArbolB2
from ArbolAvl import Avl
from ABB import ArbolBB
from ListaArchivo import ListaCircularDoblementeEnlazadaArchivo
import os, sys

listaUsuarios=ListaCircularDoblementeEnlazada()
ABgeneral=ArbolB2()
arbol=ArbolBB()
listaArchivo=ListaCircularDoblementeEnlazadaArchivo()

app = Flask("proyecto1")

@app.route('/insertarUser',methods=['POST']) 
def iu():
    parametroPython = str(request.form['user'])
    parametroPython2 = str(request.form['password'])
    largoContrasenia=len(parametroPython2)
    if listaUsuarios.buscar(parametroPython)!=True:
    	if largoContrasenia>=4:
    		listaUsuarios.agregar_inicio(parametroPython,parametroPython2)
    		print "**************"
    		listaUsuarios.recorrer_fin_inicio()
    		print "**************"	
    		ABgeneral.graficar()
    		return "valido"
    	else:
    		print("contrasenia invalida")
    		return"FalsePass"
    	
    else:
    	print "usuario ya existe"
    	return "FalseUser"
@app.route('/validarUser',methods=['POST']) 
def vu():
	#parametroPython=str(request.form['user'])
    parametroPython2=str(request.form['password'])
    parametroPython=str(request.form['user'])
    aux=listaUsuarios.buscarPosNodo(parametroPython,listaUsuarios.primero)
    if aux!= None and aux.contrasenia==parametroPython2:
    	ABgeneral.insertar(aux.usuario)
    	ABgeneral.graficar()
    	print("ingreso: "+aux.usuario)
    	return aux.usuario
    else:
    	return "invalido"

@app.route('/nuevaCarpeta',methods=['POST']) 
def nc():
	#parametroPython=str(request.form['user'])
    parametroPython2=str(request.form['carpeta'])
    parametroPython=str(request.form['user'])
    aux=ABgeneral.bus(ABgeneral.raiz,parametroPython)
    print("solicita: "+parametroPython)
    print(str(aux))
    if aux!=None:
    	aux.nuevoArbol.insertar(parametroPython2)
    	aux.nuevoArbol.graficar()
    	return parametroPython2
    else:
    	return "invalido"

@app.route('/insertarArbol',methods=['POST']) 
def ia():
    parametroPython = str(request.form['user'])
    parametroPython2 = str(request.form['password'])
    if arbol.buscar(arbol.raiz,parametroPython)!=True:
    	arbol.insertar1(parametroPython,parametroPython2)
    	print "**************"
    	arbol.mostrarEnPreordenABB(arbol.raiz)
    	arbol.graficarABB()
    	print "**************"
    	return "valido"
    else:
    	print "usuario ya existe"
    	return "False"

@app.route('/verificarPassword',methods=['POST']) 
def v():
    parametroPython = str(request.form['user'])
    parametroPython2 = str(request.form['password'])
    auxLista=arbol.buscar1(arbol.raiz,parametroPython)
    if auxLista!=None and auxLista.contrasenia==parametroPython2:
    #	auxLista.lista=ListaDoblementeEnlazada()
    	print "Usuario valido"
        return "prueba"
    else:
    	print "usuario invalido"
    	return "False"

@app.route('/subirArchivo',methods=['POST']) 
def sa():
	print("entro a subir archivo")
	parametroPython=str(request.form['archivo'])
	parametroPython2=str(request.form['user'])
	listaArchivo.agregar_inicio(parametroPython,parametroPython2)
	listaArchivo.recorrer_fin_inicio()
	return "valido"

@app.route('/descargarArchivo',methods=['POST']) 
def da():
	print("entro a descargar archivo")
	parametroPython=str(request.form['archivo'])
	parametroPython2=str(request.form['key'])
	parametroPython3=str(request.form['user'])
	auxuser=listaUsuarios.buscarPosNodo(parametroPython3,listaUsuarios.primero)
	aux=listaArchivo.buscarPosNodo(parametroPython,listaArchivo.primero)
	if aux.contrasenia==auxuser.keypublic:
		if parametroPython2==auxuser.keyprivate:
			print(aux.usuario+"funciono")
			return "valido"
		else:
			return "nel"
	else:
		print("nel prro!")
		return "nel"

@app.route('/nuevaArchivo',methods=['POST']) 
def na():
	#parametroPython=str(request.form['user'])
    parametroPython2=str(request.form['archivo'])
    parametroPython=str(request.form['user'])
    aux=ABgeneral.bus(ABgeneral.raiz,parametroPython)
    print("solicita archivo: "+parametroPython)
    print(str(aux))
    if aux!=None:
    	aux.avl.insertar(parametroPython2)
    	
    	return parametroPython2
    else:
    	return "invalido"

@app.route('/nuevaArchivoCarpeta',methods=['POST']) 
def nac():
	#parametroPython=str(request.form['user'])
    parametroPython2=str(request.form['archivo'])
    parametroPython=str(request.form['user'])
    parametroPython3=str(request.form['carpeta'])
    aux=ABgeneral.bus(ABgeneral.raiz,parametroPython)
    print("solicita archivo: "+parametroPython)
    print(str(aux))
    if aux!=None:
    	aux.nuevoArbol.bus(aux.nuevoArbol.raiz,parametroPython3).Avl.insertar(parametroPython2)
    	aux.nuevoArbol.bus(aux.nuevoArbol.raiz,parametroPython3).Avl.graficar(parametroPython+parametroPython3)
    	
    	return parametroPython2
    else:
    	return "invalido"

@app.route('/deleteAr',methods=['POST']) 
def dar():
	#parametroPython=str(request.form['user'])
    parametroPython2=str(request.form['archivo'])
    parametroPython=str(request.form['user'])
    parametroPython3=str(request.form['carpeta'])
    aux=ABgeneral.bus(ABgeneral.raiz,parametroPython)
    print("solicita archivo: "+parametroPython)
    print(str(aux))
    if aux!=None:
    	aux.nuevoArbol.bus(aux.nuevoArbol.raiz,parametroPython3).Avl.eliminar(parametroPython2)
    	aux.nuevoArbol.bus(aux.nuevoArbol.raiz,parametroPython3).Avl.graficar(parametroPython+parametroPython3)
    	
    	return parametroPython2
    else:
    	return "invalido"



@app.route('/crearCarpetaAndroid', methods = ['POST'])
def cca():
    directorio = str(request.form['directorio'])
    directorio2 = str(request.form['directorio2'])
    try:
        path = 'C:\Users\elizabeth\Desktop\perrito' + '\\' + directorio2 + '\\' + directorio  
        os.mkdir(path);
        return "CARPETA CREADA EXITOSAMENTE"
        pass
    except Exception as e:
        return "CARPETA NO CREADA"
        raise e


@app.route('/crearCarpetaAndroidInicial', methods = ['POST'])
def ccaI():
    directorio = str(request.form['directorio'])
    try:
        path = 'C:\Users\elizabeth\Desktop\perrito' + '\\' + directorio  
        os.mkdir(path);
        return "CARPETA CREADA EXITOSAMENTE"
        pass
    except Exception as e:
        return "CARPETA NO CREADA"
        raise e
        

@app.route('/subirArchivoMiCarpeta', methods = ['POST'])
def samc():
    nombreCarpeta = str(request.form['nombreCarpeta'])
    nombreArchivo = str(request.form['nombreArchivo'])
    try:
        file = open('C:\Users\elizabeth\Desktop\perrito' + '\\' + nombreCarpeta + '\\' + nombreArchivo + '.txt', "w")
        file.close()
        return "ARCHIVO SUBIDO A MI CARPETA"
        pass
    except Exception as e:
        return "ARCHIVO NO SUBIDO"
        raise e


@app.route('/subirArchivoACarpeta', methods = ['POST'])
def saac():
    carpetaUsuario = str(request.form['carpetaUsuario'])
    nombreCarpeta = str(request.form['nombreCarpeta'])
    nombreArchivo = str(request.form['nombreArchivo'])
    try:
        file = open('C:\Users\elizabeth\Desktop\perrito' + '\\' + carpetaUsuario + '\\' + nombreCarpeta + '\\' + nombreArchivo + '.txt', "w") 
        file.close()
        return "ARCHIVO SUBIDO A DICHA CARPETA"
        pass
    except Exception as e:
        return "ARCHIVO NO SUBIDO"
        raise e
        

 	

@app.route('/hola') 
def he():
    return "hola Mundo" 

if __name__ == "__main__":
  app.run(debug=True, host='192.168.1.7')