import random

# Declaro una clase Objeto con tres propiedades: a, b y c
class Objeto:
    def __init__(self):
        self.a = random.randint(1, 100)  # Valor aleatorio entre 1 y 100
        self.b = random.randint(1, 100)  # Valor aleatorio entre 1 y 100
        self.c = random.uniform(0, 1)    # Valor aleatorio entre 0 y 1

# Creo una lista de 30 objetos
objetos = []
cantidad = 30

# Recorro la lista y a cada elemento le meto una instancia de la clase Objeto
for i in range(cantidad):
    objetos.append(Objeto())

cadena = ""

# Concateno las propiedades de cada objeto en una cadena
for obj in objetos:
    cadena += f"{obj.a},{obj.b},{obj.c}|"

print(cadena)

# Guardo la cadena en un archivo de texto
with open("datos.txt", 'w') as archivo:
    archivo.write(cadena)

