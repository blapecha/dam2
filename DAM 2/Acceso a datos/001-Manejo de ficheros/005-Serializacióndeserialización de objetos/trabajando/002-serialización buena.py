variable1 = "hola"
variable2 = "que tal"

# Escribir en el archivo
with open("archivo.txt", 'w') as archivo:
    archivo.write(variable1 + "|" + variable2)

print("El archivo ha sido escrito correctamente.")

# Leer y mostrar el contenido del archivo
with open("archivo.txt", 'r') as archivo:
    contenido = archivo.read()
    print(f"Contenido del archivo: {contenido}")
archivo.close()