import os

# Especifica el nombre de la carpeta
nueva_carpeta = "\\nueva_carpeta"
print("estoy en la carpeta",os.getcwd())
ruta= os.getcwd()+nueva_carpeta
if os.path.exists(ruta):
    print("el fichero existe")

# Borra la carpeta
os.rmdir(ruta)

print(f"Carpeta '{nueva_carpeta}' borrada correctamente.")







