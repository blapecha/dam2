variable1 = "hola "
variable2 = "que tal"

archivo = open("archivo.txt",'w')
archivo.write(variable1+variable2)
archivo.close()
try:
    with open("archivo.txt", 'w') as archivo:
        archivo.write(variable1 + variable2)
    print("Archivo escrito correctamente.")
except Exception as e:
    print(f"Ocurrió un error al escribir en el archivo: {e}")

