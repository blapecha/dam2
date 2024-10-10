import json

class Cliente:
    def __init__(self):
        self.nombre = None
        self.apellidos = None
        self.emails = {"personal": [], "profesional": []}

    def to_dict(self):
        return {
            "nombre": self.nombre,
            "apellidos": self.apellidos,
            "emails": self.emails
        }

class Vehiculo:
    def __init__(self):
        self.modelo = None
        self.precio = None
        self.peso = None
        self.itv = None
        self.dimensiones = {"x": None, "y": None, "z": None}

    def to_dict(self):
        return {
            "modelo": self.modelo,
            "precio": self.precio,
            "peso": self.peso,
            "itv": self.itv,
            "dimensiones": self.dimensiones
        }

class Concesionario:
    def __init__(self):
        self.nombre = None
        self.emails = {"personal": [], "profesional": []}
        self.localidad = None

    def to_dict(self):
        return {
            "nombre": self.nombre,
            "emails": self.emails,
            "localidad": self.localidad
        }

# Inicialización de las listas
clientes = []
concesionarios = []

# Creación y adición de un objeto Cliente
clientes.append(Cliente())
cliente= clientes[-1]
cliente.nombre = "Blanca"
cliente.apellidos = "Perez Chavarria"
cliente.emails['profesional'].append("info@educa.com")
cliente.emails['profesional'].append("info@educa.com")
cliente.emails['personal'].append("edukka2@gmail.com")

# Creación y adición de un objeto Concesionario
concesionarios.append(Concesionario())
concesionario = concesionarios[-1]
concesionario.nombre = "Ford"
concesionario.emails['profesional'].append("ford@todocoches.com")

# Imprimir los emails
print(cliente.emails)
print(concesionario.emails)

# Serializar y guardar en archivos JSON
with open("clientes.json", 'w') as archivo:
    json.dump(cliente.to_dict(), archivo, indent=4)

with open("concesionarios.json", 'w') as archivo:
    json.dump(concesionario.to_dict(), archivo, indent=4)


        


