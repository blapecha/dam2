import os

# Especifica el nombre de la carpeta
nueva_carpeta = "nueva_carpeta"

# Crea la carpeta
os.makedirs(nueva_carpeta, exist_ok=True)

print(f"Carpeta '{nueva_carpeta}' creada correctamente.")
