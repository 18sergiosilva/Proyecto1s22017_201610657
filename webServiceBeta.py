__author__ = "Mac"
__date__ = "$Ago 6, 2017 11:41:07 AM$"

from flask import Flask, request

app = Flask("ejemplo_segundo_semestre_2017")

@app.route('/metodo2',methods=['POST']) 
def h():
    parametroPython = str(request.form['p'])
    return "parametro!! = " + parametroPython


@app.route('/crearUsuario', methods=['POST'])
def crearUsuario():
	nombredeusuario = str(request.form['NOMBRE DE USUARIO:'])
	password = str(request.form['CONTRASEÑA: '])
	//verificar contraseña y nombre de usuario 
	return "SE HA REGISTRADO EXITOSAMENTE"






@app.route('/hola',methods=['GET']) 
def he():
    return "hola Mundo"

if __name__ == "__main__":
  app.run(debug=True, host='127.0.0.1')