archivo = open("archivo.txt",'r')
contenido = archivo.read()

variable1 = "como  "
variable2 = "te va la vida"

archivo = open("archivo.txt",'w')
archivo.write(variable1+"|"+variable2)
archivo.close()
print(contenido)
