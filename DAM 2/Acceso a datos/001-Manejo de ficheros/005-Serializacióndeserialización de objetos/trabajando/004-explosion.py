archivo = open("archivo.txt",'r')
contenido = archivo.read()
print(contenido)
lista = contenido.split("|")     ## separa la cadena de texto por un pipe
print(lista)                       

variable1 = lista[0]
variable2 = lista[1]

print(variable1)
print(variable2)
