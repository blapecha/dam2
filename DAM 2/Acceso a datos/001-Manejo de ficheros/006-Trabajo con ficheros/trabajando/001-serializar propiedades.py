import random
import math
import json

# Declaro una clase Npc con propiedades x, y, y angulo
class Npc:
    def __init__(self):
        self.x = random.randint(0, 512)
        self.y = random.randint(0, 512)
        self.angulo = random.random() * math.pi * 2

# Creo una lista de 20 npcs
npcs = [Npc() for _ in range(20)]

# Utilizo una comprensión de lista para crear la lista de diccionarios con las propiedades
cadena = [{"x": npc.x, "y": npc.y, "angulo": npc.angulo} for npc in npcs]

# Imprimo la lista de diccionarios
print(cadena)

# Guardar los datos en un archivo JSON
with open("basededatos.json", 'w') as mibasededatos:
    json.dump(cadena, mibasededatos)
