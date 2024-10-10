#archivo = open("archivo.txt",'w')

#archivo.write("este es un texto de prueba")

#archivo.close()


# Especifica el nombre del archivo
nombre_archivo = "mi_archivo.txt"

# Abre el archivo en modo escritura,x tanto crea el archivo si no existe
with open(nombre_archivo, 'w') as archivo:
    archivo.write("Este es un nuevo archivo.\n")

print(f"Archivo '{nombre_archivo}' creado correctamente.")
