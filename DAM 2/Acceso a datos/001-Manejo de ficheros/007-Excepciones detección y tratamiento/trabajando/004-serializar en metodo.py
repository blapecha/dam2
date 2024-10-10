import json

class Cliente:
    def __init__(self):
        self.nombre = None
        self.apellidos = None
        self.emails = {"personal":[],"profesional":[]}
    def to_dict(self):
        return {
            "nombre": self.nombre,
            "apellidos": self.apellidos,
            "emails": self.emails
        }

class Producto:
    def __init__(self):
        self.nombre = None
        self.precio = None
        self.peso = None
        self.dimensiones = {"x":None,"y":None,"z":None}

clientes = []
clientes.append(Cliente())
cliente = clientes[0]
cliente.nombre = "Jose Vi"
cliente.apellidos = "Carratalá Sanchis"
cliente.emails['profesional'].append("info@josevicentecarratala.com")
cliente.emails['profesional'].append("info@jocarsa.com")
cliente.emails['personal'].append("jocarsa2@gmail.com")

print(cliente.emails)

archivo = open("clientes.json",'w')
json.dump(cliente.to_dict(),archivo,indent=4)
archivo.close()


        


